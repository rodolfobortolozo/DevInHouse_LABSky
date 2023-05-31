package tech.devinhouse.labsky.services;

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

        Optional<Passageiro> passageiro = this.passagerioRepository.findById(checkinReq.getCpf());
        Optional<Assento> assento = this.assentoRepository.findById(checkinReq.getAssento());

        if(passageiro.isEmpty()){
            throw new PassagerioNotFoundException("Cpf não encontrado.");
        }

        if(assento.isEmpty()){
            throw new AssentoNotFoundException("Assento não encontrado.");
        }

        if(!assento.get().isDisponivel()){
            throw new AssentoConflitException("Assento ocupado");
        }

        int idade = CalculdaIdade.idade(passageiro.get().getDataNascimento());
        if(idade<=18 & assento.get().isSaidaEmergencia()){
            throw new AssentoException("Passageiro Menor de idade para assento de emergência.");
        }

        if(assento.get().isSaidaEmergencia() & !checkinReq.isMalasDespachadas() ) {
            throw new AssentoException("Saída de emergência despache a mala. ");
        }

        Checkin checkin = new Checkin();
        checkin.setDataHoraConfirmacao(new Date());
        checkin.setPassageiro(passageiro.get());
        checkin.setAssento(assento.get());
        checkin.setMalasDespachadas(checkinReq.isMalasDespachadas());

        Checkin newCheckin = this.checkinRepository.save(checkin);

        assento.get().setDisponivel(false);
        this.assentoRepository.save(assento.get());

        return newCheckin;
    }

    public Checkin findByCpf(String cpf){
        return this.checkinRepository.findByPassageiroCpf(cpf);
    }
}
