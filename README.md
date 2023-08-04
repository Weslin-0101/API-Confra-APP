# Projeto API Confra APP

## Descrição do Projeto:

Projeto consiste na criação de um microserviço para a leitura e validação de QR Code do Sicoob.

## Como executar o projeto:

Este projeto é desenvolvido utilizando Java 11, Spring Boot, Maven, Docker e Docker Compose.

Para executar o projeto, é necessário ter instalado o Docker e o Docker Compose. Acesse o link abaixo para
instalar o Docker:

### Para Windows:

- Acesse o link abaixo [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop/)

### Para Linux:

- Acesse o link abaixo [Docker Engine for Linux](https://docs.docker.com/desktop/install/linux-install/)


Ao terminar o processo de instalação, clone o projeto do GitHub em um diretório de sua preferência. Em seu
terminal, execute o seguinte comando:
    
    git clone https://github.com/Weslin-0101/API-Confra-APP.git

Após clonar o projeto, acesse o diretório do projeto utilizando a sua IDE de preferência. Em seguida,
execute o comando abaixo dentro do terminal da IDE, no diretório do projeto:

    docker-compose up -d

Esse comando vai executar o Docker Compose e baixar as imagens necessárias para subir o banco de dados
localmente.


## Regras para a conduta de branchs e commits:

### Para Branchs:

- Branchs devem ser criadas a partir da branch `develop` e devem ser nomeadas com o padrão `feature{n° da task}/nome-da-feature` ou `bugfix{nº da task}/nome-do-bugfix`.
- Ao final da tarefa, deve ser feito um Pull Request para a branch `develop` e o mesmo deve ser revisado por outro membro da equipe.

### Para Commits:

- Commits devem ser feitos em inglês, descritivos e atômicos.
- Os commits devem seguir o padrão de Conventional Commits, que pode ser encontrado no link a baixo: 
https://dev.to/vitordevsp/padronizacao-de-commit-com-commitlint-husky-e-commitizen-3g1n