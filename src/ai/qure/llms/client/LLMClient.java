package ai.qure.llms.client;

/**
 * Interface for making calls to the LLM API
 */
public interface LLMClient {
    
    /**
     * Sends a request to LLM API and returns the response
     * 
     * @param input The input text/prompt to send to the model
     * @return The response from the API
     * @throws Exception If an error occurs during the API call
     */
    String generateResponse(String input) throws Exception;
} 