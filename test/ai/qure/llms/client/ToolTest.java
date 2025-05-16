package ai.qure.llms.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

public class ToolTest {
    
    @Test
    public void testConstructor() {
        Tool tool = new Tool("function", "test_tool", "Test tool description");
        assertEquals("function", tool.getType());
        assertEquals("test_tool", tool.getName());
        assertEquals("Test tool description", tool.getDescription());
        assertTrue(tool.getParameters().isEmpty());
    }

    @Test
    public void testDefaultConstructor() {
        Tool tool = new Tool();
        assertNull(tool.getType());
        assertNull(tool.getName());
        assertNull(tool.getDescription());
        assertTrue(tool.getParameters().isEmpty());
    }

    @Test
    public void testSetters() {
        Tool tool = new Tool();
        
        tool.setType("function");
        assertEquals("function", tool.getType());
        
        tool.setName("test_tool");
        assertEquals("test_tool", tool.getName());
        
        tool.setDescription("Test description");
        assertEquals("Test description", tool.getDescription());
    }

    @Test
    public void testAddParameter() {
        Tool tool = new Tool("function", "test_tool", "Test tool description");
        
        ToolParameter param1 = new ToolParameter(ParameterType.STRING, "First parameter");
        ToolParameter param2 = new ToolParameter(ParameterType.NUMBER, "Second parameter");
        
        tool.addParameter(param1);
        tool.addParameter(param2);
        
        List<ToolParameter> parameters = tool.getParameters();
        assertEquals(2, parameters.size());
        assertEquals(param1, parameters.get(0));
        assertEquals(param2, parameters.get(1));
    }

    @Test
    public void testSetParameters() {
        Tool tool = new Tool("function", "test_tool", "Test tool description");
        List<ToolParameter> params = List.of(
            new ToolParameter(ParameterType.STRING, "First parameter"),
            new ToolParameter(ParameterType.NUMBER, "Second parameter")
        );
        
        tool.setParameters(params);
        assertEquals(params, tool.getParameters());
    }

    @Test
    public void testToFunctionMap() {
        Tool tool = new Tool("function", "test_tool", "Test tool description");
        tool.addParameter(new ToolParameter(ParameterType.STRING, "First parameter"));
        tool.addParameter(new ToolParameter(ParameterType.NUMBER, "Second parameter"));
        
        Map<String, Object> functionMap = tool.toFunctionMap();
        
        assertEquals("test_tool", functionMap.get("name"));
        assertEquals("Test tool description", functionMap.get("description"));
        
        Map<String, Object> params = (Map<String, Object>) functionMap.get("parameters");
        assertEquals("object", params.get("type"));
        
        Map<String, Object> properties = (Map<String, Object>) params.get("properties");
        assertEquals(2, properties.size());
        
        Map<String, Object> param1 = (Map<String, Object>) properties.get("param1");
        assertEquals("string", param1.get("type"));
        assertEquals("First parameter", param1.get("description"));
        
        Map<String, Object> param2 = (Map<String, Object>) properties.get("param2");
        assertEquals("number", param2.get("type"));
        assertEquals("Second parameter", param2.get("description"));
    }
} 