package ai.qure.llms.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.ArrayList;

public class OpenAIAPIClientTest {
    private static OpenAIAPIClient client;
    
    @BeforeAll
    public static void setup() {
        // Load environment variables
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .systemProperties()
                .directory(".")
                .load();
        
        String apiKey = dotenv.get("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            fail("OPENAI_API_KEY environment variable or .env file is required for tests");
        }
        
        String model = dotenv.get("OPENAI_MODEL", "gpt-3.5-turbo");
        client = new OpenAIAPIClient(model, apiKey);
        
        // Set custom API base URL if specified
        String baseUrl = dotenv.get("OPENAI_API_BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            client.setBaseUrl(baseUrl);
        }
    }
    
    @Test
    public void testSimplePrompt() throws Exception {
        ApiRequest request = new ApiRequest("What is 2+2?");
        ApiResponse response = client.generateResponse(request);
        
        assertNotNull(response);
        assertNotNull(response.getContent());
        assertTrue(response.getContent().contains("4"));
        assertEquals(Message.Role.ASSISTANT, response.getRole());
    }
    
    @Test
    public void testConversationHistory() throws Exception {
        ArrayList<Message> history = new ArrayList<>();
        history.add(new Message(Message.Role.USER, "My name is Alice"));
        history.add(new Message(Message.Role.ASSISTANT, "Hello Alice! How can I help you today?"));
        
        ApiRequest request = new ApiRequest(history, "What's my name?");
        ApiResponse response = client.generateResponse(request);
        
        assertNotNull(response);
        assertNotNull(response.getContent());
        assertTrue(response.getContent().toLowerCase().contains("alice"));
        assertEquals(Message.Role.ASSISTANT, response.getRole());
    }
    
    @Test
    public void testSystemMessage() throws Exception {
        ArrayList<Message> history = new ArrayList<>();
        history.add(new Message(Message.Role.SYSTEM, "You are a helpful assistant that always responds in French."));
        
        ApiRequest request = new ApiRequest(history, "Hello, how are you?");
        ApiResponse response = client.generateResponse(request);
        
        assertNotNull(response);
        assertNotNull(response.getContent());
        // Note: We can't assert specific French words as the response may vary
        assertEquals(Message.Role.ASSISTANT, response.getRole());
    }
    
    @Test
    public void testModelChange() throws Exception {
        String originalModel = client.getModel();
        
        // Test with a different model
        client.setModel("gpt-3.5-turbo");
        ApiRequest request = new ApiRequest("What is 2+2?");
        ApiResponse response = client.generateResponse(request);
        
        assertNotNull(response);
        assertNotNull(response.getContent());
        assertTrue(response.getContent().contains("4"));
        
        // Restore original model
        client.setModel(originalModel);
    }
} 