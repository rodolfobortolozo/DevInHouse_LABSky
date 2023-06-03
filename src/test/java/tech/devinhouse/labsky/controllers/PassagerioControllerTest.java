package tech.devinhouse.labsky.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tech.devinhouse.labsky.models.Classificacao;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.repositories.CheckinRepository;
import tech.devinhouse.labsky.repositories.PassagerioRepository;
import tech.devinhouse.labsky.services.AssentoService;
import tech.devinhouse.labsky.services.CheckinService;
import tech.devinhouse.labsky.services.PassageiroService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PassagerioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("1 - Quando tem registros, deve retornar lista com os passageiros")
    void consultar() throws Exception {

        mockMvc.perform(get("/api/passageiros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(11)))
                .andExpect(jsonPath("$[0].nome").value("Rachel Green"));
    }

    @Test
    @DisplayName("2 - Quando consultar por cpf e ter registro, deve retornar o passageiro")
    void consultarPorCpf() throws Exception {

        mockMvc.perform(get("/api/passageiros/111.111.111-11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Phoebe Buffay"))
                .andExpect(jsonPath("$.cpf").value("111.111.111-11"));
    }

    @Test
    @DisplayName("2 - Quando consultar por cpf e não encontrar, deve retornar uma exception")
    void consultarPorCpfNaoEncontrado() throws Exception {

        mockMvc.perform(get("/api/passageiros/111.111.111-12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.objectName").value("PassagerioNotFoundException"))
                .andExpect(jsonPath("$.message").value("Cpf não encontrado"));
    }
}