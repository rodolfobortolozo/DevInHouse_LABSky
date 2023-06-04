package tech.devinhouse.labsky.utils;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class CalculdaIdadeTest {

  @Test
  @DisplayName("Calcula a idade da pessoa")
  public void calculaIdade(){
    LocalDate dtaNascimento = LocalDate.of(1989, Month.APRIL,3);

    int idade = CalculdaIdade.idade(dtaNascimento);

    assertEquals(34, idade);

  }


}