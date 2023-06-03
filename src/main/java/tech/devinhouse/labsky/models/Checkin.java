package tech.devinhouse.labsky.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CHECKINS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checkin {

    @Id
    @Column(name = "IDCHECHIN")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ETICKET")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eticket;

    @ManyToOne
    @JoinColumn(name = "IDPASSAGEIRO")
    private Passageiro passageiro;

    @ManyToOne
    @JoinColumn(name = "IDASSENTO")
    private Assento assento;

    @Column(name = "MALASDESPACHADAS")
    private Boolean malasDespachadas;

    @Column(name = "DATAHORACONFIRMACAO")
    private Date dataHoraConfirmacao;
}
