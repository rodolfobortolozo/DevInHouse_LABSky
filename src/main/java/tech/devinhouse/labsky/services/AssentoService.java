package tech.devinhouse.labsky.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.devinhouse.labsky.models.Assento;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.repositories.AssentoRepository;

import java.util.List;

@Slf4j
@Service
public class AssentoService {

    public final AssentoRepository assentoRepository;

    public AssentoService(AssentoRepository assentoRepository) {
        this.assentoRepository = assentoRepository;
    }

    public List<Assento> getAll(){
        log.info("Recuperar todos os acentos...");
        return assentoRepository.findAll();
    }
}
