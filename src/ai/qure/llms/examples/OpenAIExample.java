package ai.qure.llms.examples;

import ai.qure.llms.client.LLMClient;
import ai.qure.llms.client.OpenAIClient;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Example demonstrating how to use the OpenAIClient
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
        OpenAIClient client = new OpenAIClient(defaultModel, apiKey);
        
        // Set custom API base URL if specified
        String baseUrl = dotenv.get("OPENAI_API_BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            client.setBaseUrl(baseUrl);
        }
        
        // Example prompt
        String prompt = "Write a one-sentence bedtime story about a unicorn.";
        
        try {
            // Synchronous API call
            System.out.println("Sending request to OpenAI...");
            String response = client.generateResponse(null, prompt);
            System.out.println("Response: " + response);
            
            // Example with a different model
            System.out.println("\nTrying with a different model...");
            response = client.generateResponse("gpt-3.5-turbo", prompt);
            System.out.println("Response: " + response);
            
        } catch (Exception e) {
            System.err.println("Error calling OpenAI API: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 