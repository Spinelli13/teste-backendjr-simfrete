# 📦 Projeto: Teste Prático BackEnd JR.

Este projeto consiste em dois programas escritos em Java que realizam as seguintes funcionalidades:

1. **Parte 1 - BuscaCidade**: Localiza a cidade correspondente a um CEP informado, com base em faixas de CEPs.
2. **Parte 2 - CalculaPreco**: Calcula a rota mais barata de envio entre duas cidades, com base no CEP de origem e destino e a rota a ser seguida com seus custos.

## 🧠 Funcionalidades

### Parte 1 – `BuscaCidade`
- Localiza a cidade correspondente a um CEP informado em um arquivo .txt. 
- A pesquisa é realizada utilizando faixas de CEP informados em um arquivo .txt.

### Parte 2 – `CalculaPreco`
- Calcula a rota mais barata de envio entre duas cidades, considerando as rotas e custos entre elas.
- Todas informações são inseridas em um arquivo .txt.
---

## 🔹 Estrutura do Projeto
[![](https://mermaid.ink/img/pako:eNpVkE1vgkAQhv8KmTOuoFRYDk0UbHsxMW1PBdNM2BGxwNp112qN_11EbOqcZp73nY_METIpCEJYlvInW6HS1nuc1lYT42Su5Jq0XFi93qM1SeaNSu6iU1sYXeHgDsYJqm9T7OSnpq0mpvf6Tp8mLC90kddS0Z3wlLxOx_FsyirR8UnLn7vV_YnZZhgVAgWxNe6wc0Wt66W7pR9hmZkS54oyebOBDbkqBIRaGbKhIlXhpYTjZUQKekUVpRA2qUD1lUJan5qeDdYfUla3NiVNvoJwieW2qcxGoKa4wFxh9UfRaPl2qLNbD4lCSzW7frl9djOHakEqkqbWELpeuwfCI-wh5JwNPR64Qz8I-IM_4jYcGo_jM2_oe5y7njNyBtw72fDbXuaw4ML_xekMqveOYg?type=png)](https://mermaid.live/edit#pako:eNpVkE1vgkAQhv8KmTOuoFRYDk0UbHsxMW1PBdNM2BGxwNp112qN_11EbOqcZp73nY_METIpCEJYlvInW6HS1nuc1lYT42Su5Jq0XFi93qM1SeaNSu6iU1sYXeHgDsYJqm9T7OSnpq0mpvf6Tp8mLC90kddS0Z3wlLxOx_FsyirR8UnLn7vV_YnZZhgVAgWxNe6wc0Wt66W7pR9hmZkS54oyebOBDbkqBIRaGbKhIlXhpYTjZUQKekUVpRA2qUD1lUJan5qeDdYfUla3NiVNvoJwieW2qcxGoKa4wFxh9UfRaPl2qLNbD4lCSzW7frl9djOHakEqkqbWELpeuwfCI-wh5JwNPR64Qz8I-IM_4jYcGo_jM2_oe5y7njNyBtw72fDbXuaw4ML_xekMqveOYg)


## 📝 Formato do Arquivo de Entrada (`arquivo_teste.txt`)

### Parte 1 – Buscar Cidade por CEP

Formato:
Cidade A,10000,19999
Cidade B,20000,29999
15000

- Antes do `--`: faixas de CEP associadas a cidades.
- Após o `--`: o CEP a ser consultado.

### Parte 2 – Calcular Preço do Frete

Formato:
Cidade A,10000,19999 
Cidade B,20000,29999 
Cidade C,30000,39999
Cidade A,Cidade B,10.5 
Cidade B,Cidade C,7.0 
Cidade A,Cidade C,15.0
12000,32000

- Antes do primeiro `--`: faixas de CEP associadas a cidades.
- Entre os dois `--`: as rotas e seus custos.
- Após o segundo `--`: os CEPs de origem e destino.

---

## ▶️ Como Executar

### Parte 1 – Buscar Cidade por CEP

1. Navegue até o diretório `Parte1`:

    ```bash
    cd Parte1
    ```

2. Execute o programa com o arquivo de teste como parâmetro:

    ```bash
    java BuscaCidade.java arquivo_teste.txt
    ```

### Parte 2 – Calcular Preço do Frete

1. Navegue até o diretório `Parte2`:

    ```bash
    cd Parte2
    ```

2. Execute o programa com o arquivo de teste como parâmetro:

    ```bash
    java CalculaPreco.java arquivo_teste.txt
    ```

---

## 💡 Exemplo de Saída

### Parte 1

**Entrada:**
Cidade A,10000,19999 
Cidade B,20000,29999
15000

**Saída:**
Cidade A


---

### Parte 2

**Entrada:**
Cidade A,10000,19999 
Cidade B,20000,29999 
Cidade C,30000,39999
Cidade A,Cidade B,10.5 
Cidade B,Cidade C,7.0 
Cidade A,Cidade C,15.0
12000,32000


**Saída:**
Rota a ser realizada: Cidade A -> Cidade B -> Cidade C 
Preço frete: 17.50


---

## ✅ Regras de Validação

- Linhas em branco são ignoradas.
- Linhas fora do formato esperado são ignoradas.
- A cidade correspondente ao CEP será a de **menor faixa compatível** (caso mais de uma faixa cubra o CEP).
- O programa utiliza o **algoritmo de Dijkstra** para calcular o caminho mais barato entre duas cidades.

---


## ⚙️ Como Compilar (caso necessário)

### Parte 1

Para compilar o código da Parte 1:

````bash
javac Parte1/BuscaCidade.java
````


### Parte 2

Para compilar o código da Parte 2:
````bash
javac Parte2/CalculaPreco.java
````
Após compilar, os arquivos .class serão gerados, e você poderá rodá-los conforme mostrado nas instruções acima.


🚀 Tecnologias Utilizadas
Java 8+

Algoritmo de Dijkstra para cálculo de menor rota

Entrada via linha de comando e arquivos .txt

## ⚠️ Problemas Enfrentados

Durante os testes, enfrentei alguns problemas ao alterar o arquivo para incluir mais informações além das pertinentes ao uso do sistema. Para garantir que o sistema não falhasse, implementei validações para lidar com essas situações, garantindo maior robustez ao programa. Abaixo estão os principais problemas encontrados e as soluções adotadas:

### 🛠️ Problema 1: Espaços em Branco
- **Descrição**: O programa tentava ler as linhas do arquivo e não conseguia aplicar o `.split()` ou `.length()` corretamente devido a espaços em branco antes ou depois dos dados.
- **Solução**: Implementei o método `.isEmpty()` para verificar se a linha estava vazia e `.trim()` para remover espaços desnecessários antes de processar os dados.

### 🛠️ Problema 2: Linhas em Branco ou com Texto Não Esperado
- **Descrição**: O programa tentava ler linhas que estavam em branco ou com texto fora do formato esperado, o que fazia com que as variáveis não fossem corretamente setadas.
- **Solução**: Implementei verificações de `.length` nos arrays antes de processá-los, para garantir que somente as linhas com dados válidos fossem analisadas.

### 🛠️ Problema 3: Cálculo de Rota Mais Curta
- **Descrição**: Na Parte 2 do programa, o cálculo da rota mais curta estava apresentando problemas, como o programa fazendo voltas desnecessárias entre as cidades.
- **Solução**: Após algumas tentativas, encontrei a solução com o **algoritmo de Dijkstra**, que é um algoritmo eficiente para encontrar o caminho mais curto entre dois pontos. Esse algoritmo foi implementado para corrigir o problema da rota.

---

Esses ajustes tornaram o programa mais robusto e lidasse com entradas inesperadas, sem quebrar durante a execução.

🚨 Importante!
Os arquivos .class não foram versionados neste repositório para manter o projeto limpo e seguir boas práticas. Para rodar os programas, basta compilar os arquivos .java seguindo as instruções acima.
