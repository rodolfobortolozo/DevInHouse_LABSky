package tech.devinhouse.labsky.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devinhouse.labsky.exceptions.AssentoConflitException;
import tech.devinhouse.labsky.exceptions.AssentoException;
import tech.devinhouse.labsky.exceptions.AssentoNotFoundException;
import tech.devinhouse.labsky.exceptions.PassagerioNotFoundException;
import tech.devinhouse.labsky.models.Assento;
import tech.devinhouse.labsky.models.Checkin;
import tech.devinhouse.labsky.models.Classificacao;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.models.dto.CheckinReq;
import tech.devinhouse.labsky.models.dto.CheckinRes;
import tech.devinhouse.labsky.repositories.AssentoRepository;
import tech.devinhouse.labsky.repositories.CheckinRepository;
import tech.devinhouse.labsky.repositories.PassagerioRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CheckinServiceTest {

  @Mock
  private CheckinRepository checkinRepository;
  @Mock
  private PassagerioRepository passageiroRepository;
  @Mock
  private AssentoRepository assentoRepository;
  @InjectMocks
  private CheckinService checkinService;

  @Test
  @DisplayName("1- Quando nao existe passageiro, deve retornar uma exception ")
  void chekinPassageiroNaoCadastrado() {

    Optional<Passageiro> opPassageiro = Optional.empty();

    Optional<Assento> opAssento = Optional.of(
            new Assento(1L, "A1", true, false, false)
    );

    CheckinReq checkinReq = new CheckinReq("000-000-000.00", "A1", true);
    Checkin newCheckin = new Checkin(1L,UUID.randomUUID(),new Passageiro(),opAssento.get(),true,new Date());


    Mockito.when(passageiroRepository.findByCpf(Mockito.anyString())).thenReturn(opPassageiro);
    Mockito.when(assentoRepository.findByNroAssento(Mockito.anyString())).thenReturn(opAssento);

    assertThrows(PassagerioNotFoundException.class,()->{
      checkinService.save(checkinReq);
    });

  }

  @Test
  @DisplayName("2- Quando nao existe assento, deve retornar uma exception ")
  void chekinAssentoNaoCadastrado() {

    Optional<Passageiro> opPassageiro = Optional.of(
            new Passageiro(1L, "000-000-000.00", "Rodolfo",
                    LocalDate.of(1989, Month.APRIL, 3), Classificacao.VIP, 100));

    Optional<Assento> opAssento = Optional.empty();

    CheckinReq checkinReq = new CheckinReq("000-000-000.00", "A1", true);
    Checkin newCheckin = new Checkin(1L,UUID.randomUUID(),opPassageiro.get(), new Assento(),true,new Date());


    Mockito.when(passageiroRepository.findByCpf(Mockito.anyString())).thenReturn(opPassageiro);
    Mockito.when(assentoRepository.findByNroAssento(Mockito.anyString())).thenReturn(opAssento);

    assertThrows(AssentoNotFoundException.class,()->{
      checkinService.save(checkinReq);
    });

  }

  @Test
  @DisplayName("3- Quando assento estiver ocupado, deve retornar uma exception ")
  void chekinAssentoOcupado() {

    Optional<Passageiro> opPassageiro = Optional.of(
            new Passageiro(1L, "000-000-000.00", "Rodolfo",
                    LocalDate.of(1989, Month.APRIL, 3), Classificacao.VIP, 100));

    Optional<Assento> opAssento = Optional.of(
            new Assento(1L, "A1", false, false, false)
    );

    CheckinReq checkinReq = new CheckinReq("000-000-000.00", "A1", true);
    Checkin newCheckin = new Checkin(1L,UUID.randomUUID(),opPassageiro.get(), opAssento.get(),true,new Date());


    Mockito.when(passageiroRepository.findByCpf(Mockito.anyString())).thenReturn(opPassageiro);
    Mockito.when(assentoRepository.findByNroAssento(Mockito.anyString())).thenReturn(opAssento);

    assertThrows(AssentoConflitException.class,()->{
      checkinService.save(checkinReq);
    });

  }

  @Test
  @DisplayName("4- Quando passageiro for menor de idade e assento for de emergencia, deve retornar uma exception ")
  void chekinPassageiroMenorDeIdade() {

    Optional<Passageiro> opPassageiro = Optional.of(
            new Passageiro(1L, "000.000.000-00", "Rodolfo",
                    LocalDate.of(2010, Month.APRIL, 3), Classificacao.VIP, 100));

    Optional<Assento> opAssento = Optional.of(
            new Assento(1L, "A1", true, true, true)
    );

    CheckinReq checkinReq = new CheckinReq("000-000-000.00", "A1", true);
    Checkin newCheckin = new Checkin(1L,UUID.randomUUID(),opPassageiro.get(), opAssento.get(),true, new Date());


    Mockito.when(passageiroRepository.findByCpf(Mockito.anyString())).thenReturn(opPassageiro);
    Mockito.when(assentoRepository.findByNroAssento(Mockito.anyString())).thenReturn(opAssento);

    assertThrows(AssentoException.class,()->{
      checkinService.save(checkinReq);
    });

  }

  @Test
  @DisplayName("4- Quando passageiro for menor de idade e assento for de emergencia, deve retornar uma exception ")
  void chekinAssentoEmergenciaDespacharMala() {

    Optional<Passageiro> opPassageiro = Optional.of(
            new Passageiro(1L, "000.000.000-00", "Rodolfo",
                    LocalDate.of(1989, Month.APRIL, 3), Classificacao.VIP, 100));

    Optional<Assento> opAssento = Optional.of(
            new Assento(1L, "A1", true, true, true)
    );

    CheckinReq checkinReq = new CheckinReq("000-000-000.00", "A1", false);
    Checkin newCheckin = new Checkin(1L,UUID.randomUUID(),opPassageiro.get(), opAssento.get(),true, new Date());


    Mockito.when(passageiroRepository.findByCpf(Mockito.anyString())).thenReturn(opPassageiro);
    Mockito.when(assentoRepository.findByNroAssento(Mockito.anyString())).thenReturn(opAssento);

    assertThrows(AssentoException.class,()->{
      checkinService.save(checkinReq);
    });

  }
  @Test
  @DisplayName("6 - Salvar Checkin e gerar Checkin")
  void chekinRealizadoComSucesso() {

    Optional<Passageiro> opPassageiro = Optional.of(
            new Passageiro(1L, "000-000-000.00", "Rodolfo",
                    LocalDate.of(1989, Month.APRIL, 3), Classificacao.VIP, 100));

    Optional<Assento> opAssento = Optional.of(
            new Assento(1L, "A1", true, false, false)
    );

    CheckinReq checkinReq = new CheckinReq("000-000-000.00", "A1", true);
    Checkin newCheckin = new Checkin(1L,UUID.randomUUID(),opPassageiro.get(),opAssento.get(),true,new Date());


    Mockito.when(passageiroRepository.findByCpf(Mockito.anyString())).thenReturn(opPassageiro);
    Mockito.when(assentoRepository.findByNroAssento(Mockito.anyString())).thenReturn(opAssento);

    Mockito.when(checkinRepository.save(Mockito.any(Checkin.class))).thenReturn(newCheckin);

    Checkin checkin = checkinService.save(checkinReq);

    assertEquals(checkin.getAssento(),newCheckin.getAssento());
    verify(checkinRepository,times(1)).save(Mockito.any(Checkin.class));
  }

  @Test
  @DisplayName("6 - Atualiza os posntos do Pasageiro")
  void chekinRecalculaPontos() {

    Integer milhasAtual = 100;
    Classificacao classificacao = Classificacao.VIP;
    Integer milhasAtualizadas = milhasAtual + classificacao.getPontos();

    Optional<Passageiro> opPassageiro = Optional.of(
            new Passageiro(1L, "000-000-000.00", "Rodolfo",
                    LocalDate.of(1989, Month.APRIL, 3), classificacao, milhasAtual));
    Optional<Assento> opAssento = Optional.of(
            new Assento(1L, "A1", true, false, false)
    );
    CheckinReq checkinReq = new CheckinReq("000-000-000.00", "A1", true);
    Checkin newCheckin = new Checkin(1L,UUID.randomUUID(),opPassageiro.get(),opAssento.get(),true,new Date());


    Mockito.when(passageiroRepository.findByCpf(Mockito.anyString())).thenReturn(opPassageiro);
    Mockito.when(assentoRepository.findByNroAssento(Mockito.anyString())).thenReturn(opAssento);
    Mockito.when(checkinRepository.save(Mockito.any(Checkin.class))).thenReturn(newCheckin);

    Checkin checkin = checkinService.save(checkinReq);

    assertEquals(milhasAtualizadas,newCheckin.getPassageiro().getMilhas());
    verify(checkinRepository,times(1)).save(Mockito.any(Checkin.class));
  }
}


