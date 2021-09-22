# Back-end desafio Luiza Lab!

### Projeto desenvolvido para um processo seletivo na LuizaLab!

 Esse projeto consiste em preencher automaticamente os campos de RUA, BAIRRO, CIDADE e ESTADO, a partir do CEP fornecido, 
 é necessário cumprir com alguns requisitos de validação. A API precisa ter autorização para ser acessada.  

---

## Linguagem utilizada

- Java com Framework SpringBoot

## Dependências utilizadas

* SpringBoot Web

- SpringBoot Jpa (Para a manipulação de dados).

* SpringSecurity (Para a autenticação de acesso na API).

- Driver de H2 DB (Banco de dados).

* Mockito Core (Para simular valores em objetos e dados no banco).

- Swagger (Documentação).

* Feign Client (Integração de API externa).

- JJWT (Gerar tokens com informações do usuário autenticado).

* Actuator (Para coletar informações da aplicação).

- Admin Starter Client (Utiliza os dados coletados da API e cria endpoints de métricas e saúde da aplicação. Criando em um outro projeto um ambiente de monitoramento da API).

---

##EndPoints
### localhost:8080/usuarios 
Tipo: [POST]  

RequestBody: Usuario

PathVariable:

Função: Criação de um usuário.

Exemplo de envio:

{

"nomeUsuario": "Admin",

"emailUsuario": "admin@admin",

"senhaUsuario": "12345"

}

Possíveis respostas: 200, 400.

### localhost:8080/enderecos/{cep}
Tipo: [GET] 

RequestBody:

PathVariable: String

Função: consulta de um endereço preenchido utilizando apenas o cep.

Possíveis respostas: 404 (Cep inválido), 200 (Endereco), 404.

###localhost:8080/autenticacoes
Tipo: [POST]

RequestBody:

PathVariable: FormLogin

Função: Varificar se o usuário está cadastrado.

Exemplo de envio:

{

"email": "admin@admin",

"senha": "12345"

}

Possíveis respostas: 200 (Token), 404 (Endereco).

###localhost:8080/swagger-ui.html
Função: Acessar o swagger.

###localhost:8081
Esse endpoint é para visualizar as métricas e gráficos com os dados coletados da API principal em uma aplicação web.

É necessário iniciar o projeto `monitoramento.api` para conseguir acessa-lo
