import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


/**
 * This class creates a database to store user and project data
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
     * This method takes the images from the given filepath and creates a new project in the database.
     * Also, establishes the relation between the given user and the created project.
     * @param filepath the folder which includes images
     * @param username owner of the project
     * @param projectName the name that project will take
     */
    public void createProject (String filepath, String username, String projectName) {
        try {
            // Creating a new project in the database with given projectName
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Projects (name) VALUES (?)");
            pstmt.setString(1, projectName);
            pstmt.executeUpdate();

            // Getting user ID
            int userID = getUserID(username);
            // Getting project ID
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM Projects ORDER BY id ASC LIMIT 1"); // Getting highest id in projects table since we have just added a new project
            int projectID = rs.getInt("id");

            // Establishing the relation between the project and the user
            PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO User_Project_Join (user_id, project_id) VALUES (?, ?)");
            pstmt2.setInt(1, userID);
            pstmt2.setInt(2, projectID);
         
        } catch (SQLException e) {
            System.out.println("Cannot add to User_Project_Join table");
        }

        // Adding the images of the project
        ArrayList<Image> images;
        int index = 0;     // index of images in the project
        images = readImagesFromFolder(filepath);  // Taking images from the folder
        for (Image image : images) {
            saveImageToDatabase(image, username, projectName, index);
            index++;
        }
    }

    /**
     * This method reads images in a folder and convert them to JavaFX Image
     * @param filepath filepath that incldues the images
     * @return returns an arraylist of JavaFX Images.
     */
    public static ArrayList<Image> readImagesFromFolder (String filepath) {
        // Creating a File object with given filepath
        File file = new File(filepath);

        // Creating an arraylist to return the images in the folder
        ArrayList<Image> images = new ArrayList<Image>();

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
                    images.add(fxImage);

                } catch (IOException e) {
                    System.out.println("Cannot read from the file!");
                }
            }
        }
        return images;
    }

    /**
     * This is a private method which returns the id of given username
     * @return id of the user
     * @throws SQLException
     */
    private int getUserID (String username) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT id FROM Users WHERE username = " + username);
        return rs.getInt("id");
    }

    /**
     * This is a private method which returns the id of given projectName
     * @return id of the project
     * @throws SQLException
     */
    private int getProjectID (String username, String projectName) throws SQLException {
        int userID = getUserID(username);

        PreparedStatement pstmt = conn.prepareStatement("SELECT Projects.id FROM Projects" 
        + "JOIN User_Project_Join"
        + "ON Projects.id = User_Project_Join.project_id"
        + "WHERE User_Project_Join.user_id = ?"
        + "AND Projects.name = ?");
        pstmt.setInt(1, userID);
        pstmt.setString(2, projectName);

        ResultSet rs = pstmt.executeQuery();
        return rs.getInt("id");
    }

    /**
     * This method creates a EditableImage object from the parameters, then serialize it to the database.
     * @param img javaFX image
     * @param username username is also required since projectName is not unique in the database
     * @param projectName name of the project
     * @param index index of the image in the project
     */
    public void saveImageToDatabase (Image img, String username, String projectName, int index) {
        // Serialize EditableImage
        // Waiting for EditableImage class to finalize
    }
}