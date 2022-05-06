package stopmotioneditor;

/**
 *
 * @author Emir
 */
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.io.IOException;

import java.io.File;
import java.util.ArrayList;

public class DatabaseTester {
    public static void test() throws IOException {
        //Image image = new Image("Images/Test.jpg");
        Project project = new Project(null, "proje1");
        EditableImage editableImage = new EditableImage("file:Images/apo7.jpg", project, 0);
        Image image = editableImage.getFxImage();

        long key = Database.saveImageToDatabase(image, "test", "project1", 0);
        Object deserialized = Database.deserializeEditableImage(key);
        System.out.println(deserialized.toString());
        
    }
}