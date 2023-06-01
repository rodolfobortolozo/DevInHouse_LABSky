package tech.devinhouse.labsky.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.devinhouse.labsky.models.Classificacao;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassagerioRes {

  private String cpf;

  private String nome;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataNascimento;

  private Classificacao classificacao;

  private Integer milhas;

}
