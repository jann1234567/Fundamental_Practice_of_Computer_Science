package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversationDAO {
    private Connection connection;

    public ConversationDAO(Connection connection) {
        this.connection = connection;
    }

    public String getConversationType(int conversationId) {
        String conversation_type = null;
        try {
            String query = "SELECT conversation_type FROM conversations WHERE conversation_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, conversationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                conversation_type = resultSet.getString("conversation_type");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversation_type;
    }

    public void createConversation(String conversation_type) {
        try {
            String query = "INSERT INTO conversations (conversation_type) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, conversation_type);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteConversation(int conversationId) {
        try {
            String query = "DELETE FROM conversations WHERE conversation_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, conversationId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
