package com.desafioteste.teste.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnaliseDTO {
    private String nome;
    private Double totalMetros;
    private Double valorPropriedade;
    private String maiorComodo;
    private Map<String, Double> metroPorComodo;
}
