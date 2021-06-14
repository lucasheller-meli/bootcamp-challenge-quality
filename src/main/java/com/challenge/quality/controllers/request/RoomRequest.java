package com.challenge.quality.controllers.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class RoomRequest {

    @NotBlank(message = "O campo não pode estar vazio.")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]*$",message = "O nome do cômodo deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O nome do cômodo não pode exceder {max} caracteres.")
    private String name;

    @NotNull(message = "A largura do cômodo não pode estar vazia.")
    @DecimalMax(value = "25", message = "A largura máxima permitida por cômodo é de 25 metros")
    @DecimalMin(value = "0.1", message = "A largura minima permitida por cômodo é de 0.1 metros")
    private Float width;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio.")
    @DecimalMax(value = "33", message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    @DecimalMin(value = "0.1", message = "O comprimento minimo permitido por cômodo é de 0.1 metros.\n")
    private Float length;


}
