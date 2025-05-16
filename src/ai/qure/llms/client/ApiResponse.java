package ai.qure.llms.client;

/**
 * Represents a response from the LLM API
 */
public class ApiResponse {
    private String content;
    private Message.Role role;

    public ApiResponse() {
        this.role = Message.Role.ASSISTANT;
    }

    public ApiResponse(String content) {
        this.content = content;
        this.role = Message.Role.ASSISTANT;
    }

    public ApiResponse(String content, Message.Role role) {
        this.content = content;
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message.Role getRole() {
        return role;
    }

    public void setRole(Message.Role role) {
        this.role = role;
    }

    public Message toMessage() {
        return new Message(role, content);
    }
} 