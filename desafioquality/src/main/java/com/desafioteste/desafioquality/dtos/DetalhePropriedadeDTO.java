package com.desafioteste.desafioquality.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalhePropriedadeDTO {

    private String nomePropriedade;

    private Double valorPropriedade;

    private String maiorComodo;

    private Double totalMetros;

    private Map<String, Double> metroPorComodo;

}
