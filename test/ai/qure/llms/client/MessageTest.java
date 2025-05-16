package ai.qure.llms.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testConstructor() {
        Message.Role role = Message.Role.USER;
        String content = "Test content";
        
        Message message = new Message(role, content);
        
        assertEquals(role, message.getRole());
        assertEquals(content, message.getContent());
    }
    
    @Test
    public void testSettersAndGetters() {
        Message message = new Message(Message.Role.USER, "Initial content");
        
        // Test setRole and getRole
        Message.Role newRole = Message.Role.ASSISTANT;
        message.setRole(newRole);
        assertEquals(newRole, message.getRole());
        
        // Test setContent and getContent
        String newContent = "Updated content";
        message.setContent(newContent);
        assertEquals(newContent, message.getContent());
    }
    
    @Test
    public void testRoleEnum() {
        // Verify all expected Role enum values exist
        assertEquals(3, Message.Role.values().length);
        assertNotNull(Message.Role.USER);
        assertNotNull(Message.Role.ASSISTANT);
        assertNotNull(Message.Role.SYSTEM);
    }
} 