<div align="center">

# 🏥 FilaMed

### Sistema Inteligente de Gestão de Filas Médicas

API REST desenvolvida em **Java** com **Spring Boot** para gerenciamento de pacientes, médicos, consultas e fila de atendimento.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-6DB33F?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven)

</div>

---

# 📖 Sobre o Projeto

O **FilaMed** é um sistema de gerenciamento para clínicas e consultórios médicos, desenvolvido com foco em organização, escalabilidade e boas práticas de desenvolvimento.

A aplicação permite controlar o fluxo completo de atendimento, desde o cadastro de pacientes até a finalização do atendimento, utilizando uma API REST documentada com Swagger.

---

# ✨ Funcionalidades

## 👤 Pacientes

- Cadastro
- Consulta
- Atualização
- Exclusão

## 👨‍⚕️ Médicos

- Cadastro
- Consulta
- Atualização
- Exclusão

## 🩺 Especialidades

- Cadastro
- Consulta
- Atualização
- Exclusão

## 📅 Consultas

- Cadastro
- Consulta
- Atualização
- Cancelamento

## 🎟️ Fila de Atendimento

- Entrada do paciente na fila
- Chamada do próximo paciente
- Finalização do atendimento
- Consulta da fila
- Consulta por paciente
- Posição na fila
- Tempo estimado de espera

---

# 🏗 Arquitetura

```
Cliente

    │

REST Controller

    │

Service

    │

Repository

    │

Spring Data JPA

    │

MySQL
```

---

# 📂 Estrutura do Projeto

```
src
└── main
    └── java
        └── br.com.filamed
            ├── paciente
            ├── medico
            ├── especialidade
            ├── consulta
            ├── fila
            ├── exception
            └── config
```

---

# 🚀 Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- MySQL
- Swagger / OpenAPI
- Jakarta Validation
- Git
- GitHub

---

# 📚 Principais Endpoints

| Método | Endpoint |
|---------|----------|
| GET | /pacientes |
| GET | /medicos |
| GET | /especialidades |
| GET | /consultas |
| GET | /fila |
| GET | /fila/aguardando |
| GET | /fila/paciente/{pacienteId} |
| POST | /fila |
| POST | /fila/proximo |
| PUT | /fila/{id}/finalizar |

---

# ▶️ Como Executar

### Clone o projeto

```bash
git clone https://github.com/SEU-USUARIO/FilaMed.git
```

### Entre na pasta

```bash
cd FilaMed
```

### Compile

```bash
./mvnw clean install
```

### Execute

```bash
./mvnw spring-boot:run
```

---

# ⚙️ Configuração do Banco

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/filamed

spring.datasource.username=root

spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
```

---

# 📄 Documentação da API

Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📈 Roadmap

## ✅ Concluído

- Cadastro de Pacientes
- Cadastro de Médicos
- Cadastro de Especialidades
- Cadastro de Consultas
- Fila de Atendimento
- Consulta por Paciente
- Tempo Estimado
- Posição na Fila

## 🚧 Em Desenvolvimento

- Dashboard da Recepção
- Aplicativo Android
- Relatórios
- Estatísticas
- Login e Autenticação

---

# 👨‍💻 Autor

## Vagner Domingos da Silva

Projeto desenvolvido para estudos em desenvolvimento Backend utilizando Java, Spring Boot e arquitetura em camadas.

---

# ⭐ Se este projeto foi útil para você...

Considere deixar uma estrela ⭐ no repositório.