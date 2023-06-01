package tech.devinhouse.labsky.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labsky.mappers.ObjectMapperUtils;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.models.dto.PassagerioRes;
import tech.devinhouse.labsky.models.dto.PassagerioResChekin;
import tech.devinhouse.labsky.services.PassageiroService;

import java.util.List;

@RestController
@RequestMapping("/api/passageiros")
public class PassagerioController {

  private final PassageiroService passageiroService;

  public PassagerioController(PassageiroService passageiroService) {
    this.passageiroService = passageiroService;
  }

  @GetMapping
  public ResponseEntity<List<PassagerioResChekin>> getAll(){

     return ResponseEntity.status(HttpStatus.OK).body(passageiroService.getAll());

  }

  @GetMapping(path = "/{cpf}")
  public ResponseEntity<PassagerioRes> getByCpf(@PathVariable(name = "cpf") String cpf){
    Passageiro newPassageiro = passageiroService.getByCpf(cpf);

    return ResponseEntity.status(HttpStatus.OK).body(ObjectMapperUtils.map(newPassageiro, PassagerioRes.class));

  }
}
