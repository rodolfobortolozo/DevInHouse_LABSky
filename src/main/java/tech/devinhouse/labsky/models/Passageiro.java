package tech.devinhouse.labsky.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "PASSAGEIROS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passageiro {

  @Id
  @Column(name = "CPF")
  private String cpf;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "DTANASCIMENTO")
  private LocalDate dataNascimento;

  @Column(name = "CLASSIFICACAO")
  @Enumerated(EnumType.STRING)
  private Classificacao classificacao;

  @Column(name = "MINHAS")
  private Integer milhas;

}
