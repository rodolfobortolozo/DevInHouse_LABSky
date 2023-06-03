package tech.devinhouse.labsky.controllers;

import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.devinhouse.labsky.models.dto.CheckinReq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CheckinControllerTest {

  @Autowired
  private MockMvc mockMvc;

  Gson gson = new Gson();

  @Test
  @DisplayName("1- Quando nao existe passageiro, deve retornar uma exception ")
  void passageiro_nao_existe() throws Exception {

    CheckinReq checkinReq = new CheckinReq("000.000.000-01","5B",true);

    String conteudo = gson.toJson(checkinReq);

    mockMvc.perform(post("/api/passageiros/confirmacao")
           .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("Cpf não encontrado."));
  }


  @Test
  @DisplayName("2- Quando nao existe assento, deve retornar uma exception ")
  void assento_nao_existe() throws Exception {

    CheckinReq checkinReq = new CheckinReq("000.000.000-00","15B",true);

    String conteudo = gson.toJson(checkinReq);

    mockMvc.perform(post("/api/passageiros/confirmacao")
           .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("Assento não encontrado."))
            .andExpect(jsonPath("$.objectName").value("AssentoNotFoundException"));
  }


  @Test
  @DisplayName("3- Quando assento estiver ocupado, deve retornar uma exception ")
  void assento_ocupado() throws Exception {

    CheckinReq checkinReq = new CheckinReq("111.111.111-11","10F",true);

    String conteudo = gson.toJson(checkinReq);

    mockMvc.perform(post("/api/passageiros/confirmacao")
           .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.message").value("Assento não disponível."))
            .andExpect(jsonPath("$.objectName").value("AssentoConflitException"));
  }

  @Test
  @DisplayName("4- Quando passageiro for menor de idade e assento for de emergencia, deve retornar uma exception ")
  void passageiro_menor_idade_assento_emergencia() throws Exception {

    CheckinReq checkinReq = new CheckinReq("000.000.000-09","5C",false);

    String conteudo = gson.toJson(checkinReq);

    mockMvc.perform(post("/api/passageiros/confirmacao")
           .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.objectName").value("AssentoException"))
            .andExpect(jsonPath("$.message").value("Passageiro Menor de idade para assento de emergência."));
  }

  @Test
  @DisplayName("5- Quando assento for de emergência e o passageiro tentar despachar a mala, deve retornar uma exception ")
  void assento_emergencia_despacha_mala() throws Exception {

    CheckinReq checkinReq = new CheckinReq("333.333.333-33","5B",false);

    String conteudo = gson.toJson(checkinReq);


    mockMvc.perform(post("/api/passageiros/confirmacao")
           .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.objectName").value("AssentoException"))
            .andExpect(jsonPath("$.message").value("Saída de emergência despache a mala."));
  }

  @Test
  @DisplayName("6 - Salvar Checkin e gerar Checkin")
  void salvar_chekin() throws Exception {

    CheckinReq checkinReq = new CheckinReq("555.555.555-55","1B",false);

    String conteudo = gson.toJson(checkinReq);

    mockMvc.perform(post("/api/passageiros/confirmacao")
           .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.eticket").isNotEmpty())
            .andExpect(jsonPath("$.dataHoraConfirmacao").isNotEmpty());
  }

}