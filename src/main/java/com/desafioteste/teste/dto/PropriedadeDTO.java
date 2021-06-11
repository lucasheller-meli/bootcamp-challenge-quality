package com.desafioteste.teste.dto;

import com.desafioteste.teste.entities.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropriedadeDTO {
    @NotBlank(message = "O nome da propriedade não pode estar vazio!")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres!")
    @Pattern(regexp = "[A-Z][a-z]+[[ ][A-Z][a-z]+]*", message = "O nome da propriedade deve começar com uma letra maiúscula!")
    private String nome;
    @NotBlank(message = "O bairro não pode estar vazio!")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres!")
    private String bairro;
    @Valid
    @NotEmpty
    private List<Comodo> comodos;
}
