package com.desafioteste.teste.controller;

import com.desafioteste.teste.dto.AnaliseDTO;
import com.desafioteste.teste.dto.PropriedadeDTO;
import com.desafioteste.teste.service.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propriedade")
public class AvalicaoCasaController {

    private final AvaliacaoService avaliacaoService;

    public AvalicaoCasaController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("/avaliacao")
    public ResponseEntity<AnaliseDTO> avaliacao(@RequestBody PropriedadeDTO propriedadeDTO){
        return ResponseEntity.ok(avaliacaoService.analise(propriedadeDTO));
    }
}
