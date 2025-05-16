package ai.qure.llms.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LLMApiClientTest {

    @Test
    public void testMockLLMApiClient() throws Exception {
        String expectedResponse = "Test response";
        MockLLMApiClient mockClient = new MockLLMApiClient(expectedResponse);
        
        ApiRequest request = new ApiRequest("Test input");
        request.addMessage(new Message(Message.Role.USER, "Hello"));
        
        ApiResponse response = mockClient.generateResponse(request);
        
        assertEquals(expectedResponse, response.getContent());
        assertEquals(Message.Role.ASSISTANT, response.getRole());
        assertEquals(1, mockClient.getCallCount());
        assertSame(request, mockClient.getLastRequest());
    }
    
    @Test
    public void testMultipleCalls() throws Exception {
        MockLLMApiClient mockClient = new MockLLMApiClient();
        
        // First call
        ApiRequest request1 = new ApiRequest("Input 1");
        ApiResponse response1 = mockClient.generateResponse(request1);
        assertEquals(1, mockClient.getCallCount());
        assertSame(request1, mockClient.getLastRequest());
        
        // Second call
        ApiRequest request2 = new ApiRequest("Input 2");
        ApiResponse response2 = mockClient.generateResponse(request2);
        assertEquals(2, mockClient.getCallCount());
        assertSame(request2, mockClient.getLastRequest());
        
        // Both responses should have the same content from the mock
        assertEquals(response1.getContent(), response2.getContent());
    }
    
    @Test
    public void testExceptionHandling() {
        MockLLMApiClient mockClient = new MockLLMApiClient();
        mockClient.setShouldThrowException(true);
        
        ApiRequest request = new ApiRequest("Test input");
        
        Exception exception = assertThrows(Exception.class, () -> {
            mockClient.generateResponse(request);
        });
        
        assertEquals("Mock exception", exception.getMessage());
        assertEquals(1, mockClient.getCallCount());
        assertSame(request, mockClient.getLastRequest());
    }
} 