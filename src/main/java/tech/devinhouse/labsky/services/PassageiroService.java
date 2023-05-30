package tech.devinhouse.labsky.services;

import org.springframework.stereotype.Service;
import tech.devinhouse.labsky.exceptions.PassagerioConflitException;
import tech.devinhouse.labsky.exceptions.PassagerioNotFoundException;
import tech.devinhouse.labsky.models.Classificacao;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.repositories.PassagerioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PassageiroService {

  private final PassagerioRepository passagerioRepository;

  public PassageiroService(PassagerioRepository passagerioRepository) {
    this.passagerioRepository = passagerioRepository;
  }

  public List<Passageiro> getAll(){
    return passagerioRepository.findAll();
  }

  public Passageiro getByCpf(String cpf){
    Optional<Passageiro> opPassageiro = passagerioRepository.findById(cpf);

    if(opPassageiro.isPresent()){
      return opPassageiro.get();
    }

    throw new PassagerioNotFoundException("Cpf não encontrado");
  }
}
