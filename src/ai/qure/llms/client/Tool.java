package ai.qure.llms.client;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a tool that can be used by the LLM
 */
public class Tool {
    private String type;
    private String name;
    private String description;
    private List<ToolParameter> parameters;

    public Tool() {
        this.parameters = new ArrayList<>();
    }

    public Tool(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.parameters = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ToolParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ToolParameter> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(ToolParameter parameter) {
        this.parameters.add(parameter);
    }

    public Map<String, Object> toFunctionMap() {
        Map<String, Object> function = new HashMap<>();
        function.put("name", name);
        function.put("description", description);
        
        Map<String, Object> params = new HashMap<>();
        params.put("type", "object");
        
        Map<String, Object> properties = new HashMap<>();
        for (int i = 0; i < parameters.size(); i++) {
            ToolParameter param = parameters.get(i);
            properties.put("param" + (i + 1), param.toMap());
        }
        params.put("properties", properties);
        
        function.put("parameters", params);
        return function;
    }
} 