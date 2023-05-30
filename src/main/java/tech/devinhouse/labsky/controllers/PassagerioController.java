package tech.devinhouse.labsky.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.labsky.mappers.ObjectMapperUtils;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.models.dto.PassagerioReq;
import tech.devinhouse.labsky.models.dto.PassagerioRes;
import tech.devinhouse.labsky.services.PassageiroService;

import java.util.List;

@RestController
@RequestMapping("/api/passageiros")
public class PassagerioController {

  private final PassageiroService passageiroService;
  ModelMapper modelMapper = new ModelMapper();

  public PassagerioController(PassageiroService passageiroService) {
    this.passageiroService = passageiroService;
  }

  @GetMapping
  public ResponseEntity<List<PassagerioRes>> getAll(){
    List<Passageiro> passageiros = passageiroService.getAll();
    List<PassagerioRes> passagerioRes =  ObjectMapperUtils.mapAll(passageiros, PassagerioRes.class);

    return ResponseEntity.status(HttpStatus.OK).body(passagerioRes);

  }

  @GetMapping(path = "/{cpf}")
  public ResponseEntity<PassagerioRes> getByCpf(@PathVariable(name = "cpf") String cpf){
    Passageiro newPassageiro = passageiroService.getByCpf(cpf);

    return ResponseEntity.status(HttpStatus.OK).body(ObjectMapperUtils.map(newPassageiro, PassagerioRes.class));

  }
}
