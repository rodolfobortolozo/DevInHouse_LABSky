package tech.devinhouse.labsky.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinReq {

    private String cpf;

    private String assento;

    private boolean malasDespachadas;

}
