# Projeto Telex

> Sistema de gestão de planos de telecomunicações e controle de consumo de dados, desenvolvido em Java com foco em Programação Orientada a Objetos (POO), encapsulamento e boas práticas de modelagem de software.

---

## Sobre o projeto

O **Projeto Telex** é um back-end que simula a operação central de uma operadora de internet (ISP). O sistema resolve três necessidades: manter um catálogo de planos, associar cada cliente a um plano e controlar o consumo de dados de cada assinante.

O projeto partiu de uma modelagem prévia, com Diagrama Entidade-Relacionamento (DER) e fluxogramas dos processos, e depois foi implementado em Java puro, sem frameworks externos.

Este é o meu primeiro projeto autoral e continua em evolução.

---

## Funcionalidades

- **Catálogo de planos:** planos com diferentes velocidades (MB) e mensalidades (R$).
- **Gestão de clientes:**
  - Cadastro de novos clientes associados a um plano inicial.
  - Validação de identidade única por CPF, impedindo cadastros duplicados.
- **Controle de consumo:**
  - Registro e acúmulo do tráfego consumido (GB).
  - Bloqueio de valores nulos ou negativos, preservando a integridade dos dados.
- **Troca de plano (upgrade e downgrade):**
  - Alteração do plano de um cliente.
  - Bloqueio de operações inválidas, com aviso quando o cliente já está no plano mínimo (downgrade) ou no plano máximo (upgrade).
- **Relatório no terminal:** listagem de clientes com os dados formatados, por meio da sobrescrita do método `toString()`.

---

## Tecnologias e ferramentas

| Componente | Especificação |
| :--- | :--- |
| **Linguagem** | Java 25 (OpenJDK) |
| **Paradigma** | Programação Orientada a Objetos (POO) |
| **IDE** | IntelliJ IDEA |
| **Modelagem** | Draw.io (DER e fluxogramas) |
| **Versionamento** | Git e GitHub |
| **Sistema operacional** | Linux Mint |

---

## Modelagem e arquitetura

O sistema se apoia em dois pilares. A documentação da modelagem (DER e fluxogramas) está na pasta [`docs/`](docs/).

1. **Entidades de domínio**
   - `Plano`: pacote de serviço (`id`, `nome`, `velocidadeMb`, `valor`).
   - `Cliente`: assinante do serviço (`cpf`, `nome`, `consumoGb`, `plano`).
2. **Encapsulamento e regras de negócio**
   - Todos os atributos de estado são `private`, impedindo alterações indevidas por classes externas.
   - O consumo só pode ser alterado pelo método de domínio `registrarConsumo(double gb)`.
   - A mudança de plano é controlada pelo método `setPlano(Plano novoPlano)`.

---

## Como executar

1. Tenha o **JDK 25** (ou superior) instalado.
2. Clone o repositório:
   ```bash
   git clone https://github.com/mgs-core369/projeto-telex.git
   ```
3. Abra o projeto no IntelliJ IDEA (ou outra IDE Java).
4. Execute a classe principal, `Main.java`.

---

## Próximos passos

- [ ] Integração com banco de dados (MySQL ou PostgreSQL) via JDBC, com classes DAO e `PreparedStatement`
- [ ] Script `schema.sql` com as tabelas de clientes, planos e consumo
- [ ] Novas validações: formato e dígitos verificadores do CPF, e-mail e telefone
- [ ] Aviso quando o consumo ultrapassar o limite do plano
- [ ] Testes unitários com JUnit para as regras de negócio
- [ ] Tratamento de exceções com mensagens claras ao usuário

---

## Autor

**Miguel Matias**

Estudante de Análise e Desenvolvimento de Sistemas (UNINOVE)

[LinkedIn](https://www.linkedin.com/in/miguel-matias-nunes-soares0849abt) · [GitHub](https://github.com/mgs-core369)
