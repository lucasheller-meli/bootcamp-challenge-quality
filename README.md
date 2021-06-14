# Bootcamp desafio quality

### Dependências

Gradle </br>
Java 11 </br>

### Requisistos mínimos
- Java 11
- Gradle


### Endpoint

- POST http://localhost:8080/propriedade/area


### Funcionalidades 

1. **US0001** - Calcule o total de metros quadrados de uma propriedade

  
2. **US0002** -  Indique o valor de uma propriedade com base em seus cômodos e medidas. Lembre-se que os preços por metro quadrado são determinados de acordo com a vizinhança.


3. **US0003** - Determine qual é o maior cômodo.
  
  
4. **US0004** - Determinar a quantidade de metros quadrados que tem cada cômodo de uma propriedade.


</br>


### Testes Unitários
</br>


> Situações de entrada

1.Verifique se o total de metros quadrados calculados por propriedade está correto.

2.Verifique se o bairro de entrada existe no repositório de bairros.

3.Verifique se a maior sala foi realmente devolvida.

4.Verifique se de fato o total de metros quadrados por cômodo está correto.

</br>


> Comportamento esperado

1.Retorna o cálculo correto do total de metros quadrados de uma propriedade.
2.Se cumprir:
Permite continuar normalmente.

Se não cumprir:
Relate a incompatibilidade com uma exceção.

3.Retorna a sala com o maior tamanho

4.Retorna o cálculo correto do total de metros quadrados de um cômodo.


</br>

### **Estrutura de dados de Entrada** 
```json
{
    "propNome": "Teste",
    "propBairro": "pandia",
    "comodos": [{
        "nome":"Sala",
        "largura": 3.0,
        "comprimento": 4.0
    },{
        "nome":"Cozinha",
        "largura": 25.0,
        "comprimento": 9.0
    }
    ]
}
```

</br>
</br>

### **Estrutura de dados de Retorno** 

```json
{
    "nomePropriedade": "Teste",
    "valorPropriedade": 82950.0,
    "maiorComodo": "Cozinha",
    "totalMetros": 237.0,
    "metroPorComodo": {
        "Sala": 12.0,
        "Cozinha": 225.0
    }
}
```
</br>


### **Bairros para testar** 

- liberdade
- olimpia
- eldizia
- caner
- navegantes
- pandia
