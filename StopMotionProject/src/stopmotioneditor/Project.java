/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;



import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
/**
 *This class represent a project of the user
 * Please extend accordingly to other classes
 * @author yigit
 */
public class Project {
   public static final double INITIAL_FPS_RATE = 9;
   private String name;
    // user data field?
   private ArrayList <EditableImage> clonedArrayList = new ArrayList<>(); 
   private ArrayList<EditableImage> images = new ArrayList<>();
   private Timeline timer; //similar to swing Timer, determines animation fps
   private int numberOfImages; //THIS IS IMPORTANT FOR SMALLIMAGE, represents the no of images must be updated during runtime when necessary
  
   //Properties received from the EditScreen object
   private EditScreen editScreen; //EditScreen object itself, its created automatically after start method is invoked in EditScreen
   private ComboBox<String> choicePaneSelector; //this is the same ComboBox with the one in the EditScreen
   private Circle drawingCircle; //This is the same circle with the one in the DrawingChoicePane, will be used from EditableImage event handling
   private int selectedImgIndex;  //Will be useful for smallImage event handling
   /**
    * constructs a Project
    * Will be invoked when the project is constructed for the first time(not taken from database)
    * @param images 
    * @param projectName 
    * @param userName
    */
   public Project(ArrayList<Image> fxImages, String projectName){
       //set the numberOfImagesInProject in each editableImage during construction, will be used for smallImage indexLabell
       this.name = projectName;
       this.numberOfImages = fxImages.size();
       for(int i = 0; i < numberOfImages; i++){
           this.images.add( new EditableImage(fxImages.get(i), this, i));
       }
       this.initializeTimer();
       
       //After each EditableImage has been created, initialize the smallImage's labels accordingly
       for(int i = 0; i < this.numberOfImages; i++){
           
       }
   }
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
    /**
     * 
     * @param choicePaneSelector the combobox on the edit screen, this method will be useful for event handling 
     */
    public void setchoicePaneSelector(ComboBox<String> choicePaneSelector) {
        this.choicePaneSelector = choicePaneSelector;
    }
    //Invoke this from the EditScreen's constructor
    public void setDrawingCircle(Circle circle){
        this.drawingCircle = circle;
    }
    //Invoke this from EditableImage Event Handling process
    public Circle getDrawingCircle(){
        return this.drawingCircle;
    }
    /**
     * @return combobox selection indicating choice pane on edit screen
     */
    public String getChoicePaneSelection(){
        String selection = this.choicePaneSelector.getValue();
        return selection;
    }
    //Invoke from start method
    public void setEditScreen(EditScreen editScreen){
        this.editScreen = editScreen;
    }
    public void setSelectedImageIndex(int index){
        this.selectedImgIndex = index;
    }
    public int getSelectedImageIndex(){
        return this.selectedImgIndex;
    }
    /**
     * This method calls the corresponding method on the EditScreen object, will be invoked when a smallImage is clicked
     * No bounds checking
     * @param index of the selected smallImage 
     */
    public void invokeUpdateEditableImagePane(int index){
        this.editScreen.updateEditableImagePane(index);
    }
    public void copy(int minIndex , int maxIndex){
        // I assume that you have already convert these integers to ındex values
        for(int i = minIndex; i <= maxIndex ;i++ ){

            clonedArrayList.add(clone(images.get(i)));
        }
    }

    public void remove (int minIndex, int maxIndex){

        for (int i = minIndex; i<= maxIndex; i++ ){
            images.remove(i);
        }
        updateIndexesOfImages();
    }
    public void cut(int minIndex, int maxIndex){
        int calculation = minIndex;

        for(int i = minIndex; i <= maxIndex; i++){
            clonedArrayList.add(clone(images.get(i)));
        }
        for(int i = minIndex; i <= maxIndex; i++){
            images.remove(calculation);
        }
    }
    public void paste(int entryIndex){

        ArrayList <EditableImage> temp = new ArrayList<>();

        for(int i = entryIndex+1 ; i < images.size(); i++){

            temp.add(clone(images.get(i)));
        }
        for(int i = images.size()-1; i > entryIndex ; i--){

            images.remove(i);
        }
        for(int i = 0 ; i < clonedArrayList.size() ; i++){

            images.add(clonedArrayList.get(i));
        }
        for(int i = 0 ; i < temp.size(); i++){

            images.add(temp.get(i));
        }
        updateIndexesOfImages();
    }
    public EditableImage clone(EditableImage x){
            return null; 
    }
}