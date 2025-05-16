package ai.qure.llms.client;

/**
 * Interface for a conversational chatbot
 */
public interface Chatbot {
    
    /**
     * Starts an interactive chat session
     * Takes user input and returns chatbot responses until user exits
     */
    void startChat();
} 