package com.desafioteste.desafioquality.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Comodo {

    @NotNull(message = "O campo com o nome do comodo não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    @Pattern(regexp = "[A-Z][a-z]+[[ ][A-Z][a-z]+]*", message = "O nome da propriedade deve começar com uma letra maiuscula!")
    private String nome;

    @NotNull(message = "O campo com a largura do comodo não pode estar vazio.")
    @DecimalMax(value = "25.0", message = "A largura máxima permitida por cômodo é de 25 metros")
    private double largura;

    @NotNull(message = "O campo com o comprimento do comodo não pode estar vazio.")
    @DecimalMax(value = "33.0", message = "O comprimento máximo permitido por cômodo é de 33 metros.\n")
    private double comprimento;

}
