# Sistema de Gerenciamento de Livraria

Este é um sistema de gerenciamento de livraria que permite o cadastro de livros impressos e eletrônicos, realização de vendas, listagem de livros e vendas, além de registrar o estoque, preço e frete dos livros.

## Funcionalidades Principais

- Cadastro de livros (Impressos e Eletrônicos)
- Realização de vendas de livros
- Listagem de livros disponíveis
- Listagem das vendas realizadas

## Pré-requisitos

- Java 20
- MySQL

## Instalação e Execução

1. Clone este repositório para o seu sistema local:
2. Navegue até o diretório do projeto:
3. Compile e execute o projeto:

### Usando Docker (Alternativa)

Se você tiver o Docker instalado, pode usar o `docker-compose` fornecido para simplificar a configuração do banco de dados MySQL. Execute o seguinte comando na raiz do projeto:


Isso criará um contêiner MySQL e configurará as informações necessárias para o sistema funcionar.

Em seguida, você pode compilar e executar o projeto Java normalmente.

Com o docker instalado basta usar o comado docker-compose up -d