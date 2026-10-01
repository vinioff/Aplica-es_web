# ImagemPeças

API em **Spring Boot** para um álbum de imagens online: publicar imagens com nome e tags, pesquisar por nome, tag ou extensão e visualizar cada imagem em tamanho real.

## Tecnologias

- Java 25
- Spring Boot 4.1.0 (Web MVC + Data JPA)
- PostgreSQL 16 (via Docker)
- Lombok
- Maven (wrapper incluso)

## Como rodar

1. Suba o banco de dados (PostgreSQL + pgAdmin):

   ```bash
   docker compose up -d
   ```

2. Inicie a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

A API fica disponível em `http://localhost:8080` e o pgAdmin em `http://localhost:15432`.

## Endpoints

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/v1/images` | Publica uma imagem (`multipart/form-data` com `file`, `name` e `tags`) |
| `GET` | `/v1/images/{id}` | Retorna a imagem em tamanho real |
| `GET` | `/v1/images?extension=PNG&query=motor` | Pesquisa por extensão (`PNG`, `JPG`, `GIF`) e/ou texto no nome e nas tags |

---

# Requisitos Funcionais do Projeto

Abaixo estão listados os Requisitos Funcionais (RF) que especificam o comportamento e o funcionamento do sistema.

## Tabela de Requisitos Funcionais

| ID | Descrição |
| :--- | :--- |
| **RF01** | O sistema deve permitir que um visitante crie uma conta informando nome, e-mail e senha. |
| **RF02** | O sistema deve impedir o cadastro de um e-mail já existente. |
| **RF03** | O sistema deve permitir que um usuário cadastrado se autentique com e-mail e senha. |
| **RF04** | O sistema deve emitir um token de acesso (JWT) após autenticação bem-sucedida. |
| **RF05** | O sistema deve permitir que o usuário autenticado encerre sua sessão. |
| **RF06** | O sistema deve permitir que o usuário autenticado publique uma nova imagem, informando nome, tags e arquivo. |
| **RF07** | O sistema deve validar o formato (PNG, JPEG, GIF) e o tamanho do arquivo antes do envio. |
| **RF08** | O sistema deve permitir que o usuário pesquise imagens por nome, por tag e/ou por extensão. |
| **RF09** | O sistema deve exibir as imagens encontradas em formato de galeria, com nome, extensão, tamanho e data de upload. |
| **RF10** | O sistema deve permitir a visualização da imagem em tamanho real a partir da miniatura. |
| **RF11** | O sistema deve notificar o usuário sobre sucesso ou falha de cada operação realizada. |
