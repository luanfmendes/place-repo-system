<h1 align="center">
  Place Service
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio" />
</p>

API para gerenciar lugares (CRUD) que faz parte [desse desafio](https://github.com/RocketBus/quero-ser-clickbus/tree/master/testes/backend-developer) para pessoas desenvolvedoras backend.

## Descrição

A aplicação Places é um projeto de exemplo que demonstra a criação de um aplicativo Spring Boot básico. Ela inclui integração com um banco de dados H2, geração de documentação Swagger usando Springdoc OpenAPI, e outras funcionalidades comuns de aplicativos Spring.

## Pré-requisitos

Antes de começar, certifique-se de ter os seguintes pré-requisitos instalados:

- Java 17 ou superior
- Maven
- Docker (opcional, se você deseja criar imagens Docker)

## Instalação

Siga as etapas abaixo para configurar e executar o projeto:

1. Clone o repositório para o seu ambiente local:

   ```bash
   git clone https://github.com/seu-usuario/places.git

## Como Executar

### Localmente
- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Executar:
```
java -jar places/target/places-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Usando Docker

- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Construir a imagem:
```
docker build -t minha-imagem-docker:1.0 .
```
- Executar o container:
```
docker run -p 8080:80 minha-imagem-docker:1.0
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

- POST /places
```
http POST :8080/places name="Place" state="State"

HTTP/1.1 200 OK
Content-Length: 129
Content-Type: application/json

{
    "createdAt": "2023-04-20T19:00:07.241632",
    "name": "Place",
    "slug": "place-slug",
    "state": "State",
    "updatedAt": "2023-04-20T19:00:07.241632"
}
```

- GET /places/{id}
```
http :8080/places/1
HTTP/1.1 200 OK
Content-Length: 129
Content-Type: application/json

{
    "createdAt": "2023-06-07T14:45:39.693689",
    "name": "Place",
    "slug": "place-slug",
    "state": "State",
    "updatedAt": "2023-06-07T14:45:39.693689"
} 
```
- PUT /places/{id}
```
http PATCH :8080/places/1 name='New Name' state='New State'
HTTP/1.1 200 OK
Content-Length: 142
Content-Type: application/json

{
    "createdAt": "2023-06-07T14:45:39.693689",
    "name": "New Name",
    "slug": "new-name-slug",
    "state": "New State",
    "updatedAt": "2023-06-07T14:53:21.671129345"
}
```