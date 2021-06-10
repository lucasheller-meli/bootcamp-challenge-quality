package com.desafioteste.teste.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comodo {
    @Size(min = 1, max = 30, message = "Numero invalido de caractere!")
    private String nome;
    private Double comprimento;
    private Double largura;
}
