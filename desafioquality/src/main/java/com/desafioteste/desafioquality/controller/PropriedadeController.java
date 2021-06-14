package com.desafioteste.desafioquality.controller;


import com.desafioteste.desafioquality.dtos.DetalhePropriedadeDTO;
import com.desafioteste.desafioquality.dtos.PropriedadeDTO;
import com.desafioteste.desafioquality.exception.BairroNaoExisteException;
import com.desafioteste.desafioquality.service.PropriedadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/propriedade")
@RequiredArgsConstructor
public class PropriedadeController {

    private final PropriedadeService propriedadeService;


    @PostMapping("/area")
    public ResponseEntity<DetalhePropriedadeDTO> calculaArea(@RequestBody @Valid PropriedadeDTO propriedadeDTO) throws BairroNaoExisteException {
        return ResponseEntity.ok(propriedadeService.calcularAreaPropriedade(propriedadeDTO));
    }
}

