package tech.devinhouse.labsky.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labsky.mappers.ObjectMapperUtils;
import tech.devinhouse.labsky.models.Assento;
import tech.devinhouse.labsky.models.Checkin;
import tech.devinhouse.labsky.models.Passageiro;
import tech.devinhouse.labsky.models.dto.CheckinReq;
import tech.devinhouse.labsky.models.dto.CheckinRes;
import tech.devinhouse.labsky.services.AssentoService;
import tech.devinhouse.labsky.services.CheckinService;
import tech.devinhouse.labsky.services.PassageiroService;

@RestController
@RequestMapping("/api/passageiros")
public class CheckinController {

    private final PassageiroService passageiroService;
    private final AssentoService assentoService;

    private final CheckinService checkinService;

    public CheckinController(PassageiroService passageiroService, AssentoService assentoService, CheckinService checkinService) {
        this.passageiroService = passageiroService;
        this.assentoService = assentoService;
        this.checkinService = checkinService;
    }

    @PostMapping(path = "/confirmacao")
    public ResponseEntity<CheckinRes> save(@RequestBody CheckinReq checkinReq){

        CheckinRes checkinRes = ObjectMapperUtils.map(checkinService.save(checkinReq),CheckinRes.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(checkinRes);
    }
}
