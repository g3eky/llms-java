package ai.qure.llms.client;

/**
 * Interface for making calls to the LLM API
 */
public interface LLMApiClient {
    
    /**
     * Sends a request to LLM API and returns the response
     * 
     * @param request The API request containing conversation history and input
     * @return The response from the API
     * @throws Exception If an error occurs during the API call
     */
    ApiResponse generateResponse(ApiRequest request) throws Exception;
} 