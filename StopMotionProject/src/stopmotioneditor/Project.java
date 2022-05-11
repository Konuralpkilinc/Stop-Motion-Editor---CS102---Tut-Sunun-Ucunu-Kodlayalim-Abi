/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;



import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
   private ArrayList<EditableImage> images = new ArrayList<>();
   private ArrayList <EditableImage> clonedArrayList = new ArrayList<>();
   //private Timeline timer; //similar to swing Timer, determines animation fps
   private int numberOfImages; //THIS IS IMPORTANT FOR SMALLIMAGE, represents the no of images must be updated during runtime when necessary
   private double fpsRate = INITIAL_FPS_RATE;
   
   //Properties received from the EditScreen object
   private EditScreen editScreen; //EditScreen object itself, its created automatically after start method is invoked in EditScreen
   //private PlayScreen playScreen; //When the animation is played
   private ComboBox<String> choicePaneSelector; //this is the same ComboBox with the one in the EditScreen
   private Circle drawingCircle; //This is the same circle with the one in the DrawingChoicePane, will be used from EditableImage event handling
   private int selectedImgIndex;  //Will be useful for smallImage event handling
   private String userName;
   
   /**
    * constructs a Project
    * Will be invoked when the project is constructed for the first time(not taken from database)
    * @param images 
    * @param projectName 
    * @param userName
    */
   public Project(ArrayList<EditableImage> editableImages, String projectName){
       //set the numberOfImagesInProject in each editableImage during construction, will be used for smallImage indexLabell
       this.name = projectName;
       this.numberOfImages = (editableImages == null) ? 0 : editableImages.size();
       this.images = editableImages;
       //this.initializeTimer();
       
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
       //this.initializeTimer();
       
       //After each EditableImage has been created, initialize the smallImage's labels accordingly
       for(int i = 0; i < this.numberOfImages; i++){
           
       }
   }
   
   public Project(String projectName){
       this.name = projectName;
       this.fpsRate = 3.0;
   }
   
   
   public void incrementNumberOfImages () {
       this.numberOfImages++;
   }
   public void addImage (EditableImage ei) {
       this.images.add(ei);
   }
   public String getProjectName(){
       return name;
   }
   
   //will be used by smallImage instances
   public int getNumberOfImages(){
       return this.numberOfImages;
   }
   public String getName() {
       return this.name;
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
   public void setUserName(String userName){
       this.userName = userName;
   }
   public String getUserName(){
       return this.userName;
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
       this.updateNumberOfImages(); // This will be useful !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
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
   
   /*//Set the fps rate to 9
   private void initializeTimer(){
       this.timer = new Timeline(new KeyFrame(Duration.millis(1000 / INITIAL_FPS_RATE), e -> {
           //This is the actionPerformed method of the timer, will be used when the animation is played
           //ToDo
       }));
       KeyFrame frame = new KeyFrame(Duration.millis(3), e-> {
           
       });
       
       this.timer.setCycleCount(1);//play the animation only once
   }
   public void pauseProject(){
       this.timer.pause();
   }
   public void playProject(){
       this.timer.play(); // continue the animation if it's paused
   }*/
   //Set the project's fps rate, invoke from the FpsAudioChoicePane's slider
   public void setFpsRate(double fps){
       
       this.fpsRate = fps;
       
       /*EventHandler<ActionEvent> timerHandler;           //Event handler of the timer
       timerHandler = playScreen.getPlayHandler();//Event handler of the timer
               
       //Create a new timer instance and assign it to our reference. Then set the properties of the new timer
       this.timer = new Timeline(new KeyFrame(Duration.millis(1000 / fps), timerHandler));
       this.timer.setCycleCount(this.images.size()); //Cycle count must be equal to number of images*/ 
   }
   public double getFpsRate(){
       return this.fpsRate;
   }
   //Invoke this from the PlayScreen. This will dictate what happens when the animation is being played
   public void setTimerHandler(EventHandler<ActionEvent> eventHandler){
       
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
    /*//Invoke from the start of playScreen
    public void setPlayScreen(PlayScreen playScreen){
        this.playScreen = playScreen;
    }*/
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


    //Bahadırın kod
    
    /**
     * This method reverses the existing images from startIndex to endIndex inclusive
     * @param startIndex
     * @param endIndex 
     */
    public void reverse(int startIndex, int endIndex){
        int high = endIndex;
        int low = startIndex;
        while(low < high){
            EditableImage temp = this.images.remove(low);
            //DEVAM ET
            this.images.add(high , temp);
            temp = this.images.remove(high - 1);
            this.images.add(low , temp);        //MIGHT BE PROBLEMATIC
            
            
            high--;
            low++;
        }
        this.updateIndexesOfImages();
        this.editScreen.setSmallImageBox();
    }
    
    //Inclusive start, inclusive end copy
    public void copy(int minIndex , int maxIndex){
        // I assume that you have already convert these integers to ındex values
        
        //Clear the clonedArrayList so previous elements are disregarded
        this.clonedArrayList.clear();
        for(int i = minIndex; i <= maxIndex ;i++ ){
            EditableImage currentImage = this.images.get(i);
            EditableImage clonedImage = currentImage.clone();
            
            //Add the cloned image to the clonedArrayList
            this.clonedArrayList.add(clonedImage);
        }
        this.updateIndexesOfImages();
        this.editScreen.setSmallImageBox();
    }

    public void remove (int minIndex, int maxIndex){

        for (int i = minIndex; i<= maxIndex; i++ ){
            images.remove(i);
        }
        updateIndexesOfImages();
        this.editScreen.setSmallImageBox();
    }
    //Inclusive start, inclusive end
    public void cut(int minIndex, int maxIndex){
        //First copy, then remove
        this.copy(minIndex, maxIndex);
        this.remove(minIndex, maxIndex);
        this.updateIndexesOfImages();
        this.editScreen.setSmallImageBox();
    }
    /**
     * Pastes the copiedArrayList to specified index
     * Throws Exception if copiedArrayList is empty (if user hasn't copied anything)
     * @param startIndex index to be pasted, if there are 6 images, user can specify 0 to 6 inclusive values, specifying 6 will just append it to the end 
     * 
     */
    public void paste(int startIndex) throws Exception{
        //First clone the clonedArrayList to a temp so we can preserve it after paste operation
        ArrayList<EditableImage> temp = new ArrayList<>();
        for(int i = 0; i < this.clonedArrayList.size(); i++){
            EditableImage currentEditableImage = this.clonedArrayList.get(i);
            EditableImage clonedEditableImage = currentEditableImage.clone();
            temp.add(clonedEditableImage);
        }
        
        
        if(this.clonedArrayList.isEmpty()){
            //no image has been copied, throw an exception
            throw new Exception("Exception: Nothing is copied"); //!!!Important, use this when handling the exception
        }
        if(startIndex == this.images.size()){
            //last index of the current images is specified, just append the copied list to the end
            this.images.addAll(this.clonedArrayList);
        }
        else{
            this.images.addAll(startIndex, this.clonedArrayList);
        }
        this.updateIndexesOfImages();
        this.editScreen.setSmallImageBox();
        
        //preserve the copied images
        this.clonedArrayList = temp;
    }
    /**
     * this method adds the new images to the project during runtime
     * invoke from imageorderingchoicepane
     * @param newImages 
     */
    public void addNewImages(ArrayList<EditableImage> newImages){
        this.images.addAll(newImages);
        
        this.updateIndexesOfImages();
        this.editScreen.setSmallImageBox();
    }
}