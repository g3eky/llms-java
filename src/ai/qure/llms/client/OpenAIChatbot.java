package ai.qure.llms.client;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Chatbot interface that uses OpenAI API
 */
public class OpenAIChatbot implements Chatbot {
    private final OpenAIAPIClient apiClient;
    private final List<Message> history = new ArrayList<>();
    
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
        
        try {
            while (true) {
                System.out.print("> ");
                String userInput = scanner.nextLine().trim();
                
                if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("quit")) {
                    System.out.println("Ending chat session. Goodbye!");
                    break;
                }
                
                try {
                    // Add user message to history
                    history.add(new Message(Message.Role.USER, userInput));
                    ApiRequest request = new ApiRequest(new ArrayList<>(history), null);
                    ApiResponse response = apiClient.generateResponse(request);
                    System.out.println("\nChatbot: " + response.getContent() + "\n");
                    // Add assistant response to history
                    history.add(new Message(response.getRole(), response.getContent()));
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
    }
} 