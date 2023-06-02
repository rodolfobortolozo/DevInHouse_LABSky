package tech.devinhouse.labsky.controllers;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CheckinControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("1- Quando nao existe passageiro, deve retornar uma exception ")
  void passageiro_nao_existe() throws Exception {

    String conteudo = "{\n" +
            "    \"cpf\": \"000.000.000-01\",\n" +
            "    \"assento\": \"5B\",\n" +
            "    \"malasDespachadas\": true\n" +
            "}";

    mockMvc.perform(post("/api/passageiros/confirmacao")
                    .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isNotFound());
  }


  @Test
  @DisplayName("2- Quando nao existe assento, deve retornar uma exception ")
  void assento_nao_existe() throws Exception {

    String conteudo = "{\n" +
                      "    \"cpf\": \"000.000.000-00\",\n" +
                      "    \"assento\": \"15B\",\n" +
                      "    \"malasDespachadas\": true\n" +
                      "}";

    mockMvc.perform(post("/api/passageiros/confirmacao")
                    .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isNotFound());
  }


  @Test
  @DisplayName("3- Quando assento estiver ocupado, deve retornar uma exception ")
  void assento_ocupado() throws Exception {

    String conteudo = "{\n" +
            "    \"cpf\": \"111.111.111-11\",\n" +
            "    \"assento\": \"10F\",\n" +
            "    \"malasDespachadas\": true\n" +
            "}";

    mockMvc.perform(post("/api/passageiros/confirmacao")
                    .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isConflict());
  }

  @Test
  @DisplayName("4- Quando passageiro for menor de idade e assento for de emergencia, deve retornar uma exception ")
  void passageiro_menor_idade_assento_emergencia() throws Exception {

    String conteudo = "{\n" +
            "    \"cpf\": \"000.000.000-09\",\n" +
            "    \"assento\": \"5C\",\n" +
            "    \"malasDespachadas\": false\n" +
            "}";

    mockMvc.perform(post("/api/passageiros/confirmacao")
                    .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("5- Quando assento for de emergência e o passageiro tentar despachar a mala, deve retornar uma exception ")
  void assento_emergencia_despacha_mala() throws Exception {

    String conteudo = "{\n" +
            "    \"cpf\": \"333.333.333-33\",\n" +
            "    \"assento\": \"5B\",\n" +
            "    \"malasDespachadas\": false\n" +
            "}";

    mockMvc.perform(post("/api/passageiros/confirmacao")
                    .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("6 - Salvar Checkin e gerar Checkin")
  void salvar_chekin() throws Exception {

    String conteudo = "{\n" +
            "    \"cpf\": \"555.555.555-55\",\n" +
            "    \"assento\": \"1B\",\n" +
            "    \"malasDespachadas\": false\n" +
            "}";

    mockMvc.perform(post("/api/passageiros/confirmacao")
                    .contentType(MediaType.APPLICATION_JSON).content(conteudo))
            .andExpect(status().isCreated());
  }

}