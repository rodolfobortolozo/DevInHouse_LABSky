package tech.devinhouse.labsky.models.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CheckinRes {

    private UUID eticket;

    private Date dataHoraConfirmacao;

}
