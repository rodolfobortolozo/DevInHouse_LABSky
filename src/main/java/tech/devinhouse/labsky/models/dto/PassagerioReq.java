package tech.devinhouse.labsky.models.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PassagerioReq {

  private String cpf;

  private String nome;

  @JsonFormat(pattern="dd/MM/yyyy")
  private LocalDate dataNascimento;


}
