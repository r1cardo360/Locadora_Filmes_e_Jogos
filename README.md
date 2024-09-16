# Locadora de Filmes e Jogos

Este é um projeto desenvolvido em Java com o banco de dados SQLite, com o objetivo de criar um sistema de gerenciamento para uma locadora de filmes e jogos. O projeto foi desenvolvido principalmente para fins educacionais, oferecendo uma oportunidade para aplicar conceitos de programação e gerenciamento de banco de dados.

## Visão Geral

A **Locadora de Filmes e Jogos** é uma aplicação que visa substituir o tradicional sistema de registros em papel utilizado por muitas locadoras. Com este sistema, os administradores podem gerenciar e armazenar informações sobre filmes e jogos de forma digital e organizada. O projeto visa oferecer uma interface amigável para realizar operações básicas, como:

- Cadastro de novos filmes e jogos
- Registro de empréstimos e devoluções
- Consulta e edição de registros existentes
- Geração de relatórios sobre o estado dos itens e transações

O sistema foi projetado para simplificar o processo de administração e melhorar a eficiência, minimizando o uso de papel e melhorando a gestão dos dados.

## Funcionalidades

- **Cadastro e Edição de Itens:** Adicione e atualize informações sobre filmes e jogos disponíveis para locação.
- **Gerenciamento de Empréstimos:** Registre e acompanhe empréstimos e devoluções de itens.
- **Consulta de Dados:** Realize pesquisas e consultas sobre itens e transações.
- **Relatórios:** Gere relatórios detalhados sobre o estado dos itens e atividades de locação.

## Tecnologias Utilizadas

- **Linguagem de Programação:** Java
- **Banco de Dados:** SQLite

## Instalação

Para configurar e executar o projeto, siga estas etapas:

1. **Clone o Repositório:**

    ```bash
    git clone https://github.com/usuario/nome-do-projeto.git
    ```

2. **Abra o Projeto em uma IDE Java:**

    Você pode usar qualquer IDE Java, como IntelliJ IDEA ou Eclipse. 

3. **Configure o Caminho do Banco de Dados:**

    O banco de dados está localizado no caminho `.\Locadora_Filmes_e_Jogos\Banco_de_Dados\Locadora.db`. Para garantir que o projeto funcione corretamente, você deve configurar o caminho do banco de dados na classe `DAALocadora`.

4. **Compile o Projeto:**

    Se estiver usando Maven, compile o projeto executando o comando:

    ```bash
    mvn clean package
    ```

## Uso

Para executar o projeto corretamente, recomenda-se usar o Prompt de Comando (CMD) em vez da IDE, pois a função de limpar a tela pode não funcionar corretamente nas IDEs.

1. **Compile o Projeto com Maven:**

    Execute o comando abaixo para criar o arquivo JAR do projeto:

    ```bash
    mvn clean package
    ```

2. **Execute o Projeto no CMD:**

    Navegue até o diretório onde o arquivo JAR foi gerado (normalmente na pasta `target`), e execute o projeto com o comando:

    ```bash
    java -jar caminho-do-arquivo.jar
    ```

    Substitua `caminho-do-arquivo.jar` pelo caminho real do arquivo JAR gerado.

## Contribuição

Se você deseja contribuir para o projeto, siga estas diretrizes:

- Faça um fork do repositório.
- Crie uma branch para sua feature ou correção (`git checkout -b feature/nome-da-feature`).
- Faça commit das suas alterações (`git commit -am 'Adiciona nova feature'`).
- Envie para o repositório remoto (`git push origin feature/nome-da-feature`).
- Crie um Pull Request no GitHub.




