package com.desafioteste.teste.entities;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class Comodo {
    @Size(min = 1, max = 30, message = "O comprimento do nome do comodo não pode exceder 30 caracteres!")
    @NotBlank(message = "O campo nao pode estar vazio!")
    @Pattern(regexp = "[A-Z][a-z]+[[ ][A-Z][a-z]+]*", message = "O nome do comodo deve se iniciar com letra maiúscula!")
    private String nome;
    @NotNull(message = "O comprimento do comodo não pode estar vazio.")
    @DecimalMax(value = "33", message = "O Comprimento máximo permitido por comodo é de 33 metros!")
    private Double comprimento;
    @NotNull
    @DecimalMax(value = "25", message = "A Largura máxima permitido por comodo é de 25 metros!")
    private Double largura;
}
