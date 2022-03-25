/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;

import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
/**
 *This class represent a project of the user
 * Please extend accordingly to other classes
 * @author yigit
 */
public class Project {
   public static final double INITIAL_FPS_RATE = 9;
   //add User data field ?
   private ArrayList<EditableImage> images = new ArrayList<>();
   private Timeline timer; //similar to swing Timer, determines animation fps
   
   /**
    * constructs a Project
    * Will be invoked when the project is constructed for the first time(not taken from database)
    * @param imageFilePaths images' filepaths
    */
   public Project(ArrayList<String> imageFilePaths){
       for(int i = 0; i < imageFilePaths.size(); i++){
           String filePath = imageFilePaths.get(i);
           images.add(new EditableImage(filePath));
       }
       this.initializeTimer();
   }
   //Set the fps rate to 9
   private void initializeTimer(){
       this.timer = new Timeline(new KeyFrame(Duration.millis(1000 / INITIAL_FPS_RATE), e -> {
           //This is the actionPerformed method of the timer, will be used when the animation is played
           //ToDo
       }));
       this.timer.setCycleCount(1);//play the animation only once
   }
   public void pauseProject(){
       this.timer.pause();
   }
   public void playProject(){
       this.timer.play(); // continue the animation if it's paused
   }
}
