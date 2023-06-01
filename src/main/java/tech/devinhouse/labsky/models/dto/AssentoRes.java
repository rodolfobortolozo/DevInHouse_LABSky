package tech.devinhouse.labsky.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssentoRes {
  private String nroAssento;

  private boolean disponivel;

  private boolean saidaEmergencia;

  private boolean despacheObrigatorio;
}
