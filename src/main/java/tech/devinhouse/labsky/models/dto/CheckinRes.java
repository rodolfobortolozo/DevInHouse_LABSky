package tech.devinhouse.labsky.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinRes {

    private UUID eticket;

    private Date dataHoraConfirmacao;

}
