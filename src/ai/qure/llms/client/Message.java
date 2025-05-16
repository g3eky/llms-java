package ai.qure.llms.client;

/**
 * Represents a message in the conversation history
 */
public class Message {
    public enum Role {
        USER,
        ASSISTANT,
        SYSTEM
    }

    private Role role;
    private String content;

    public Message(Role role, String content) {
        this.role = role;
        this.content = content;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
} 