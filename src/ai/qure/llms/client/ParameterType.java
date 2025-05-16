package ai.qure.llms.client;

/**
 * Represents the possible types for tool parameters
 */
public enum ParameterType {
    STRING("string"),
    NUMBER("number"),
    INTEGER("integer"),
    BOOLEAN("boolean");

    private final String value;

    ParameterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
} 