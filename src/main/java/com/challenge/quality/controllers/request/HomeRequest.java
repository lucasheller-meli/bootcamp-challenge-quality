package com.challenge.quality.controllers.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Builder
public class HomeRequest{

    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]*$",message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder {max} caracteres.")
    private String name;

    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String neighborhood;

    @Valid
    @NotEmpty(message = "A casa precisa ter no minimo 1 comodo")
    private List<RoomRequest> rooms;
}
