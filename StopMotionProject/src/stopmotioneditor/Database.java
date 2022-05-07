package stopmotioneditor;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class creates a database to store user and project data
 * and provides methods which enable the application to interact with database.
 * @author Emir Tuglu
 */

public final class Database {
    private static final String URL = "jdbc:sqlite:db.db";    // Database connection URL
    private static Connection CONN = setConnection();

    /**
     * The private method which sets database connection
     * @return Connection
     */
    private static Connection setConnection() {
        try {
            // Connect to the database
            return DriverManager.getConnection(URL);
    
        } catch (SQLException e) {
            System.out.println("Connection error");
            return null;
        }  
    }

    /**
     * INVOKE THIS METHOD WHEN USER TRIES TO LOG IN
     * This method takes username and password strings, then check if there is such a user in the database.
     * @return true if username and password matches, false in all other cases
     * @throws SQLException
     */
    public static boolean isUserExist (String aUsername, String aPassword) {
        try {
            PreparedStatement pstmt = CONN.prepareStatement( "SELECT password FROM Users WHERE username = '?'");
            pstmt.setString( 1, aUsername);
            ResultSet rs = pstmt.executeQuery();

            // Return true if there is a user with given username and password
            return rs.getString( "password").equals( aPassword);
        } 
        catch (SQLException e) {
            System.out.println("Cannot check whether user exist or not");
            return false;
        }
    }

    /**
     * INVOKE THIS METHOD TO CHECK WHETHER GIVEN USERNAME IS UNIQUE OR NOT DURING THE REGISTRATION
     * This method takes a username and checks whether there is a user with the same username in the database
     * @param aUsername the username that will be checked
     * @return true if unique, false if not
     * @throws SQLException
     */
    public static boolean isUsernameUnique (String aUsername) {
        try {
            // Getting the number of users with have a username as 'aUsername' in the database 
            PreparedStatement pstmt = CONN.prepareStatement( "SELECT count(*) FROM Users WHERE username = '?'");
            pstmt.setString( 1, aUsername);
            ResultSet rs = pstmt.executeQuery();

            if (rs.getInt(1) == 0) {
                return true;  // if count is zero, return true
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            System.out.println("Cannot check whether username is unique or not");
            return false;
        }
    }

    /**
     * INVOKE THIS METHOD TO REGISTER A USER TO THE DATABASE
     * This methods takes username and password, then create a new user in the database with them.
     * @param username 
     * @param password
     */
    public static void registerUser (String username, String password) {
        if (isUsernameUnique(username)) {
            try {
                PreparedStatement pstmt = CONN.prepareStatement( "INSERT INTO Users (username, password) VALUES (?, ?)");
                pstmt.setString(1, username);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Cannot register the user");
            }
        }
    }

    /**
     * INVOKE THIS METHOD TO CREATE A NEW PROJECT
     * This method takes the images from the given filepath and creates a new project in the database.
     * Also, establishes the relation between the given user and the created project.
     * @param file the folder which includes images
     * @param username owner of the project
     * @param projectName the name that project will take
     */
    public static void registerProject (File file, String username, String projectName) {
        try {
            // Creating a new project in the database with given projectName
            PreparedStatement pstmt = CONN.prepareStatement("INSERT INTO Projects (name) VALUES (?)");
            pstmt.setString(1, projectName);
            pstmt.executeUpdate();

            // Getting user ID
            int userID = getUserID(username);
            // Getting project ID
            Statement st = CONN.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM Projects ORDER BY id ASC LIMIT 1"); // Getting highest id in projects table since we have just added a new project
            int projectID = rs.getInt("id");

            // Establishing the relation between the project and the user
            PreparedStatement pstmt2 = CONN.prepareStatement("INSERT INTO User_Project_Join (user_id, project_id) VALUES (?, ?)");
            pstmt2.setInt(1, userID);
            pstmt2.setInt(2, projectID);
            pstmt2.executeUpdate();

            // Copying the images to our Projects folder and saving them to database.
            addImagesToProject(readImagesFromFolderToFileArrayList(file), projectID);
            
        } 
        catch (SQLException e) {
            System.out.println("Cannot register project");
        }
    }
    
    /**
     * INVOKE THIS METHOD WHEN USER CLICKS SHARE PROJECT BUTTON
     * This methods sets relation between targetUser and the project in the database. So the project is shared with targetUser
     * @param sharerUsername username of the current user who logged şn
     * @param targetUsername username of the user which the project will be shared
     * @param projectName name of the project to be shared
     */
    public static void shareProject (String sharerUsername, String targetUsername, String projectName) {
        try {
            int targetUserID = getUserID(targetUsername);
            int projectID = getProjectID(sharerUsername, projectName);
            PreparedStatement pstmt = CONN.prepareStatement("INSERT INTO User_Project_Join (user_id, project_id) VALUES (?, ?)");
            pstmt.setInt(1, targetUserID);
            pstmt.setInt(2, projectID);
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println("shareProject error");
        }
    }
    
    /**
     * INVOKE THIS METHOD AFTER VALIDATING USERNAME AND PASSWORD (LOGGING IN)
     * This method returns a User object which is created from database. 
     * @param username username
     * @return User object
     */
    public static User getUser (String username) {
        User user = new User(username);
        ArrayList<Project> projects = getAllProjectsOfUser(username);
        
        for (Project project : projects) {
            user.addProject(project);
        }
        return user;
    }
    
    /**
     * INVOKE THIS METHOD WHEN CREATING USERLIST PANEL
     * This method returns an ArrayList of all Users in the database. 
     * Warning: These User objects contains only username and avatar. They don't contain Project, EditableImage etc.
     * @return arraylist of all users in the database
     */
    public static ArrayList<User> getAllUsers () {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement pstmt = CONN.prepareStatement("SELECT username FROM Users");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                users.add ( new User( rs.getString("username")));
            }
        } 
        catch (SQLException ex) {
            System.out.println("getAllUsers error");
        }
        return users;
    }
    
    /**
     * This methods return a random avatar from the Avatars folder
     * @return an Avatar in the form of BufferedImage
     */
    public static BufferedImage getRandomAvatar () {
        double chooser = Math.random() * 4; // we have 4 avatars
        String filepath;
        BufferedImage img = null;
        if (chooser < 1) {
            filepath= "Avatars\\avatar1.jpeg";
        }
        else if (chooser < 2) {
            filepath= "Avatars\\avatar2.jpg";
        }
        else if (chooser < 3) {
            filepath= "Avatars\\avatar3.jpg";
        }
        else {
            filepath= "Avatars\\avatar4.jpg";
        }
        try {
            img = ImageIO.read(new File( filepath));
        } 
        catch (IOException e) {
            System.out.println("getRandomAvatar error");
        }
        return img;
    }
    
    /**
     * This method returns the project whose name is projectName from the database
     * @param username owner of the project
     * @param projectName name of the project
     * @return 
     */
    public static Project getProject (String username, String projectName) {
        ArrayList<EditableImage> images = new ArrayList<EditableImage>();
        try {
            int projectID = getProjectID(username, projectName);
            PreparedStatement pstmt = CONN.prepareStatement("SELECT filepath,image_index FROM Editable_Images WHERE project_id = ?");
            pstmt.setInt(1, projectID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                images.add( new EditableImage(rs.getString("filepath"), rs.getInt("image_index")));
            }
            Project project = new Project(images, projectName);
            
            for (EditableImage image : images) {
                image.setProject(project);
            }
            return project;
        } 
        catch (SQLException ex) {
            System.out.println("getProject error");
        }
        return null;
    }
    
    /**
     * This method returns all of the projects of a user from the database
     * @param username username
     * @return project arraylist
     */
    public static ArrayList<Project> getAllProjectsOfUser (String username) {
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
            int userID = getUserID(username);
            PreparedStatement pstmt = CONN.prepareStatement("SELECT Projects.name FROM Projects" 
            + "JOIN User_Project_Join"
            + "ON Projects.id = User_Project_Join.project_id"
            + "WHERE User_Project_Join.user_id = ?");
            
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                projects.add (getProject(username, rs.getString("name")));
            }
            return projects;
        } 
        catch (SQLException ex) {
            System.out.println("getAllProjectsOfUser error");
        }
        return null;
    }
    
    /**
     * This method reads images in a folder and add them to a ArrayList
     * @param file folder that contains images
     * @return the arraylist that contains images
     */
    private static ArrayList<File> readImagesFromFolderToFileArrayList (File file) {
        // Creating an arraylist to return the images in the folder
        ArrayList<File> images = new ArrayList<File>();

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
                images.add(f);
            }
        }
        return images;
    }
    
    /**
     * This method creates a ProjectX folder in Projects, and copies images in the files ArrayList there.
     * Also, invokes saveImageToDatabase method to save image's properties to database.
     * @param files the arraylist that contains images
     * @param projectID id of the project
     */
    private static void addImagesToProject (ArrayList<File> files, int projectID) {
        String folderName = "Project" + projectID;
        File projectFolder = new File("Projects\\" + folderName);
        projectFolder.mkdir();
        int index = 0;
                
        for (File file : files) {
            String from = file.getPath();
            String to = "Projects\\" + folderName + "\\" + file.getName();
            Path source = Paths.get(from);
            Path target = Paths.get(to);
            
            try {
                Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);
                saveImageToDatabase(target, index, projectID);
                index++;
            }
            catch (FileAlreadyExistsException ex) {
                
            }
            catch (IOException ex) {
                System.out.println(ex);
                System.out.println("Image Copy Fail");
            } 
            
        }
    }
    
    /**
     * This method inserts into Editable_Images table in the database
     * @param path filepath of the image
     * @param index index of the image in the project
     * @param projectID id of the project which the image belongs to
     */
    private static void saveImageToDatabase (Path path, int index, int projectID) {
        try {
            PreparedStatement pstmt = CONN.prepareStatement("INSERT INTO Editable_Images (filepath, image_index, project_id) VALUES (?, ?, ?)");
            pstmt.setString(1, path.toString());
            pstmt.setInt(2, index);
            pstmt.setInt(3, projectID);
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error in saving images to database");
        }
        
    }

    /**
     * This is a private method which returns the id of given username
     * @return id of the user
     * @throws SQLException
     */
    private static int getUserID (String username) throws SQLException {
        PreparedStatement pstmt = CONN.prepareStatement("SELECT id FROM Users WHERE username = ?");
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        return rs.getInt("id");
    }

    /**
     * This is a private method which returns the id of given projectName
     * @return id of the project
     * @throws SQLException
     */
    private static int getProjectID (String username, String projectName) throws SQLException {
        int userID = getUserID(username);

        PreparedStatement pstmt = CONN.prepareStatement("SELECT Projects.id FROM Projects" 
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
     * @param username username is also required to identify the project since projectName is not unique in the database
     * @param projectName name of the project
     * @param index index of the image in the project
     */
    private static void serialize (String username, String projectName, int index) {        
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(5);
        al.add(6);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String st = gson.toJson(al);
        
        try {
            PreparedStatement pstmt = CONN.prepareStatement("INSERT INTO Editable_Images (image, image_index, project_id) VALUES (?, ?, ?)");
            pstmt.setString(1, st);
            pstmt.setInt(2, 1);
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();
            
            System.out.println("Serialization done!");
        }
        catch (SQLException exception) {
            System.out.println("Serialization fail!");
        }
        
    }
    
    private static Object deserialize () {
        try {
            PreparedStatement pstmt = CONN.prepareStatement("SELECT image FROM Editable_Images WHERE id = ?");
            pstmt.setLong(1, 1);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String st = rs.getString(1);
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList<Image> deserializedImage = gson.fromJson(st, new TypeToken<List<Integer>>(){}.getType());
            
            System.out.println("Deserialization successful!");
            return deserializedImage;
            
        } catch (SQLException ex) {
            System.out.println("Desiralization error!");
        }
        return null;
    }
}