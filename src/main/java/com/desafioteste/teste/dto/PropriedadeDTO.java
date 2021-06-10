package com.desafioteste.teste.dto;

import com.desafioteste.teste.entities.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropriedadeDTO {
    @Size(min = 1, max = 30, message = "Numero de caractere invalido!")
    private String nome;
    @Size(min = 1, max = 45, message = "Numero de caractere invalido!")
    private String bairro;
    @Valid
    @NotEmpty
    private List<Comodo> comodos;
}
