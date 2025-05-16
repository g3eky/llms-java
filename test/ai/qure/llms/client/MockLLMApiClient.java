package ai.qure.llms.client;

/**
 * A mock implementation of LLMApiClient for testing
 */
public class MockLLMApiClient implements LLMApiClient {
    
    private final String fixedResponse;
    private int callCount;
    private ApiRequest lastRequest;
    private boolean shouldThrowException;
    
    public MockLLMApiClient() {
        this("Mock response");
    }
    
    public MockLLMApiClient(String fixedResponse) {
        this.fixedResponse = fixedResponse;
        this.callCount = 0;
        this.shouldThrowException = false;
    }
    
    @Override
    public ApiResponse generateResponse(ApiRequest request) throws Exception {
        callCount++;
        lastRequest = request;
        
        if (shouldThrowException) {
            throw new Exception("Mock exception");
        }
        
        return new ApiResponse(fixedResponse);
    }
    
    public String getFixedResponse() {
        return fixedResponse;
    }
    
    public int getCallCount() {
        return callCount;
    }
    
    public ApiRequest getLastRequest() {
        return lastRequest;
    }
    
    public void setShouldThrowException(boolean shouldThrowException) {
        this.shouldThrowException = shouldThrowException;
    }
} 