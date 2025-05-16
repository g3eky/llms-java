package ai.qure.llms.client;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents a parameter of a tool function
 */
public class ToolParameter {
    private ParameterType type;
    private String description;

    public ToolParameter(ParameterType type, String description) {
        this.type = type;
        this.description = description;
    }

    public ParameterType getType() {
        return type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("type", type.getValue());
        params.put("description", description);
        return params;
    }
} 