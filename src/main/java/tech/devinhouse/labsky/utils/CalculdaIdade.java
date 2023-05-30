package tech.devinhouse.labsky.utils;

import java.time.LocalDate;
import java.time.Period;

public class CalculdaIdade {

    public static int idade(LocalDate aniversario) {
        return Period.between(aniversario, LocalDate.now()).getYears();
    }
}
