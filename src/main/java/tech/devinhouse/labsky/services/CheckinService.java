package tech.devinhouse.labsky.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.devinhouse.labsky.exceptions.AssentoConflitException;
import tech.devinhouse.labsky.exceptions.AssentoException;
import tech.devinhouse.labsky.exceptions.AssentoNotFoundException;
import tech.devinhouse.labsky.exceptions.PassagerioNotFoundException;
import tech.devinhouse.labsky.models.Assento;
import tech.devinhouse.labsky.models.Checkin;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.models.dto.CheckinReq;
import tech.devinhouse.labsky.repositories.AssentoRepository;
import tech.devinhouse.labsky.repositories.CheckinRepository;
import tech.devinhouse.labsky.repositories.PassagerioRepository;
import tech.devinhouse.labsky.utils.CalculdaIdade;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CheckinService {

    private final CheckinRepository checkinRepository;
    private final PassagerioRepository passagerioRepository;
    private final AssentoRepository assentoRepository;

    public CheckinService(CheckinRepository checkinRepository, PassagerioRepository passagerioRepository, AssentoRepository assentoRepository) {
        this.checkinRepository = checkinRepository;
        this.passagerioRepository = passagerioRepository;
        this.assentoRepository = assentoRepository;
    }

    public Checkin save(CheckinReq checkinReq){

        Optional<Passageiro> passageiro = this.passagerioRepository.findByCpf(checkinReq.getCpf());
        Optional<Assento> assento = this.assentoRepository.findByNroAssento(checkinReq.getAssento());

        passageiroNaoEncontrado(passageiro);
        assentoNaoEncontrado(assento);
        assentoDisponivel(assento.get());
        menorDeIdade(passageiro.get(),assento.get());
        assentoEmergencia(assento.get(),checkinReq.isMalasDespachadas());

        Checkin checkin = new Checkin();
        checkin.setDataHoraConfirmacao(new Date());
        checkin.setPassageiro(passageiro.get());
        checkin.setAssento(assento.get());
        checkin.setEticket(generateUUID());
        checkin.setMalasDespachadas(checkinReq.isMalasDespachadas());

        Checkin newCheckin = this.checkinRepository.save(checkin);
        log.info("Confirmação feita pelo passageiro de CPF "+ newCheckin.getPassageiro().getCpf() + " com e-ticket "+newCheckin.getEticket());
        passageiro.get().atualizaMilhas();
        this.passagerioRepository.save(passageiro.get());

        log.info("Muda o status do assento...");
        assento.get().setDisponivel(false);
        this.assentoRepository.save(assento.get());

        return newCheckin;
    }

    public UUID generateUUID(){
        log.info("Gerar UUID para o Ticket...");
        return UUID.randomUUID();
    }

    public void passageiroNaoEncontrado(Optional<Passageiro> passageiro){
        log.info("Valida se passageiro Existe...");
        if(passageiro.isEmpty()){
            throw new PassagerioNotFoundException("Cpf não encontrado.");
        }
    }

    public void assentoNaoEncontrado(Optional<Assento> assento){
        log.info("Valida se assento Existe...");
        if(assento.isEmpty()){
            throw new AssentoNotFoundException("Assento não encontrado.");
        }
    }

    public void assentoDisponivel(Assento assento){
        log.info("Valida se assento está ocupado...");
        if(!assento.isDisponivel()){
            throw new AssentoConflitException("Assento não disponível.");
        }
    }

    public void menorDeIdade(Passageiro passageiro, Assento assento){
        log.info("Valida se passageiro pode sentar no assento de emergência...");
        int idade = CalculdaIdade.idade(passageiro.getDataNascimento());
        if(idade<=18 & assento.isSaidaEmergencia()){
            throw new AssentoException("Passageiro Menor de idade para assento de emergência.");
        }
    }

    public void assentoEmergencia(Assento assento, Boolean malaDespachada){
        log.info("Valida se assnto é de emergência para despachar a mala...");
        if(assento.isSaidaEmergencia() & !malaDespachada ) {
            throw new AssentoException("Saída de emergência despache a mala.");
        }
    }

}
