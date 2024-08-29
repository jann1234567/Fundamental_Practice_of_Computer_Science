package Server;

import java.util.List;

public class User {
    private String username;
    private String status;
    private List<String> conversationIds;

    // Constructor
    public User(String username, String status) {
        this.username = username;
        this.status = status;
        // this.conversationIds.add(conversationId);
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getConversationIds() {
        return conversationIds;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addConversationId(String conversationId) {
        conversationIds.add(conversationId);
    }
}
