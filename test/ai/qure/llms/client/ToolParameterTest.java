package ai.qure.llms.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

public class ToolParameterTest {
    
    @Test
    public void testConstructor() {
        ToolParameter param = new ToolParameter(ParameterType.STRING, "Test description");
        assertEquals(ParameterType.STRING, param.getType());
        assertEquals("Test description", param.getDescription());
    }

    @Test
    public void testSetters() {
        ToolParameter param = new ToolParameter(ParameterType.STRING, "Initial description");
        
        param.setType(ParameterType.NUMBER);
        assertEquals(ParameterType.NUMBER, param.getType());
        
        param.setDescription("Updated description");
        assertEquals("Updated description", param.getDescription());
    }

    @Test
    public void testToMap() {
        ToolParameter param = new ToolParameter(ParameterType.BOOLEAN, "Test boolean parameter");
        Map<String, Object> map = param.toMap();
        
        assertEquals("boolean", map.get("type"));
        assertEquals("Test boolean parameter", map.get("description"));
        assertEquals(2, map.size()); // Only type and description should be present
    }

    @Test
    public void testAllParameterTypes() {
        for (ParameterType type : ParameterType.values()) {
            ToolParameter param = new ToolParameter(type, "Test " + type.getValue());
            assertEquals(type, param.getType());
            assertEquals("Test " + type.getValue(), param.getDescription());
            
            Map<String, Object> map = param.toMap();
            assertEquals(type.getValue(), map.get("type"));
        }
    }
} 