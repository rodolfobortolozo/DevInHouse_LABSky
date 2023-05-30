package tech.devinhouse.labsky.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssentoReq {

  private String nroAssento;

  private boolean disponivel;

  private boolean saidaEmergencia;

  private boolean despacheObrigatorio;

}
