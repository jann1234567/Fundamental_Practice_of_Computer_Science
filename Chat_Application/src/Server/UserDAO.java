package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private DatabaseManager dbManager;

    // Constructor
    public UserDAO(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    // Example method: Retrieve a user by username
    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Assuming User has a constructor that takes these parameters
                    user = new User(
                        rs.getString("username"),
                        rs.getString("status"),
                        rs.getString("conversation_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Example method: Update user status
    public void updateUserStatus(String username, boolean isOnline) {
        String query = "UPDATE users SET status = ? WHERE username = ?";

        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, isOnline ? "ONLINE" : "OFFLINE");
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
