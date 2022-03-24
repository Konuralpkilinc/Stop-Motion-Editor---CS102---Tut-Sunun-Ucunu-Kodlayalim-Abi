import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

/**
 * This class...
 */

public class Database {
    public Database() {
        String url = "jdbc:sqlite:db.db";    // Database connection URL
        Connection conn = null;
        try {
            // Connect to the database
            conn = DriverManager.getConnection(url);

            PreparedStatement insertUser = conn.prepareStatement( "INSERT INTO Users (username, password, avatar_id) VALUES (?, ?, ?)");
            PreparedStatement insertProject = conn.prepareStatement( "INSERT INTO Projects (name) VALUES (?)");
            PreparedStatement insertUserProjectJoin = conn.prepareStatement( "INSERT INTO User_Project_Join (user_id, project_id) VALUES (?, ?");
            PreparedStatement insertEditableImage = conn.prepareStatement( "INSERT INTO EditableImages (image, project_id) VALUES (?, ?)");
            PreparedStatement insertAvatar = conn.prepareStatement( "INSERT INTO Avatars (avatar) VALUES (?)");


        } catch (SQLException e) {
            
        }
    }
}