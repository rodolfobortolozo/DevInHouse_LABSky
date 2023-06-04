# Descrição do Projeto

A LABSky Linhas Aéreas entrou em operação com uma aeronave para atender a um grupo seleto de clientes que fazem o trecho Florianópolis/SC - Santa Maria/RS diariamente. Você foi escolhido para criar o back-end de uma aplicação para gerenciar os passageiros que irão no voo.

A aplicação deve ser uma API REST desenvolvida em Java com Spring Boot, e que atenda aos requisitos elencados neste documento - principalmente os quesitos de testes - para controle de quais passageiros confirmaram (realizaram check-in) para o voo e qual assento foi selecionado.

## **Ferramentas e Programas Utilizados**
```
Intellij
Spring Boot     
Junit 5
H2
````

# Métodos
Requisições para a API devem seguir os padrões:

| Método   | Descrição                                             |
|----------|-------------------------------------------------------|
| `GET`    | Retorna informações de um ou mais registros.          |
| `POST`   | Utilizado para criar um novo registro.                |
| `PUT`    | Atualiza dados de um registro ou altera sua situação. |
| `DELETE` | Remove um registro do sistema.                        |

# Recursos da Api

| Tipo       | End Point                    |
|------------|------------------------------|
| `GET`      | /api/passageiros             |
| `GET`      | /api/passageiros/{cpf}       |
| `POST`     | /api/passageiros/confirmacao |
| `GET`      | /api/assentos                |

# Passageiros [_/api/passageiros_]

Lista todos os passageiros.

### Listar [**GET** _/api/passageiros_]
````
[
  {
    "cpf": "000.000.000-00",
    "nome": "Rachel Green",
    "dataNascimento": "11/01/1969",
    "classificacao": "VIP",
    "milhas": 100,
    "eticket": null,
    "assento": null,
    "dataHoraConfirmacao": null
  },
  {
    "cpf": "111.111.111-11",
    "nome": "Phoebe Buffay",
    "dataNascimento": "30/07/1963",
    "classificacao": "OURO",
    "milhas": 100,
    "eticket": null,
    "assento": null,
    "dataHoraConfirmacao": null
  }
]
````
Lista passageiro por cpf.

### Listar [**GET** _/api/passageiros/{CPF}_]
````
  {
    "cpf": "111.111.111-11",
    "nome": "Phoebe Buffay",
    "dataNascimento": "30/07/1963",
    "classificacao": "OURO",
    "milhas": 100,
    "eticket": null,
    "assento": null,
    "dataHoraConfirmacao": null
  }
````

Confirmação Checin.

### Confirmar [**POST** _/api/passageiros/confirmacao_]

Request
````
 {
    "cpf": "000.000.000-00",
    "assento": "5A",
    "malasDespachadas": true
}
````
Response [201Created]
````
{
"eticket": "4c8bf1ba-f332-4de3-b40f-b565f04049a8",
"dataHoraConfirmacao": "03/06/2023 11:06:49"
}
````