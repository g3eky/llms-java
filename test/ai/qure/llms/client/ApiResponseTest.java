package ai.qure.llms.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApiResponseTest {

    @Test
    public void testDefaultConstructor() {
        ApiResponse response = new ApiResponse();
        
        assertNull(response.getContent());
        assertEquals(Message.Role.ASSISTANT, response.getRole());
    }
    
    @Test
    public void testConstructorWithContent() {
        String content = "Test response content";
        ApiResponse response = new ApiResponse(content);
        
        assertEquals(content, response.getContent());
        assertEquals(Message.Role.ASSISTANT, response.getRole());
    }
    
    @Test
    public void testConstructorWithContentAndRole() {
        String content = "Test response content";
        Message.Role role = Message.Role.SYSTEM;
        
        ApiResponse response = new ApiResponse(content, role);
        
        assertEquals(content, response.getContent());
        assertEquals(role, response.getRole());
    }
    
    @Test
    public void testSettersAndGetters() {
        ApiResponse response = new ApiResponse();
        
        String content = "Updated content";
        response.setContent(content);
        assertEquals(content, response.getContent());
        
        Message.Role role = Message.Role.USER;
        response.setRole(role);
        assertEquals(role, response.getRole());
    }
    
    @Test
    public void testToMessage() {
        String content = "Test content";
        Message.Role role = Message.Role.ASSISTANT;
        
        ApiResponse response = new ApiResponse(content, role);
        Message message = response.toMessage();
        
        assertNotNull(message);
        assertEquals(content, message.getContent());
        assertEquals(role, message.getRole());
    }
} 