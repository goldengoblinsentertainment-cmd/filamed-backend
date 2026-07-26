# Arquitetura do Sistema FilaMed

## Objetivo

O FilaMed é um sistema para gerenciamento de atendimento em clínicas e consultórios médicos.

O sistema permitirá que pacientes realizem seu cadastro, respondam a anamnese digital, entrem em uma fila de atendimento e acompanhem sua posição em tempo real através de um aplicativo Android.

Médicos poderão visualizar a anamnese, chamar pacientes e registrar atendimentos.

Administradores poderão gerenciar usuários, perguntas da anamnese, médicos e estatísticas do sistema.

## Tecnologias

Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Maven

Banco de Dados

- MySQL
- Aiven Cloud

Aplicativo

- Android
- Kotlin (definiremos depois)

Controle de Versão

- Git
- GitHub

Documentação

- Markdown
- Swagger
## Arquitetura

O backend será organizado utilizando arquitetura em camadas.

Cada camada terá apenas uma responsabilidade.

Android
│
▼
Controller
│
▼
Service
│
▼
Repository
│
▼
MySQL

## Módulos

O sistema será dividido nos seguintes módulos:

- Pacientes
- Médicos
- Especialidades
- Consultas
- Anamnese
- Perguntas
- Respostas
- Fila
- Usuários
- Autenticação
- Dashboard
