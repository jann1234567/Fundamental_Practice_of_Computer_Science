package DAOs;

import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void updateUserStatus(String username, String status) {
        String query = "UPDATE Users SET status = ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("status"), getAllGroupChatConversationIds(username), getAllPrivateChatConversationIds(username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        String query = "INSERT INTO Users (username, status) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllUsernames(String username) {
        List<String> usernames = new ArrayList<>();
        String query = "SELECT username FROM Users WHERE username != ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) { 
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    public List<Integer> getAllPrivateChatConversationIds(String username) {
        List<Integer> conversationIds = new ArrayList<>();
        String query = "SELECT c.conversation_id " +
                       "FROM conversations c " +
                       "JOIN users u ON c.conversation_id = u.involved_conversation " +
                       "WHERE u.username = ? AND c.conversation_type = 'PRIVATE'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                conversationIds.add(rs.getInt("conversation_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversationIds;
    }

    public List<Integer> getAllGroupChatConversationIds(String username) {
        List<Integer> conversationIds = new ArrayList<>();
        String query = "SELECT c.conversation_id " +
                       "FROM conversations c " +
                       "JOIN users u ON c.conversation_id = u.involved_conversation " +
                       "WHERE u.username = ? AND c.conversation_type = 'GROUP'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                conversationIds.add(rs.getInt("conversation_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversationIds;
    }
}
