package tech.devinhouse.labsky.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CHECKINS")
@Data
public class Checkin {

    @Id
    @Column(name = "ETICKET")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eticket;

    @ManyToOne
    @JoinColumn(name = "CPF")
    private Passageiro passageiro;

    @ManyToOne
    @JoinColumn(name = "NROASSENTO")
    private Assento assento;

    @Column(name = "MALASDESPACHADAS")
    private Boolean malasDespachadas;

    @Column(name = "DATAHORACONFIRMACAO")
    private Date dataHoraConfirmacao;
}
