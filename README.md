## Como rodar o projeto
### Crie o banco de dados
 
Crie um banco chamado no PostgreSQL (via pgAdmin4 ou terminal).
 
### Altere as informações dentro do `application.properties` para conseguir roda-lo em seu banco local
 
Em `src/main/resources/application.properties`:

Altere o `NOME_DO_SEU_BANCO` para o nome do banco criado em sua maquina e também faça a alteração do `SEU_USUARIO` e `SUA_SENHA` para as credenciais configuradas em seu banco.
 
```properties
spring.application.name=demo
spring.datasource.url=jdbc:postgresql://localhost:5432/NOME_DO_SEU_BANCO
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
```

### Para rodar a aplicação
 
Na IDE de sua escolha, rode a classe `DemoApplication`
 
A aplicação sobe por padrão na porta `8080`.

## Endpoints implementados
 
| Verbo | Rota | Descrição |
|---|---|---|
| GET | `/pratos` | Lista todos os pratos 
| GET | `/pratos/{id}` | Busca um prato pelo ID 
| GET | `/pratos?categoria=vegano` | Filtra pratos por categoria 
| GET | `/pratos/calorias?max=100` | Filtra pratos por quantidade de calorias 
| POST | `/pratos` | Cria um novo prato 
| PUT | `/pratos/{id}` | Atualiza um prato existente 
| DELETE | `/pratos/{id}` | Remove um prato 

