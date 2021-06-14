package com.desafioteste.desafioquality.dtos;

import com.desafioteste.desafioquality.entities.Comodo;



import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PropriedadeDTO {

    @NotNull(message = "O nome da propriedade não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    @Pattern(regexp = "[A-Z][a-z]+[[ ][A-Z][a-z]+]*", message = "O nome da propriedade deve começar com uma letra maiuscula!")
    private String propNome;

    @NotNull(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String propBairro;

    @Valid
    private List<Comodo> comodos;
}
