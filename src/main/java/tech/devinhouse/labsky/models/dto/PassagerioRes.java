package tech.devinhouse.labsky.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tech.devinhouse.labsky.models.Checkin;
import tech.devinhouse.labsky.models.Classificacao;
import tech.devinhouse.labsky.models.Passageiro;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
public class PassagerioRes {

  private String cpf;

  private String nome;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataNascimento;

  private Classificacao classificacao;

  private Integer milhas;

  private UUID eticket;

  private String assento;

  private Date dataHoraConfirmacao;

  public PassagerioRes(Passageiro passagerio, Checkin checkin){
    this.cpf = passagerio.getCpf();
    this.nome = passagerio.getNome();
    this.dataNascimento = passagerio.getDataNascimento();
    this.classificacao = passagerio.getClassificacao();
    this.milhas = passagerio.getMilhas();
    if(checkin!=null){
      this.eticket = checkin.getEticket();
      this.assento = checkin.getAssento().getNroAssento();
      this.dataHoraConfirmacao = checkin.getDataHoraConfirmacao();
    }

  }

}
