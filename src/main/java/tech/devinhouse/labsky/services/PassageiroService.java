package tech.devinhouse.labsky.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.devinhouse.labsky.exceptions.PassagerioNotFoundException;
import tech.devinhouse.labsky.models.Checkin;
import tech.devinhouse.labsky.models.Classificacao;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.models.dto.PassagerioResChekin;
import tech.devinhouse.labsky.repositories.CheckinRepository;
import tech.devinhouse.labsky.repositories.PassagerioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PassageiroService {

  private final PassagerioRepository passagerioRepository;
  private final CheckinRepository checkinRepository;

  public PassageiroService(PassagerioRepository passagerioRepository, CheckinRepository checkinRepository) {
    this.passagerioRepository = passagerioRepository;
    this.checkinRepository = checkinRepository;
  }

  public List<PassagerioResChekin> getAll(){
    log.info("Recuperando todos os passageiros...");
    List<PassagerioResChekin> listPassagerio = new ArrayList<>();

    passagerioRepository.findAll().forEach(passageiro -> {
              Checkin checkin = this.checkinRepository.findByPassageiroCpf(passageiro.getCpf());
              PassagerioResChekin passagerioResChekin = new PassagerioResChekin(passageiro, checkin);
              listPassagerio.add(passagerioResChekin);

            }
    );
    return listPassagerio;

  }

  public Passageiro getByCpf(String cpf){
    log.info("Recuperando passageiro por cpf...");
    Optional<Passageiro> opPassageiro = passagerioRepository.findByCpf(cpf);
    if(opPassageiro.isPresent()){
      return opPassageiro.get();
    }

    throw new PassagerioNotFoundException("Cpf não encontrado");
  }
}
