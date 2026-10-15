<div align="center">
  <h1>Java Budgeting</h1>
</div>

<p align="center">
  <a href="https://www.java.com" rel="noreferrer" target="_blank">
    <img alt="Java 25" src="https://img.shields.io/badge/Java%2025-ED8B00?style=flat&logo=openjdk&logoColor=white">
  </a>
</p>

<div align="center">
  <h4>Read this in another language</h4>
  <a href="README.pt-BR.md" hreflang="pt-br">🇧🇷 Português</a>
</div>

## 💡 About

Java Budgeting is a Spring Boot application for managing financial transactions. It provides a REST API to register transactions and list them by category, with an AI-powered flow that transcribes a spoken expense, records it, and returns an audio response.

## 🌟 Features

- **Transaction management:** create transactions and list them by category.
- **Voice-based expense entry:** transcribe an audio recording, use an AI assistant to register the transaction, and receive the response as MP3 audio.
- **AI endpoints:** chat with an OpenAI model, transcribe audio, and synthesize speech.

## 🚀 Getting Started

### Prerequisites

- Java 25
- Docker Engine with Docker Compose
- An OpenAI API key for the AI and audio endpoints

### ⚙️ How to Run

1. Clone the repository:

```bash
git clone <repository-url>
cd java-budgeting
```

2. Set your OpenAI API key in the environment:

```bash
# macOS/Linux
export OPENAI_API_KEY=<your-openai-api-key>

# Windows PowerShell
$env:OPENAI_API_KEY = "<your-openai-api-key>"
```

3. Start the application:

```bash
# macOS/Linux
./gradlew bootRun

# Windows
.\gradlew.bat bootRun
```

Spring Boot's Docker Compose integration starts the MySQL service defined in `compose.yml`. The application listens on **http://localhost:8081**.

### API

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/transactions` | Create a transaction with `description`, `category` (`AUTO`, `GROCERIES`, or `PHARMA`), and `amount`. |
| `GET` | `/transactions/{category}` | List transactions in the selected category. |
| `POST` | `/transactions/ai` | Upload an audio file as multipart field `file`; transcribe it, register the expense through the AI assistant, and return an MP3. |
| `GET` | `/api/chat?prompt={text}` | Send a prompt to the chat client. |
| `GET` | `/api/chat-model?prompt={text}` | Send a prompt directly to the OpenAI chat model. |
| `POST` | `/api/transcribe` | Upload an audio file as multipart field `file` and receive its transcription. |
| `POST` | `/api/sinthesize` | Send JSON `{"text":"..."}` and receive MP3 audio. |

Example transaction request:

```http
POST http://localhost:8081/transactions
Content-Type: application/json

{
  "description": "Groceries",
  "category": "GROCERIES",
  "amount": 80
}
```

You can also use the examples in [`requests.http`](requests.http) with the REST Client extension for Visual Studio Code, or any HTTP client such as Insomnia or Postman.

### Tests

Run the test suite with:

```bash
# macOS/Linux
./gradlew test

# Windows
.\gradlew.bat test
```

The OpenAI integration tests run only when `OPENAI_API_KEY` is set.

## 🛠️ Tech Stack

- **Language:** Java 25
- **Framework:** Spring Boot 4.1, Spring Web
- **AI:** Spring AI with OpenAI chat, transcription, and text-to-speech models
- **Persistence:** Spring Data JPA and MySQL
- **Build and tests:** Gradle 9.2, JUnit 5
- **Development environment:** Docker Compose

## 🏛️ Architecture

The application separates transaction logic into domain, application, and infrastructure layers. REST controllers receive requests and delegate to application use cases; those use cases operate on the transaction domain and repository interface, implemented with Spring Data JPA and MySQL. The AI transaction endpoint combines audio transcription, a chat client with transaction tools, and speech synthesis.

---
Made with ❤️ by Guilherme Bezerra 👋 [Get in touch!](https://www.linkedin.com/in/gbdsantos)