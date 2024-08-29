package Server;

import java.time.LocalDateTime;

public class Message {
    private String content;
    private String sender;
    private int belongedConversation;
    private LocalDateTime timestamp; // Changed from DateTime to LocalDateTime

    public Message(String content, String sender, int belongedConversation) {
        this.content = content;
        this.sender = sender;
        this.belongedConversation = belongedConversation;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public int getBelongedConversation() {
        return belongedConversation;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}