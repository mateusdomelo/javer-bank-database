# Javer Database | Serviço de Armazenamento H2

Esta aplicação é responsável por ser a base do sistema de cadastro de clientes do banco **Javer**.
Ela gerencia operações CRUD diretamente em uma base de dados local H2.

### Pré-requisitos
- Certificar-se de ter o Java JDK 17 e o Maven instalados na máquina.

## Como Executar
1. Clone este repositório: `git clone https://github.com/mateusdomelo/javer-bank-database.git`
2. Navegue até o diretório do projeto: `cd javer-bank-database`
3. Execute o projeto usando o Maven: `mvn spring-boot:run`

## Como Acessar
1. Após iniciar a aplicação, é possível acessar os endpoints REST utilizando ferramentas como cURL, Postman, Insomnia ou qualquer outro cliente HTTP;
2. Os endpoints estão disponíveis em `http://localhost:8081/`;
   1. A porta definida é '_8081_', por padrão, mas pode ser alterada através do "_application.properties_" da pasta "_resources_".
3. Consultar a documentação da API para obter detalhes sobre os endpoints disponíveis e os payloads esperados em `http://localhost:8081/swagger-ui/index.html`;

## Testes Unitários

1. Garantir que os passos descritos na seção "_Como Executar_" foram realizados;
2. Executar os testes usando o Maven: `mvn test`.

O relatório de coverage gerado pela ferramenta _Jacoco_ pode ser visualizado através do "_index.html_" do diretório "_htmlReport_".

---
## Contatos
Em caso de dúvidas, problemas ou sugestões, sinta-se à vontade para entrar em contato:

- **E-mail:** mateusdomelo@gmail.com
- **LinkedIn:** [Mateus Melo](https://www.linkedin.com/in/mateusdomelo/)