package com.desafioteste.teste.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Propriedade {
    private String nome;
    private String bairro;
    private List<Comodo> comodoList;
}
