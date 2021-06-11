package com.desafioteste.teste.service;

import com.desafioteste.teste.dto.AnaliseDTO;
import com.desafioteste.teste.dto.PropriedadeDTO;
import com.desafioteste.teste.entities.Comodo;
import com.desafioteste.teste.exceptions.DistrictNotFound;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AvaliacaoService {

    private static final Map<String, Double> bairrosCidade = new HashMap<>();

    public AvaliacaoService(){}

    private static Map<String, Double> getBairrosCidade() {
        bairrosCidade.put("catole", 500.00);
        bairrosCidade.put("alto branco", 700.00);
        bairrosCidade.put("malvinas", 200.00);
        bairrosCidade.put("liberdade", 250.00);
        bairrosCidade.put("mirante", 700.00);
        bairrosCidade.put("centro", 350.00);
        return bairrosCidade;
    }

    public AnaliseDTO analise(PropriedadeDTO propriedadeDTO){
        String nomePropriedade = propriedadeDTO.getNome();
        String bairro = propriedadeDTO.getBairro();
        List<Comodo> comodoList = propriedadeDTO.getComodos();

        Double totalMetro = totalMetrosMetodo(comodoList);
        Double valorPropriedade = valorPropriedade(bairro, totalMetro);
        Map<String, Double> metroComodo = metroPorComodo(comodoList);


        return AnaliseDTO.builder()
                .nome(nomePropriedade)
                .totalMetros(totalMetro)
                .valorPropriedade(valorPropriedade)
                .maiorComodo(maiorComodo(metroComodo))
                .metroPorComodo(metroPorComodo(comodoList))
                .build();
    }

    private Double totalMetrosMetodo(List<Comodo> comodoList){
        double totalMetros = 0.0;

        for (Comodo comodo : comodoList) {
            totalMetros += calculaArea(comodo.getComprimento(), comodo.getLargura());
        }
        return totalMetros;
    }

    private Double calculaArea(Double comprimento, Double largura){ return  largura*comprimento;}

    private Map<String, Double> metroPorComodo(List<Comodo> comodoList){
        Map<String, Double> metroComodo = new HashMap<>();

        for (Comodo comodo : comodoList) {
            Double area =  calculaArea(comodo.getComprimento(), comodo.getLargura());;
            metroComodo.put(comodo.getNome(), area);
        }

        return metroComodo;
    }

    private String maiorComodo(Map<String, Double> metroComodo ){
        Map.Entry<String, Double> maxComodo = null;

        for (Map.Entry<String, Double> entry: metroComodo.entrySet()) {
            if (maxComodo == null || entry.getValue().compareTo(maxComodo.getValue()) > 0){
                maxComodo = entry;
            }
        }

        assert maxComodo != null;
        return maxComodo.getKey();
    }

    private Double valorPropriedade(String bairro, Double totalMetro) throws DistrictNotFound {
        Map<String, Double> bairroCidade = getBairrosCidade();

        if(!bairroCidade.containsKey(bairro)) throw new DistrictNotFound();
        Double valorPorMetro = bairroCidade.get(bairro);

        return totalMetro*valorPorMetro;
    }


}
