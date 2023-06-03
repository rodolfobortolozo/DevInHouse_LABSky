package tech.devinhouse.labsky.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssentoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("1 - Quando tem registros, deve retornar lista com os assentos")
  void consultar() throws Exception {

    mockMvc.perform(get("/api/assentos")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(60)))
            .andExpect(jsonPath("$[0].nroAssento").value("1A"))
            .andExpect(jsonPath("$[59].nroAssento").value("10F"));
  }

}