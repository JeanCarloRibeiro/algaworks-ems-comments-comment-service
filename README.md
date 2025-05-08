
# CommentService

O **CommentService** é um microsserviço responsável por receber comentários de usuários, submetê-los à moderação e armazenar apenas aqueles que forem aprovados. Ele integra-se com o `ModerationService` de forma **síncrona via HTTP/REST**, utilizando `RestClient`.

Este serviço faz parte do sistema **AlgaComments**, desenvolvido como parte do desafio do curso **Especialista Microsserviços** da [Algaworks](https://www.algaworks.com/).

---

## 🌐 Porta padrão

Este microsserviço está configurado para rodar na porta **8080**.

---

## 🚀 Funcionalidades

### ✅ Criar um novo comentário

Cria um novo comentário e envia automaticamente para moderação. Somente comentários aprovados serão persistidos no banco de dados.

**Endpoint:**  
`POST /api/comments`

**Exemplo de requisição:**
```bash
curl --location 'http://localhost:8080/api/comments' \
--header 'Content-Type: application/json' \
--data '{
    "text": "Este é um comentário ódio.",
    "author": "Jean fulano de tal"
}'
```

**Respostas possíveis:**

- `201 Created`: Comentário aprovado e salvo com sucesso.
- `422 Unprocessable Entity`: Comentário reprovado na moderação.
- `502 Bad Gateway`: Erro na integração com o serviço de moderação.
- `504 Gateway Timeout`: Timeout ao tentar comunicar com o serviço de moderação.

---

### 🔍 Consultar um comentário por ID

Retorna os detalhes de um comentário previamente aprovado.

**Endpoint:**  
`GET /api/comments/{id}`

**Exemplo de requisição:**
```bash
curl --location 'http://localhost:8080/api/comments/ac020698-0827-495d-afa0-0abc0cfb00c6'
```

**Respostas possíveis:**

- `200 OK`: Comentário encontrado.
- `404 Not Found`: Comentário não encontrado ou ainda não aprovado.

---

### 📄 Listar comentários aprovados com paginação

Retorna uma lista paginada de todos os comentários aprovados.

**Endpoint:**  
`GET /api/comments?page={page}&size={size}`

**Exemplo de requisição:**
```bash
curl --location 'http://localhost:8080/api/comments?page=0&size=10'
```

**Exemplo de Resposta:**
```json
{
    "content": [
        {
            "id": "01965a35-2aeb-72f1-81c5-01f6325f9432",
            "text": "Este é um novo comentário.",
            "author": "Fulano de tal",
            "createdAt": "2025-04-21T22:15:56.36704-03:00"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "first": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "numberOfElements": 1,
    "empty": false
    
}
```

---

## ⚙️ Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- RestClient
- Lombok

---

## 🚀 Como Executar o Projeto

Você pode executar o projeto de duas formas principais:

### ✅ Usando o IntelliJ IDEA

1. Importe o projeto como um projeto Gradle.
2. Aguarde o download das dependências.
3. Localize a classe `CommentServiceApplication`.
4. Clique com o botão direito e selecione **Run 'CommentServiceApplication'**.

### ✅ Usando o Terminal

Certifique-se de que o Java 21 (ou superior).

```bash
# Para executar o projeto
./gradlew bootRun
```

Ou, se preferir compilar primeiro:

```bash
# Para compilar o projeto
./gradlew build

# Para executar o JAR gerado
java -jar build/libs/comment-service-0.0.1-SNAPSHOT.jar
```

O serviço estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 👨‍💻 Autor

Desenvolvido por Jean Carlo Ribeiro como parte do desafio prático do curso **Especialista Microsserviços** da [Algaworks](https://www.algaworks.com/).

---