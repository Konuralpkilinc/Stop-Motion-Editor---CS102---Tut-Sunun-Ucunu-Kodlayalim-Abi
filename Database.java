import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


/**
 * This class creates a database to store uesr and project data
 * and provides methods which enable the application to interact with database.
 * @author Emir Tuglu
 */

public class Database {
    private Connection conn;
    private String url;

    public Database() {
        this.conn = null;
        this.url = "jdbc:sqlite:db.db";    // Database connection URL

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println("Connection error");
        }
    }

    /**
     * This method takes username and password strings, then check if there is such a user in the database.
     * @return true if username and password matches, false in all other cases
     * @throws SQLException
     */
    public boolean isUserExist (String aUsername, String aPassword) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement( "SELECT password FROM USERS WHERE username = '?'");
        pstmt.setString( 1, aUsername);
        ResultSet rs = pstmt.executeQuery();

        // Return true if there is a user with given username and password
        return rs.getString( "password").equals( aPassword);
    }


    /**
     * This method creates a project from the images in the given filepath. 
     * Also, sets relations between the user, project and images.
     * @param filepath filepath which includes images
     * @param username username of the user who creates this project
     * @param projectName the name that created project will take
     */
    public void createProject (String filepath, String username, String projectName) {
        // Creating a new project in database
        addProjectToDB(username, projectName);

        // Creating a File object with given filepath
        File file = new File(filepath);

        // Specifying the supported extensions
        String[] extensions = new String[] { "jpg", "jpeg", "png"}; 

        // Filter to identify images based on their extensions
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                for (String ext : extensions) {
                    if (name.endsWith("." + ext)) {
                        return true;
                    }
                }
                return false;
            }
        };


        if (file.isDirectory()) {   // Making sure it is a directory
            for (File f : file.listFiles( filter)) {   // Reading each image from the directory
                BufferedImage img = null;

                try {
                    img = ImageIO.read(f);
                    Image fxImage = SwingFXUtils.toFXImage(img, null);
                    saveImageToDatabase(fxImage, username, projectName);

                } catch (IOException e) {
                    System.out.println("Cannot read from the file!");
                }
            }
        }
    }

    /**
     * This method takes username and projectname, then create a new project which belongs to given username in the database . 
     * @param username username
     * @param projectName the name that created project will take
     */
    private void addProjectToDB (String username, String projectName) {
        try {
            // Creating a new project in the database with given projectName
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Projects (name) VALUES (?)");
            pstmt.setString(1, projectName);
            pstmt.executeUpdate();

            // Getting user ID
            int userID = getUserIDFromUsername(username);
            // Getting project ID
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM Projects ORDER BY id ASC LIMIT 1"); // Getting highest id in projects table since we have just added a new project
            int projectID = rs.getInt("id");

            // Establishing the relation between the project and the user
            PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO User_Project_Join (user_id, project_id) VALUES (?, ?)");
            pstmt2.setInt(1, userID);
            pstmt2.setInt(2, projectID);
            } 
            catch (SQLException e) {
                //TODO: handle exception
            }
    }

    private int getUserIDFromUsername (String projectName) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT id FROM Projects WHERE username = " + projectName);
        return rs.getInt("id");
    }

    public void saveImageToDatabase (Image img, String username, String projectName) {
        // TODO
        // Serialize img
    }
}