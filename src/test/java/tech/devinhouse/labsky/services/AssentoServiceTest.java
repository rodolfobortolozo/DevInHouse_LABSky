package tech.devinhouse.labsky.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devinhouse.labsky.models.Assento;
import tech.devinhouse.labsky.repositories.AssentoRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssentoServiceTest {

  @Mock
  private AssentoRepository assentoRepository;
  @InjectMocks
  private AssentoService assentoService;

  @Test
  @DisplayName("1 - Quando contem registros cadastrados, deve retornar lista com registros")
  void listarTodosPassageiros(){
    List<Assento> assentos = List.of(new Assento(1L,"1A",true,false,false));

    Mockito.when(assentoRepository.findAll()).thenReturn(assentos);
    List<Assento> listAssentos = assentoService.getAll();
    assertFalse(listAssentos.isEmpty());
    assertEquals(listAssentos.size(), listAssentos.size());

  }

  @Test
  @DisplayName("2 - Quando sem registros cadastrados, deve retornar lista vazia")
  void listarNenhumPassageiro() {
    List<Assento> passageiro = assentoService.getAll();
    assertTrue(passageiro.isEmpty());
  }

}