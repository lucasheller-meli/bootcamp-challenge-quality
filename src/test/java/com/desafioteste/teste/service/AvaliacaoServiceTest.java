package com.desafioteste.teste.service;

import com.desafioteste.teste.dto.AnaliseDTO;
import com.desafioteste.teste.dto.PropriedadeDTO;
import com.desafioteste.teste.entities.Comodo;
import com.desafioteste.teste.exceptions.DistrictNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoServiceTest {

    private List<Comodo> listComodo;
    private final AvaliacaoService avaliacaoService = new AvaliacaoService();
    private AnaliseDTO analiseTeste;
    private PropriedadeDTO prop;

    @BeforeEach
    public void setUp(){
        Comodo com1 = Comodo.builder().nome("Sala").comprimento(2.0).largura(2.0).build();
        Comodo com2 = Comodo.builder().nome("Quarto").comprimento(3.0).largura(3.0).build();
        listComodo = new ArrayList<>();
        listComodo.add(com1);
        listComodo.add(com2);

        prop = PropriedadeDTO.builder()
                .nome("Casa Moderna")
                .bairro("catole")
                .comodos(listComodo).build();

    }

    @Test
    void shouldReturnTotalMetros() {
        //act
        analiseTeste = avaliacaoService.analise(prop);

        Double totalMetro = analiseTeste.getTotalMetros();

        //assert
        assertEquals(13.0, totalMetro);
    }

    @Test
    void shouldReturnMetroPorComodo() {
        //arrange
        Map<String, Double> metroComodoExpect = new HashMap<>();
        metroComodoExpect.put("sala", 4.0);
        metroComodoExpect.put("quarto", 9.0);

        analiseTeste = avaliacaoService.analise(prop);

        //act
        Map<String, Double> metroComodo = analiseTeste.getMetroPorComodo();

        //assert
        assertEquals(metroComodoExpect, metroComodo);
    }

    @Test
    void shouldReturnMaiorComodoTeste() {
        //act
        analiseTeste = avaliacaoService.analise(prop);
        String maiorComodo = analiseTeste.getMaiorComodo();

        //assert
        assertEquals("quarto", maiorComodo);
    }

    @Test
    void shouldVerifyExceptionDistrictNotFound() {
        //arrange
        String exp = "Bairro não encontrado!";
        prop.setBairro("Bairro nao encontrado");

        //act
        Exception exception = assertThrows(DistrictNotFound.class, () -> {avaliacaoService.analise(prop);});

        //assert
        assertEquals(exception.getMessage(), exp);
    }

    @Test
    void shouldReturnValorPropriedade(){
        //act
        analiseTeste = avaliacaoService.analise(prop);
        Double valorPropriedade = analiseTeste.getValorPropriedade();

        //assert
        assertEquals(6500.0, valorPropriedade);
    }
}