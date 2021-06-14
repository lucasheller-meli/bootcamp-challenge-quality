package com.desafioteste.desafioquality.service;

import com.desafioteste.desafioquality.dtos.DetalhePropriedadeDTO;
import com.desafioteste.desafioquality.dtos.PropriedadeDTO;
import com.desafioteste.desafioquality.entities.Comodo;
import com.desafioteste.desafioquality.exception.BairroNaoExisteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class PropriedadeServiceTest {
    @InjectMocks
    private PropriedadeService propriedadeService;

    @Test
    void QuandoCalcularAreaDaPropriedadeForChamadoComParametrosValidosRetornaTotalDeMetros(){
        DetalhePropriedadeDTO detalhePropriedade = propriedadeService.calcularAreaPropriedade(PropriedadeDTO.builder()
                .propNome("Recanto dos Passarinhos")
                .propBairro("pandia")
                .comodos(List.of(
                        Comodo.builder()
                        .nome("sala")
                        .largura(25.0)
                        .comprimento(30.0)
                        .build(),
                        Comodo.builder()
                                .nome("cozinha")
                                .largura(5.0)
                                .comprimento(33.0)
                                .build()))
                .build());

        assertEquals( 915.0, detalhePropriedade.getTotalMetros());
    }

    @Test
    void QuandoCalcularAreaDaPropriedadeForChamadaComParametrosValidosRetornaMaiorComodoDaPropriedade(){
        DetalhePropriedadeDTO detalhePropriedade = propriedadeService.calcularAreaPropriedade(PropriedadeDTO.builder()
                .comodos(List.of(
                        Comodo.builder()
                                .nome("sala")
                                .largura(25.0)
                                .comprimento(30.0)
                                .build(),
                        Comodo.builder()
                                .nome("cozinha")
                                .largura(5.0)
                                .comprimento(33.0)
                                .build()))
                .build());

        assertEquals("sala", detalhePropriedade.getMaiorComodo());
    }

    @Test
    void QuandoCalcularAreaDaPropriedadeForChamadaComParametrosValidosRetornaMetroDeCadaComodo(){

        Map<String, Double> metroComodoExpect = new HashMap<>();
        metroComodoExpect.put("sala", 750.0);
        metroComodoExpect.put("quarto", 165.0);

        DetalhePropriedadeDTO detalhePropriedade = propriedadeService.calcularAreaPropriedade(PropriedadeDTO.builder()
                .comodos(List.of(
                        Comodo.builder()
                                .nome("sala")
                                .largura(25.0)
                                .comprimento(30.0)
                                .build(),
                        Comodo.builder()
                                .nome("quarto")
                                .largura(5.0)
                                .comprimento(33.0)
                                .build()))
                .build());

        assertEquals(metroComodoExpect, detalhePropriedade.getMetroPorComodo());
    }

    @Test
    void QuandoCalcularAreaDaPropriedadeForChamadaComBairroQueNaoExisteEleRetornaUmErro(){

        Exception exception = assertThrows(BairroNaoExisteException.class, () ->
            propriedadeService.calcularAreaPropriedade(PropriedadeDTO.builder()
                    .propBairro("pand")
                    .build())
        );

        assertEquals("Por favor inserir um bairro valido", exception.getMessage());
    }




}
