package tech.devinhouse.labsky.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devinhouse.labsky.exceptions.PassagerioNotFoundException;
import tech.devinhouse.labsky.models.Classificacao;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.models.dto.PassagerioResChekin;
import tech.devinhouse.labsky.repositories.CheckinRepository;
import tech.devinhouse.labsky.repositories.PassagerioRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PassageiroServiceTest {

    @Mock
    private PassagerioRepository passagerioRepository;
    @Mock
    private CheckinRepository checkinRepository;
    @InjectMocks
    private PassageiroService passageiroService;

    @Test
    @DisplayName("1 - Quando contem registros cadastrados, deve retornar lista com registros")
    void listarTodosPassageiros(){
        List<Passageiro> passageiros = List.of( new Passageiro(1L,"000.000.000-00","Rodolfo R B",
                                            LocalDate.of(1989, Month.APRIL,04),Classificacao.VIP,100));

        Mockito.when(passagerioRepository.findAll()).thenReturn(passageiros);
        List<PassagerioResChekin> listPassageiro = passageiroService.getAll();
        assertFalse(listPassageiro.isEmpty());
        assertEquals(passageiros.size(), listPassageiro.size());

    }

    @Test
    @DisplayName("2 - Quando sem registros cadastrados, deve retornar lista vazia")
    void listarNenhumPassageiro() {
        List<PassagerioResChekin> passageiro = passageiroService.getAll();
        assertTrue(passageiro.isEmpty());
    }

    @Test
    @DisplayName("3 - Quando cpf existir, deve retornar uma pessoa")
    void listarPassageiroPorCpf(){
        Passageiro passageiro = new Passageiro(1L,"000.000.000-00","Rodolfo R B",
                LocalDate.of(1989, Month.APRIL,04),Classificacao.VIP,100);

        Mockito.when(passagerioRepository.findByCpf("000.000.000-00")).thenReturn(Optional.of(passageiro));
        Passageiro newPassageiro = passageiroService.getByCpf("000.000.000-00");
        assertNotNull(newPassageiro);
        assertEquals(passageiro.getCpf(), newPassageiro.getCpf());
    }


    @Test
    @DisplayName("4 - Quando cpf não existir, deve retornar uma exception")
    void cpfNaoEncontrado(){
        Mockito.when(passagerioRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(PassagerioNotFoundException.class, () -> passageiroService.getByCpf("000.000.000-00"));
    }

}