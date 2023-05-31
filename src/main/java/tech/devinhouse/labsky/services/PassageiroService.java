package tech.devinhouse.labsky.services;

import org.springframework.stereotype.Service;
import tech.devinhouse.labsky.exceptions.PassagerioNotFoundException;
import tech.devinhouse.labsky.models.Checkin;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.models.dto.PassagerioRes;
import tech.devinhouse.labsky.repositories.CheckinRepository;
import tech.devinhouse.labsky.repositories.PassagerioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassageiroService {

  private final PassagerioRepository passagerioRepository;
  private final CheckinRepository checkinRepository;

  public PassageiroService(PassagerioRepository passagerioRepository, CheckinRepository checkinRepository) {
    this.passagerioRepository = passagerioRepository;
    this.checkinRepository = checkinRepository;
  }

  public List<PassagerioRes> getAll(){
    List<PassagerioRes> listPassagerio = new ArrayList<>();

    passagerioRepository.findAll().forEach(passageiro -> {
              Checkin checkin = this.checkinRepository.findByPassageiroCpf(passageiro.getCpf());
              PassagerioRes passagerioRes = new PassagerioRes(passageiro, checkin);
              listPassagerio.add(passagerioRes);

            }
    );
    return listPassagerio;

  }

  public Passageiro getByCpf(String cpf){
    Optional<Passageiro> opPassageiro = passagerioRepository.findById(cpf);

    if(opPassageiro.isPresent()){
      return opPassageiro.get();
    }

    throw new PassagerioNotFoundException("Cpf não encontrado");
  }
}
