package tech.devinhouse.labsky.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labsky.mappers.ObjectMapperUtils;
import tech.devinhouse.labsky.models.Assento;
import tech.devinhouse.labsky.models.dto.AssentoRes;
import tech.devinhouse.labsky.services.AssentoService;

import java.util.List;

@RestController
@RequestMapping("/api/assentos")
public class AssentoController {

    private final AssentoService assentoService;

    public AssentoController(AssentoService assentoService) {
        this.assentoService = assentoService;
    }

    @GetMapping
    public ResponseEntity<List<AssentoRes>> getAll(){
        List<Assento> assentos = assentoService.getAll();
        List<AssentoRes> assentoRes =  ObjectMapperUtils.mapAll(assentos, AssentoRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(assentoRes);

    }
}
