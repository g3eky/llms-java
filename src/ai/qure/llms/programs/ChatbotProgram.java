package ai.qure.llms.programs;

import ai.qure.llms.client.OpenAIAPIClient;
import ai.qure.llms.client.OpenAIChatbot;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Program that starts an interactive OpenAI chatbot session
 */
public class ChatbotProgram {
    
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
        
        // Create an OpenAI API client with the specified model
        OpenAIAPIClient apiClient = new OpenAIAPIClient(defaultModel, apiKey);
        
        // Set custom API base URL if specified
        String baseUrl = dotenv.get("OPENAI_API_BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            apiClient.setBaseUrl(baseUrl);
        }
        
        System.out.println("Initializing OpenAI Chatbot with model: " + apiClient.getModel());
        
        // Create and start the chatbot
        OpenAIChatbot chatbot = new OpenAIChatbot(apiClient);
        chatbot.startChat();
    }
} 