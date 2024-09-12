package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Message {
    private String content;
    private String sender;
    private int belongedConversation;
    private LocalDateTime timestamp; // Changed from DateTime to LocalDateTime

    public Message(String content, String sender, int belongedConversation, LocalDateTime timestamp) {
        this.content = content;
        this.sender = sender;
        this.belongedConversation = belongedConversation;
        this.timestamp = timestamp;
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