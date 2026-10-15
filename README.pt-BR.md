<div align="center">
  <h1>Java Budgeting</h1>
</div>

<p align="center">
  <a href="https://www.java.com" rel="noreferrer" target="_blank">
    <img alt="Java 25" src="https://img.shields.io/badge/Java%2025-ED8B00?style=flat&logo=openjdk&logoColor=white">
  </a>
</p>

<div align="center">
  <h4>Leia em outro idioma</h4>
  <a href="README.md" hreflang="en">🇺🇸 English</a>
</div>

## 💡 Sobre

Java Budgeting é uma aplicação Spring Boot para gerenciamento de transações financeiras. Ela oferece uma API REST para registrar transações e listá-las por categoria, além de um fluxo com IA que transcreve uma despesa falada, registra a transação e retorna uma resposta em áudio.

## 🌟 Funcionalidades

- **Gerenciamento de transações:** criar transações e listá-las por categoria.
- **Registro de despesas por voz:** transcrever uma gravação de áudio, usar um assistente de IA para registrar a transação e receber a resposta em áudio MP3.
- **Endpoints de IA:** conversar com um modelo OpenAI, transcrever áudio e sintetizar fala.

## 🚀 Como começar

### Pré-requisitos

- Java 25
- Docker Engine com Docker Compose
- Uma chave de API da OpenAI para os endpoints de IA e áudio

### ⚙️ Como executar

1. Clone o repositório:

   ```bash
   git clone <url-do-repositorio>
   cd java-budgeting
   ```

2. Configure a chave da API da OpenAI no ambiente:

   ```bash
   # macOS/Linux
   export OPENAI_API_KEY=<sua-chave-da-openai>

   # Windows PowerShell
   $env:OPENAI_API_KEY = "<sua-chave-da-openai>"
   ```

3. Inicie a aplicação:

   ```bash
   # macOS/Linux
   ./gradlew bootRun

   # Windows
   .\gradlew.bat bootRun
   ```

A integração do Spring Boot com Docker Compose inicia o serviço MySQL definido em `compose.yml`. A aplicação fica disponível em **http://localhost:8081**.

### API

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `POST` | `/transactions` | Cria uma transação com `description`, `category` (`AUTO`, `GROCERIES` ou `PHARMA`) e `amount`. |
| `GET` | `/transactions/{category}` | Lista as transações da categoria selecionada. |
| `POST` | `/transactions/ai` | Envia um arquivo de áudio no campo multipart `file`; transcreve, registra a despesa com o assistente de IA e retorna um MP3. |
| `GET` | `/api/chat?prompt={texto}` | Envia um prompt ao cliente de chat. |
| `GET` | `/api/chat-model?prompt={texto}` | Envia um prompt diretamente ao modelo de chat da OpenAI. |
| `POST` | `/api/transcribe` | Envia um arquivo de áudio no campo multipart `file` e recebe a transcrição. |
| `POST` | `/api/sinthesize` | Envia o JSON `{"text":"..."}` e recebe um áudio MP3. |

Exemplo de requisição para criar uma transação:

```http
POST http://localhost:8081/transactions
Content-Type: application/json

{
  "description": "Compras de mercado",
  "category": "GROCERIES",
  "amount": 80
}
```

Para testar os endpoints, você pode:
- Usar os exemplos em [`requests.http`](requests.http) com a extensão REST Client do Visual Studio Code
- Use qualquer cliente HTTP de sua escolha, como Insomnia, Postman, etc.

### Testes

Execute a suíte de testes com:

```bash
# macOS/Linux
./gradlew test

# Windows
.\gradlew.bat test
```

Os testes de integração com a OpenAI são executados somente quando `OPENAI_API_KEY` está configurada.

## 🛠️ Tecnologias

- **Linguagem:** Java 25
- **Framework:** Spring Boot 4.1, Spring Web
- **IA:** Spring AI com modelos OpenAI para chat, transcrição e conversão de texto em fala
- **Persistência:** Spring Data JPA e MySQL
- **Build e testes:** Gradle 9.2, JUnit 5
- **Ambiente de desenvolvimento:** Docker Compose

## 🏛️ Arquitetura

A aplicação separa a lógica de transações nas camadas de domínio, aplicação e infraestrutura. Os controllers REST recebem as requisições e delegam o processamento aos casos de uso; estes operam sobre o domínio de transações e uma interface de repositório, implementada com Spring Data JPA e MySQL. O endpoint de transações com IA combina transcrição de áudio, um cliente de chat com ferramentas de transação e síntese de fala.

---
Feito com ❤️ por Guilherme Bezerra 👋 [Get in touch!](https://www.linkedin.com/in/gbdsantos)
