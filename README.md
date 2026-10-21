# Projeto Java Final

API REST em Spring Boot para gerenciar managers, vendedores, clientes e vendas. A conexão é feita com o Oracle da FIAP via JDBC.

## Requisitos

- Java 21
- Maven
- Acesso ao banco Oracle da FIAP

## Como rodar

Na raiz do projeto:

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

Se a porta 8080 já estiver ocupada:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

Para só compilar:

```bash
mvn -DskipTests compile
```

## Endpoints

| Recurso | Método | Caminho |
|---|---|---|
| Manager | GET, POST | `/manager` |
| Manager | GET, DELETE | `/manager/{id}` |
| Manager | PUT | `/manager` |
| Customer | GET, POST | `/customer` |
| Customer | GET, DELETE | `/customer/{id}` |
| Customer | PUT | `/customer` |
| Salesman | GET, POST | `/salesman` |
| Salesman | GET, DELETE | `/salesman/{id}` |
| Salesman | GET | `/salesman/nome/{nome}` |
| Salesman | PUT | `/salesman` |
| Sale | GET, POST | `/sale` |
| Sale | GET, DELETE | `/sale/{id}` |
| Sale | PUT | `/sale` |
| Sale | GET | `/sale/salesman/{idSalesman}` |
| Sale | PUT | `/sale/{id}/iniciar/{idSalesman}` |
| Sale | PUT | `/sale/{id}/finalizar/{idSalesman}` |

Exemplo de cadastro de cliente:

```bash
curl -X POST http://localhost:8080/customer \
  -H "Content-Type: application/json" \
  -d '{"name":"Maria","cpf":"12345678900","phone":"11999999999"}'
```

Exemplo de listar vendas de um vendedor:

```bash
curl http://localhost:8080/sale/salesman/1
```

## Integrantes

- João Vitor Piccolo | RM 565127
- Gabrielle Calazans | RM 564460
- Jéssica Domingues | RM 562973
- Kauã Carvalho | RM 566371
- Leonardo Pereira | RM 561349
