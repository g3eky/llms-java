package ai.qure.llms.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ApiRequestTest {

    @Test
    public void testDefaultConstructor() {
        ApiRequest request = new ApiRequest();
        assertNotNull(request.getHistory());
        assertTrue(request.getHistory().isEmpty());
        assertNull(request.getInputToken());
    }

    @Test
    public void testConstructorWithInputToken() {
        String inputToken = "Test input";
        ApiRequest request = new ApiRequest(inputToken);
        
        assertNotNull(request.getHistory());
        assertTrue(request.getHistory().isEmpty());
        assertEquals(inputToken, request.getInputToken());
    }

    @Test
    public void testConstructorWithHistoryAndInputToken() {
        List<Message> history = new ArrayList<>();
        history.add(new Message(Message.Role.USER, "Hello"));
        String inputToken = "Test input";
        
        ApiRequest request = new ApiRequest(history, inputToken);
        
        assertSame(history, request.getHistory());
        assertEquals(1, request.getHistory().size());
        assertEquals(inputToken, request.getInputToken());
    }

    @Test
    public void testSettersAndGetters() {
        ApiRequest request = new ApiRequest();
        
        String inputToken = "Updated input";
        request.setInputToken(inputToken);
        assertEquals(inputToken, request.getInputToken());
        
        List<Message> history = new ArrayList<>();
        history.add(new Message(Message.Role.SYSTEM, "System message"));
        request.setHistory(history);
        assertSame(history, request.getHistory());
    }

    @Test
    public void testAddMessage() {
        ApiRequest request = new ApiRequest();
        
        Message message1 = new Message(Message.Role.USER, "First message");
        request.addMessage(message1);
        assertEquals(1, request.getHistory().size());
        assertSame(message1, request.getHistory().get(0));
        
        Message message2 = new Message(Message.Role.ASSISTANT, "Second message");
        request.addMessage(message2);
        assertEquals(2, request.getHistory().size());
        assertSame(message2, request.getHistory().get(1));
    }
} 