package com.desafioteste.desafioquality.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Propriedade {
    private String propNome;
    private String propBairro;
    private List<Comodo> comodos;
}
