package com.desafioteste.desafioquality.service;

import com.desafioteste.desafioquality.dtos.DetalhePropriedadeDTO;
import com.desafioteste.desafioquality.dtos.PropriedadeDTO;
import com.desafioteste.desafioquality.entities.Comodo;
import com.desafioteste.desafioquality.exception.BairroNaoExisteException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropriedadeService {

    private PropriedadeDTO propriedadeDTO;
    private DetalhePropriedadeDTO detalhePropriedadeDTO;

    private static final Map<String, Double> bairrosCidade = new HashMap<>(){{
        put("liberdade", 500.00);
        put("olimpia", 700.00);
        put("eldizia", 200.00);
        put("caner", 250.00);
        put("navegantes", 700.00);
        put("pandia", 350.00);
    }};


    public DetalhePropriedadeDTO calcularAreaPropriedade(PropriedadeDTO propriedadeDTO)  throws BairroNaoExisteException {
        String nomePropriedade = propriedadeDTO.getPropNome();
        String bairro = propriedadeDTO.getPropBairro();
        List<Comodo> comodoList = propriedadeDTO.getComodos();

        if(!bairrosCidade.containsKey(bairro)) throw new BairroNaoExisteException("Por favor inserir um bairro valido");

        Double totalMetrosVariavel = totalMetros(comodoList);
        Double valorPropriedade = calcularValorPropriedade(bairro, totalMetrosVariavel);


        return DetalhePropriedadeDTO.builder()
                .nomePropriedade(nomePropriedade)
                .totalMetros(totalMetrosVariavel)
                .valorPropriedade(valorPropriedade)
                .maiorComodo(obterMaiorComodo(comodoList))
                .metroPorComodo(metroPorComodo(comodoList))
                .build();
    }
    //    US0001

    private Double totalMetros(List<Comodo> comodoList){
         double area = 0.0;

         for(Comodo comodo: comodoList){
             Double largura = comodo.getLargura();
             Double comprimento = comodo.getComprimento();

             area += largura*comprimento;
         }
         return area;
    }

   //    US0002

    private Double calcularValorPropriedade(String bairro, Double totalMetro){
        double valorPorMetro = bairrosCidade.get(bairro);

        return totalMetro*valorPorMetro;
    }


//    US0003
    private String obterMaiorComodo(List<Comodo> comodoList){
        String maiorComodo;
        Map<String, Double> metroComodo = metroPorComodo(comodoList);

        Map.Entry<String, Double> maxComodo = null;

        for (Map.Entry<String, Double> entry: metroComodo.entrySet()) {
            if (maxComodo == null || entry.getValue().compareTo(maxComodo.getValue()) > 0){
                maxComodo = entry;
            }
        }

        assert maxComodo != null;
        maiorComodo = maxComodo.getKey();

        return maiorComodo;
    }

//    US0004

    private Map<String, Double> metroPorComodo(List<Comodo> comodoList){
        Map<String, Double> metroCadaComodo = new HashMap<>();

        for (Comodo comodo: comodoList) {
            //criar um metodo disso
            Double comprimento = comodo.getComprimento();
            Double largura = comodo.getLargura();
            Double area = comprimento*largura;

            metroCadaComodo.put(comodo.getNome(), area);
        }

        return metroCadaComodo;
    }
}
