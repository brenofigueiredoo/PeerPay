Projeto de Sistema de Transações Bancárias

Bem-vindo ao projeto PeerPay! Este é um projeto pessoal criado com o objetivo de simular transações bancárias entre usuários. O sistema é construído com base em duas tabelas principais: users e transactions.

Diagrama das tabelas

![DER drawio](https://github.com/brenofigueiredoo/PeerPay/assets/80117189/2959522e-450c-4d47-a90f-6f6d8e88d4bb)

## Funcionalidades
- Tabela transactions: Esta tabela registra as transações realizadas entre os usuários.
- Registro de Usuário: Os usuários podem se registrar no sistema, fornecendo seu nome, email, cpf, password e type. Isso cria uma entrada na tabela users.
- Realização de Transações: Os usuários podem realizar transações entre si. Uma transação é registrada na tabela transactions e envolve um remetente, um destinatário e um valor.
- Verificação de Saldo: Os usuários podem verificar o saldo atual de suas contas.
- Histórico de Transações: Os usuários podem visualizar um histórico das transações que realizaram ou receberam.

## Tecnologias Utilizadas
- Linguagem de Programação: Java
- Banco de Dados: SQL (H2 database)
- Framework Web: Spring Boot
- Ferramenta de Construção: Maven
  
## Instruções de Uso:
1. Faça o clone da do repósitorio e acesse a pasta clonada.
```shell
git clone https://github.com/brenofigueiredoo/PeerPay.git

cd PeerPay
```
2. Rode o servidor.
```
mvn spring-boot:run
```
