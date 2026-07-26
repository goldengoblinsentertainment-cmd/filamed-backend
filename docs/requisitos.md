# Requisitos do Sistema - FilaMed

## Objetivo

O FilaMed é um sistema para gerenciamento digital de clínicas e consultórios médicos.

Seu objetivo é reduzir filas físicas, organizar atendimentos e permitir que pacientes acompanhem sua posição na fila em tempo real.

---

# Perfis de Usuário

O sistema possuirá quatro perfis principais.

## Paciente

Pode:

- Criar conta
- Fazer login
- Atualizar cadastro
- Tirar foto pelo aplicativo
- Responder a anamnese
- Entrar na fila
- Acompanhar sua posição
- Receber notificações
- Consultar histórico de atendimentos

---

## Médico

Pode:

- Fazer login
- Visualizar fila
- Chamar paciente
- Visualizar anamnese
- Registrar atendimento
- Finalizar consulta

---

## Recepcionista

Pode:

- Cadastrar pacientes
- Atualizar dados
- Inserir paciente na fila
- Cancelar atendimento
- Consultar fila

---

## Administrador

Pode:

- Gerenciar usuários
- Gerenciar médicos
- Gerenciar perguntas da anamnese
- Gerenciar especialidades
- Visualizar estatísticas
- Gerenciar permissões

---

# Funcionalidades

## Cadastro de Pacientes

- Nome
- CPF
- RG
- Data de nascimento
- Sexo
- Telefone
- E-mail
- Endereço
- Convênio
- Foto

---

## Anamnese

O paciente responderá um questionário antes de entrar na fila.

As perguntas poderão ser criadas e alteradas pelo administrador.

---

## Fila Digital

O sistema deverá:

- Mostrar posição atual
- Mostrar quantidade de pessoas na frente
- Estimar tempo de espera
- Atualizar automaticamente

---

## Atendimento

O médico poderá:

- Chamar próximo paciente
- Iniciar consulta
- Finalizar consulta
- Registrar observações

---

## Notificações

O paciente receberá notificações quando:

- Entrar na fila
- Estiver próximo do atendimento
- For chamado pelo médico