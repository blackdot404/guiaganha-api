# Mapeamento de Tecnologias e Frameworks (Back-end)

Este documento apresenta a stack tecnológica escolhida para o desenvolvimento do **Gestor de Saúde Financeira** e as respectivas justificativas técnicas e acadêmicas para a sua adoção.

## 1. Core do Sistema: Java 21 & Spring Boot 4.x
O coração da aplicação será desenvolvido na linguagem Java utilizando a plataforma Spring Boot.
* **Tipagem Forte e Precisão Matemática:** Essencial para sistemas financeiros. O uso do tipo `BigDecimal` do Java garante que não ocorram erros de arredondamento comuns em linguagens de tipagem dinâmica.
* **Inversão de Controle (IoC) e Injeção de Dependências (DI):** Gerenciado nativamente pelo Spring, permite o desacoplamento da camada de serviços (`Service Layer`), facilitando a aplicação dos Design Patterns *Strategy* e *State*.

## 2. Persistência de Dados: Spring Data JPA & PostgreSQL
Para a camada de acesso a dados (`Data Access Layer`), optou-se por um modelo Relacional e uma camada de abstração ORM.
* **Mapeamento Objeto-Relacional (ORM):** O Spring Data JPA (via Hibernate) automatiza a tradução entre as entidades Java e as tabelas SQL, acelerando o desenvolvimento.
* **Integridade e Consistência (ACID):** O PostgreSQL garante que relacionamentos rígidos (Chaves Estrangeiras) e travas de segurança sejam aplicados na base, impedindo dados órfãos e garantindo a consistência das transações.

## 3. Segurança e Autenticação: Spring Security & JWT (JSON Web Token)
A segurança do sistema é projetada para o modelo de arquitetura de APIs modernas.
* **Autenticação Stateless:** O servidor não armazena o estado da sessão na memória. O token JWT é gerado no login ou cadastro, assinado digitalmente e enviado ao cliente.
* **Filtros de Intercepção:** O Spring Security atua antes da `Resource Layer` (Controllers), garantindo que um usuário autenticado só acesse e manipule dados vinculados estritamente ao seu próprio ID de usuário.

## 4. Infraestrutura e Ferramentas: Maven & Docker
Ferramentas de suporte para garantir a portabilidade e gerenciamento do projeto.
* **Apache Maven:** Gerenciador de dependências que padroniza o ciclo de vida do build e o download de bibliotecas do ecossistema Java.
* **Docker & Docker Compose:** Utilizado para containerizar o banco de dados PostgreSQL, permitindo que o ambiente de banco seja inicializado de forma idêntica em qualquer máquina de desenvolvimento com um único comando.

## 5. Banco de Dados em Memória: Redis
Utilizado como camada de suporte de alta performance para duas regras arquiteturais distintas:
* **Cache da Dashboard:** Armazena o resultado consolidado do cálculo da meta diária e saldos do usuário para evitar consultas repetitivas e pesadas no PostgreSQL. O cache é invalidado automaticamente a cada novo lançamento financeiro.
* **Blacklist de Tokens (Logout Stateful):** Garante a invalidação imediata de tokens JWT antes do seu tempo natural de expiração quando o usuário solicita o encerramento da sessão, mantendo a integridade da segurança sem sobrecarregar a base relacional.

## 6. Documentação da API: Springdoc OpenAPI & Swagger UI
Utilizado para automatizar a geração da documentação técnica e interativa dos endpoints da aplicação.
* **Geração Automatizada:** Analisa as anotações dos controladores na `Resource Layer` e gera a especificação OpenAPI 3.
* **Interface Interativa (Sandbox):** Permite que desenvolvedores de Front-end ou avaliadores testem os endpoints diretamente pelo navegador, simulando envios de dados e fluxos de autenticação sem a necessidade de softwares terceiros (como Postman/Insomnia).