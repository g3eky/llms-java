package ai.qure.llms.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to the LLM API
 */
public class ApiRequest {
    private List<Message> history;
    private String inputToken;

    public ApiRequest() {
        this.history = new ArrayList<>();
    }

    public ApiRequest(String inputToken) {
        this.history = new ArrayList<>();
        this.inputToken = inputToken;
    }

    public ApiRequest(List<Message> history, String inputToken) {
        this.history = history;
        this.inputToken = inputToken;
    }

    public List<Message> getHistory() {
        return history;
    }

    public void setHistory(List<Message> history) {
        this.history = history;
    }

    public String getInputToken() {
        return inputToken;
    }

    public void setInputToken(String inputToken) {
        this.inputToken = inputToken;
    }

    public void addMessage(Message message) {
        this.history.add(message);
    }
} 