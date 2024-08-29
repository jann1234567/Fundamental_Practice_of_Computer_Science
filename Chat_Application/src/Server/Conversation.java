package Server;

public class Conversation {
    private int conversationId;
    private String conversationType;

    public Conversation(int conversationId, String conversationType) {
        this.conversationId = conversationId;
        this.conversationType = conversationType;
    }

    public int getConversationId() {
        return conversationId;
    }

    public String getConversationType() {
        return conversationType;
    }
}
