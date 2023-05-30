package tech.devinhouse.labsky.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "ASSENTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assento {

  @Id
  @Column(name = "NROASSENTO")
  private String nroAssento;

  @Column(name = "DISPONIVEL")
  private boolean disponivel;

  @Column(name = "SAIDAEMERGENCIA")
  private boolean saidaEmergencia;

  @Column(name = "DESPACHEOBRIGATORIO")
  private boolean despacheObrigatorio;

}
