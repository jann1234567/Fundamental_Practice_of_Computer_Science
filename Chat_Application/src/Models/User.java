package Models;

import java.util.List;

public class User {
    private String username;
    private String status;
    private List<Integer> groupConversationIds;
    private List<Integer> privateConversationIds;

    // Constructor
    public User(String username, String status, List<Integer> groupConversationIds, List<Integer> privateConversationIds) {
        this.username = username;
        this.status = status;
        this.groupConversationIds = groupConversationIds;
        this.privateConversationIds = privateConversationIds;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public List<Integer> getPrivateConversationIds() {
        return privateConversationIds;
    }

    public List<Integer> getGroupConversationIds() {
        return groupConversationIds;
    }
}
