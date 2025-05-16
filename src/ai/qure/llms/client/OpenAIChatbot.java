package ai.qure.llms.client;

import java.util.Scanner;

/**
 * Implementation of Chatbot interface that uses OpenAI API
 */
public class OpenAIChatbot implements Chatbot {
    private final OpenAIAPIClient apiClient;
    
    /**
     * Creates a new OpenAIChatbot with the specified API client
     * 
     * @param apiClient The OpenAI API client to use
     */
    public OpenAIChatbot(OpenAIAPIClient apiClient) {
        this.apiClient = apiClient;
    }
    
    @Override
    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("OpenAI Chatbot started. Type 'exit' or 'quit' to end the conversation.");
        
        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine().trim();
            
            if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit")) {
                System.out.println("Ending chat session. Goodbye!");
                break;
            }
            
            try {
                String response = apiClient.generateResponse(userInput);
                System.out.println("\nChatbot: " + response + "\n");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
} 