package ai.qure.llms.client;

/**
 * Interface for making calls to the LLM API
 */
public interface LLMClient {
    
    /**
     * Sends a request to LLM API and returns the response
     * 
     * @param model The model to use (e.g., "gpt-4.1")
     * @param input The input text/prompt to send to the model
     * @return The response from the API
     * @throws Exception If an error occurs during the API call
     */
    String generateResponse(String model, String input) throws Exception;
} 