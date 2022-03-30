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
   private int numberOfImages; //THIS IS IMPORTANT FOR SMALLIMAGE, represents the no of images must be updated during runtime when necessary
   /**
    * constructs a Project
    * Will be invoked when the project is constructed for the first time(not taken from database)
    * @param imageFilePaths images' filepaths
    */
   public Project(ArrayList<String> imageFilePaths){
       //set the numberOfImagesInProject in each editableImage during construction, will be used for smallImage indexLabell
       this.numberOfImages = imageFilePaths.size();
       for(int i = 0; i < numberOfImages; i++){
           String filePath = imageFilePaths.get(i);
           images.add(new EditableImage(filePath,this,i));
       }
       this.initializeTimer();
       
       //After each EditableImage has been created, initialize the smallImage's labels accordingly
       for(int i = 0; i < this.numberOfImages; i++){
           
       }
   }
   //will be used by smallImage instances
   public int getNumberOfImages(){
       return this.numberOfImages;
   }
   //IMPORTANT, invoke each time when an image is deleted added pasted etc., updates the number of images
   public void updateNumberOfImages(){
       this.numberOfImages = this.images.size();
   }
   /**
    * 
    * @param startIndex, start index of the sublist, inclusive
    * @param endIndex end index of the sublist, exclusive
    * works similar to substring method
    * @return new arraylist of EditableImage 
    */
   public ArrayList<EditableImage> getImages(int startIndex, int endIndex){
       //toDo
       return null;
   }
   /**
    * 
    * @param index of image
    * @return image on the specified index if exists
    */
   public EditableImage getImage(int index){
       if(index >= 0 && index < this.images.size()){
           return this.images.get(index);
       }
       return null;   
   }
   //return all of the images
   public ArrayList<EditableImage> getAllImages(){
       return this.images;
   }
   /**
    * This method updates the indexes of each editable image, invoke after performing an operation
    * on a project's editable image arraylist
    */
   public void updateIndexesOfImages(){
       for(int i = 0; i < this.images.size(); i++){
           //set the index of each editable image to i
           EditableImage currentImg = this.images.get(i);
           currentImg.setIndex(i);
       }
   }
   //returns the index of this editable image on the arraylist, returns -1 if not found
   public int indexOf(EditableImage img){
       if(this.images.contains(img)){
           return this.images.indexOf(img);
       }
       return -1;
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