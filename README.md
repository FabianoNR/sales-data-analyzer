# Relatório de Análide de Vendas

Foi desenvolvido um sistema de análise de dados de venda que irá importar lotes de arquivos e produzir um relatório baseado em informações presentes no mesmo. Existem 3 tipos de dados dentro dos arquivos e eles podem ser distinguidos pelo seu identificador que estará presente na primeira coluna de cada linha, onde o separador de colunas é o caractere **ç**.

O sistema lerá continuamente todos os arquivos dentro do diretório padrão HOMEPATH/data/in e colocar o arquivo de saída 
em HOMEPATH/data/out.

**No arquivo de saída** o sistema gerará os seguintes dados:
 - Quantidade de clientes no arquivo de entrada
 - Quantidade de vendedores no arquivo de entrada
 - ID da venda mais cara
 - O pior vendedor
 
 **No arquivo de entrada** o sistema reconhecerá as seguinte estruturas:

#### Dados do vendedor
Os dados do vendedor possuem o identificador 001 e seguem o seguinte formato:
 - 001çCPFçNameçSalary

#### Dados do cliente
Os dados do cliente possuem o identificador 002 e seguem o seguinte formato:
 - 002çCNPJçNameçBusiness Area

#### Dados de venda
Os dados de venda possuem o identificador 003 e seguem o seguinte formato:
 - 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
 
## Tecnologias utilizadas
- Java 8
- SpringBoot
- Junit
- Gradle

## Pré requisitos para rodar aplicação
- JRE 8 instalado na máquina

## Para rodar aplicação
Bata acessar o diretório **/analyzer** e digitar via terminal executar o comando:
```
./gradlew run
```

