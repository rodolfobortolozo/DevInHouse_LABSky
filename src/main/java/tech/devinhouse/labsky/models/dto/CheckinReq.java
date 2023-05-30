package tech.devinhouse.labsky.models.dto;

import lombok.Data;

@Data
public class CheckinReq {

    private String cpf;

    private String assento;

    private boolean malasDespachadas;

}
