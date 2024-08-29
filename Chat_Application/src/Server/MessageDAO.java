package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private Connection connection;

    public MessageDAO(Connection connection) {
        this.connection = connection;
    }

    public void createMessage(String content, String sender, int belongedConversation) {
        try {
            String query = "INSERT INTO messages (content, sender, belonged_conversation) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, content);
            statement.setString(2, sender);
            statement.setInt(3, belongedConversation);
            statement.executeUpdate();
            statement.close();
            System.out.println("Message created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all messages from a conversation (history messages)
    public List<Message> getConversationMessagesByConverationID(int conversationId) {
        List<Message> messages = new ArrayList<>();
    
        try {
            String query = "SELECT * FROM messages WHERE belonged_conversation = ? ORDER BY timestamp ASC";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, conversationId);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                String sender = resultSet.getString("sender");
                int belongedConversation = resultSet.getInt("belonged_conversation");
    
                Message message = new Message(content, sender, belongedConversation);
                messages.add(0, message); // Add the message at the beginning of the list
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return messages;
    }
}
