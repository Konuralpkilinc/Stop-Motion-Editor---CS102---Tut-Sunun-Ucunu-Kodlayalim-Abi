/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;

/**
 *
 * @author yigit
 * Java Swing Main Menu, ToDo
 */
import java.io.File;
import java.util.ArrayList;
public class MainMenu {
    
    //Static method for accessing from EditScreen, this method must return the selected project from mouse click
    public static Project getSelectedProject(){
        //ToDo
        return null;
    }
    //This method is for test only purposes, omit later
    public static Project testMethod(){
        ArrayList<String> filePaths = new ArrayList<>();
        filePaths.add(new File(("src/stopmotioneditor/Images/FixedImage.jpg")).toURI().toString());
        filePaths.add(new File(("src/stopmotioneditor/Images/FixedImage.jpg")).toURI().toString());
        filePaths.add(new File(("src/stopmotioneditor/Images/FixedImage.jpg")).toURI().toString());
        filePaths.add(new File(("src/stopmotioneditor/Images/FixedImage.jpg")).toURI().toString());
        filePaths.add(new File(("src/stopmotioneditor/Images/FixedImage.jpg")).toURI().toString());
        filePaths.add(new File(("src/stopmotioneditor/Images/FixedImage.jpg")).toURI().toString());
        Project project = new Project(filePaths);
        project.setUserName("User1");
        return project;
    }
}
