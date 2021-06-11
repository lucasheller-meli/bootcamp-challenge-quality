# Bootcamp Challenge Testing
______

## Dependências

Maven 3.8.1

Java 11.0

Lombok 

Spring Boot Start Validation

Spring Boot Start Web

Spring Boot Start Test

## Teste da API

Para realizar o teste da API deve utilizar o Postman com a seguinte coleção:

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1539a5957e078216d08a)

O end-point do programa receberá um arquivo contendo o nome da propriedade, 
o bairro que a mesma esta situada e a lista de comodos que a mesma contem, 
representados por nome, largura e comprimento. 

A API retorna o relatorio da analise da propriedade, no qual consta a 
área total do imovel, o valor monetário da propriedade, 
o maior comodo que ela possui e a lista de comodos e suas respectivas áreas.

## Teste unitário

Foi desenvolvido o switch de teste para a aplicação, no qual verifica se os métodos 
do Service estão funcionando como o esperando. O mesmo pode ser vizualizado no arquivo 
titulado como AvaliacaoServiceTest na pasta Test.