package tech.devinhouse.labsky.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date dataHoraConfirmacao;

}
