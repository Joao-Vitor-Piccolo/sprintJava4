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

## Deploy

API publicada em [https://sprintjava4-1.onrender.com](https://sprintjava4-1.onrender.com).

## Endpoints

| Recurso  | Método      | URL                                                                |
| -------- | ----------- | ------------------------------------------------------------------ |
| Manager  | GET, POST   | https://sprintjava4-1.onrender.com/manager                          |
| Manager  | GET, DELETE | https://sprintjava4-1.onrender.com/manager/{id}                     |
| Manager  | PUT         | https://sprintjava4-1.onrender.com/manager                          |
| Customer | GET, POST   | https://sprintjava4-1.onrender.com/customer                         |
| Customer | GET, DELETE | https://sprintjava4-1.onrender.com/customer/{id}                    |
| Customer | PUT         | https://sprintjava4-1.onrender.com/customer                         |
| Salesman | GET, POST   | https://sprintjava4-1.onrender.com/salesman                         |
| Salesman | GET, DELETE | https://sprintjava4-1.onrender.com/salesman/{id}                    |
| Salesman | GET         | https://sprintjava4-1.onrender.com/salesman/nome/{nome}             |
| Salesman | PUT         | https://sprintjava4-1.onrender.com/salesman                         |
| Sale     | GET, POST   | https://sprintjava4-1.onrender.com/sale                             |
| Sale     | GET, DELETE | https://sprintjava4-1.onrender.com/sale/{id}                        |
| Sale     | PUT         | https://sprintjava4-1.onrender.com/sale                             |
| Sale     | GET         | https://sprintjava4-1.onrender.com/sale/salesman/{idSalesman}       |
| Sale     | PUT         | https://sprintjava4-1.onrender.com/sale/{id}/iniciar/{idSalesman}   |
| Sale     | PUT         | https://sprintjava4-1.onrender.com/sale/{id}/finalizar/{idSalesman} |

Exemplo de cadastro de cliente:

```bash
curl -X POST https://sprintjava4-1.onrender.com/customer \
  -H "Content-Type: application/json" \
  -d '{"name":"Maria","cpf":"12345678900","phone":"11999999999"}'
```

Exemplo de listar vendas de um vendedor:

```bash
curl https://sprintjava4-1.onrender.com/sale/salesman/1
```

## Integrantes

- João Vitor Piccolo | RM 565127
- Gabrielle Calazans | RM 564460
- Jéssica Domingues | RM 562973
- Kauã Carvalho | RM 566371
- Leonardo Pereira | RM 561349
