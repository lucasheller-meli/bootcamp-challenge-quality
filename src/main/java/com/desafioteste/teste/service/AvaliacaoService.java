package com.desafioteste.teste.service;

import com.desafioteste.teste.dto.AnaliseDTO;
import com.desafioteste.teste.dto.PropriedadeDTO;
import com.desafioteste.teste.entities.Comodo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

        Double totalMetro = totalMetros(comodoList);
        Double valorPropriedade = valorPropriedade(bairro, totalMetro);

        return AnaliseDTO.builder()
                .nome(nomePropriedade)
                .totalMetros(totalMetro)
                .valorPropriedade(valorPropriedade)
                .maiorComodo(maiorComodo(comodoList))
                .metroPorComodo(metroPorComodo(comodoList))
                .build();

    }

    public Double totalMetros(List<Comodo> comodoList){
        double area = 0.0;

        for (Comodo comodo: comodoList) {
            Double largura = comodo.getLargura();
            Double comprimento = comodo.getComprimento();

            area += largura*comprimento;
        }
        return area;
    }

    public Map<String, Double> metroPorComodo(List<Comodo> comodoList){

        Map<String, Double> metroComodo = new HashMap<>();

        for (Comodo comodo: comodoList) {
            //criar um metodo disso
            Double comprimento = comodo.getComprimento();
            Double largura = comodo.getLargura();
            Double area = comprimento*largura;

            metroComodo.put(comodo.getNome(), area);
        }

        return metroComodo;
    }

    public String maiorComodo(List<Comodo> comodoList){
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

    public Double valorPropriedade(String bairro, Double totalMetro) {
        double valorPorMetro = 0.0;
        Map<String, Double> bairroCidade = getBairrosCidade();

        for (String key: bairroCidade.keySet()) {
            if (key.equals(bairro)) {
                valorPorMetro = bairroCidade.get(key);
                break;
            }
        }


        return totalMetro*valorPorMetro;
    }


}
