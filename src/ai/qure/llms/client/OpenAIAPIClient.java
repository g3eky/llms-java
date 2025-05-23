package ai.qure.llms.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Implementation of LLMApiClient for OpenAI API
 */
public class OpenAIAPIClient implements LLMApiClient {
    private final HttpClient httpClient;
    private String apiKey;
    private String model;
    private String baseUrl = "https://api.openai.com/v1";
    
    /**
     * Creates a new OpenAIAPIClient with the specified model and API key
     * 
     * @param model the OpenAI model to use (e.g., "gpt-4.1")
     * @param apiKey the OpenAI API key for authentication
     */
    public OpenAIAPIClient(String model, String apiKey) {
        this.model = model;
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(30))
                .build();
    }
    
    @Override
    public ApiResponse generateResponse(ApiRequest request) throws Exception {
        // Using chat completions API
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", this.model);
        
        // Set up messages for the chat completion
        JSONArray messages = new JSONArray();
        
        // Add history messages
        for (Message message : request.getHistory()) {
            JSONObject messageObj = new JSONObject();
            messageObj.put("role", message.getRole().toString().toLowerCase());
            messageObj.put("content", message.getContent());
            messages.put(messageObj);
        }
        
        // Add current input as a user message
        if (request.getInputToken() != null && !request.getInputToken().isEmpty()) {
            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", request.getInputToken());
            messages.put(message);
        }
        
        requestBody.put("messages", messages);
        
        // Adding common optional parameters
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 150);
        
        String fullUrl = baseUrl + "/chat/completions";
        
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();
        
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("API request failed with status code: " + response.statusCode() + 
                    " and body: " + response.body());
        }
        
        // Parse response for chat completion format
        JSONObject jsonResponse = new JSONObject(response.body());
        JSONArray choices = jsonResponse.getJSONArray("choices");
        if (choices.length() > 0) {
            JSONObject choice = choices.getJSONObject(0);
            JSONObject responseMessage = choice.getJSONObject("message");
            String content = responseMessage.getString("content");
            String role = responseMessage.getString("role");
            
            // Convert OpenAI role to our Role enum
            Message.Role messageRole = Message.Role.ASSISTANT; // Default
            if ("user".equalsIgnoreCase(role)) {
                messageRole = Message.Role.USER;
            } else if ("system".equalsIgnoreCase(role)) {
                messageRole = Message.Role.SYSTEM;
            }
            
            return new ApiResponse(content, messageRole);
        } else {
            return new ApiResponse(jsonResponse.toString(), Message.Role.ASSISTANT);
        }
    }
    
    /**
     * Configures the API key for authentication
     * 
     * @param apiKey The API key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    /**
     * Sets the base URL for the API
     * 
     * @param baseUrl The base URL for the API (default: "https://api.openai.com/v1")
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    /**
     * Sets the model to use for requests
     * 
     * @param model The model name (e.g., "gpt-4.1")
     */
    public void setModel(String model) {
        this.model = model;
    }
    
    /**
     * Gets the currently configured model
     * 
     * @return The model name
     */
    public String getModel() {
        return this.model;
    }
} 