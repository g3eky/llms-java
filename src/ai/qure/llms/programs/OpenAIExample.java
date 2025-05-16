package ai.qure.llms.programs;

import ai.qure.llms.client.OpenAIAPIClient;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Example demonstrating how to use the OpenAIAPIClient
 */
public class OpenAIExample {
    
    public static void main(String[] args) {
        // Load environment variables from .env file with priority over system environment variables
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .systemProperties()  // Load from system properties
                .directory(".")      // Look in current directory
                .load();
        
        // Retrieve API key from environment variables or .env file
        String apiKey = dotenv.get("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Please set the OPENAI_API_KEY environment variable or in .env file");
            System.exit(1);
        }
        
        // Get the default model or use a fallback
        String defaultModel = dotenv.get("OPENAI_MODEL", "gpt-4.1");
        
        // Create a client with the specified model
        OpenAIAPIClient client = new OpenAIAPIClient(defaultModel, apiKey);
        
        // Set custom API base URL if specified
        String baseUrl = dotenv.get("OPENAI_API_BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            client.setBaseUrl(baseUrl);
        }
        
        // Example prompt
        String prompt = "Write a one-sentence bedtime story about a unicorn.";
        
        try {
            // Synchronous API call using the model from constructor
            System.out.println("Sending request to OpenAI...");
            String response = client.generateResponse(prompt);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.err.println("Error calling OpenAI API: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 