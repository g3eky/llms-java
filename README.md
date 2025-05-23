# LLMs Java Client

A simple Java client for making API calls to Large Language Models (LLMs).

## Overview

This project provides a clean interface for interacting with LLM APIs, with an implementation for OpenAI's API.

### Features

- Simple, synchronous API calling
- Configurable model selection
- JSON response parsing
- Error handling
- Environment variable management with dotenv-java

## Project Structure

```
src/
└── ai/qure/llms/
    ├── client/
    │   ├── LLMApiClient.java    # Interface for LLM clients
    │   └── OpenAIAPIClient.java  # OpenAI implementation
    └── programs/
        └── OpenAIAPIExample.java   # Usage example
```

## Requirements

- Java 17 or higher
- Maven 3.6 or higher
- An OpenAI API key

## How to Compile

1. Clone the repository
   ```
   git clone https://github.com/yourusername/llms-java.git
   cd llms-java
   ```

2. Compile with Maven
   ```
   mvn clean compile
   ```

## Configuration

The application can be configured through environment variables or a `.env` file.

1. Copy the example environment file
   ```
   cp .env.example .env
   ```

2. Edit the `.env` file to add your OpenAI API key
   ```
   # OpenAI API Key
   OPENAI_API_KEY=your_api_key_here

   # Default model (optional)
   OPENAI_MODEL=gpt-4.1

   # API Base URL (optional)
   # OPENAI_API_BASE_URL=https://api.openai.com/v1
   ```

## Running Tests

The project includes integration tests that make actual API calls to OpenAI. To run the tests:

1. Make sure you have set up your `.env` file with your OpenAI API key
2. Run the tests with Maven:
   ```
   mvn test
   ```

Note: The tests require a valid OpenAI API key to run. If you don't have an API key, the tests will fail.

## How to Run

1. After configuration, run the example
   ```
   mvn exec:java
   ```

   This uses the configuration in the pom.xml file to run the OpenAIAPIExample class.

2. Alternatively, you can specify the main class explicitly
   ```
   mvn exec:java -Dexec.mainClass="ai.qure.llms.programs.OpenAIAPIExample"
   ```

## Using the LLMApiClient

```java
// Load environment variables from .env file
Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

// Get API key and default model
String apiKey = dotenv.get("OPENAI_API_KEY");
String defaultModel = dotenv.get("OPENAI_MODEL", "gpt-4.1");

// Create a client with the specified model
OpenAIAPIClient client = new OpenAIAPIClient(defaultModel, apiKey);

// Optional: Set custom API base URL
String baseUrl = dotenv.get("OPENAI_API_BASE_URL");
if (baseUrl != null && !baseUrl.isEmpty()) {
    client.setBaseUrl(baseUrl);
}

// Set a prompt
String prompt = "Write a one-sentence bedtime story about a unicorn.";

try {
    // Make the API call using the model set in constructor
    String response = client.generateResponse(prompt);
    System.out.println("Response: " + response);
    
    // Or change the model and make another request
    client.setModel("gpt-3.5-turbo");
    response = client.generateResponse(prompt);
    System.out.println("Response: " + response);
    
} catch (Exception e) {
    System.err.println("Error: " + e.getMessage());
}
```

## License

This project is licensed under the MIT License - see the LICENSE file for details. 