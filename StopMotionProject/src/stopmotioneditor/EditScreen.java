/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;
/**
 *This class will be used to view the main editing screen when the edit button is hit on the main menu.
 * @author yigit
 */
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class EditScreen extends Application{
    public static final int SMALL_IMAGE_BOX_SPACING = 5;
    public static final int SMALL_IMAGE_BOX_MIN_HEIGHT = 160;
    public static final int BORDERPANE_INSETS_VALUE = 30; //represents the insets value from top and bottom
    public static final int SCROLLPANE_MIN_HEIGHT = 160;
    public static final boolean CHOICE_PANE_SELECTOR_EDITABLE = false;
    private Project project;
    private EditableImage selectedImg; //this represents the selected image, will be the first image initially
    private Stage primaryStage;
    //private String userName; //retrieved from args will be used for database
    
    //Layout management
    private Pane editableImagePane = new Pane();
    private HBox smallImageBox = new HBox();
    private ScrollPane scrollPane; //this will hold smallImageBox
    private BorderPane borderPane = new BorderPane(); //will comtain smallImageBox and editableImagePane
    private ComboBox<String> choicePaneSelector = new ComboBox<>();
    private ArrayList<Pane> choicePanes = new ArrayList<>();
    private VBox choicePaneContainer = new VBox(); //holds the comboBox and choice pane
    private HBox bigContainer = new HBox();//will contain borderPane and choicePaneContainer with respect to selection
    private Pane selectedChoicePane; //represents the choice pane selected from the combobox
    
    @Override
    public void start(Stage primaryStage){
        //Receive the corresponding Project instance from the main menu class's getSelectedProject method
        this.project = Database.getProject("test3", "project");
        this.primaryStage = primaryStage;
        
        this.project.setEditScreen(this); //Set the project's EditScreen
        //Initializations
        this.setEditableImagePane();
        this.setSmallImageBox();
        this.setBorderPane();
        this.setChoicePanes();
        this.setChoicePaneContainer();
        this.setBigContainer();
        this.sendDrawingCircle();
        
        //Set the Scene and show the primaryStage
        Scene scene = new Scene(bigContainer);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Project");
        primaryStage.setOnCloseRequest(e -> { 
            this.closingOperation(); //will save the changes to database
        }); 
        primaryStage.show();
    }
    public static void main(String[] args){
        //For test only purposes
        launch(args);//launch the edit screen
    }
    private void setEditableImagePane(){
        /*this.selectedImg = this.project.getImage(0);//initially the first image is selected
        this.project.setSelectedImageIndex(0); //Set the datafield on project regarding selected image index
        //add the editableImageContainer object in the EditableImage, which is a Pane instance to this pane
        Pane container = this.selectedImg.getContainer();
        this.editableImagePane.getChildren().add(container); //Add the container which contains the EditableImage and its Polylines 
        */
        this.updateEditableImagePane(0);
    }
    /**
     * This method redraws the EditableImagePane after a smallImage is pressed, it doesn't perform bounds checking
     * Invoke when a smallImage is clicked
     * @param index of the selected image 
     */
    public void updateEditableImagePane(int index){
        //clear the editableImagePane
        this.editableImagePane.getChildren().clear(); //!!!! Might be problematic later on !!!!
        
        this.selectedImg = this.project.getImage(index);
        this.project.setSelectedImageIndex(index);
        
        Pane container = this.selectedImg.getContainer();
        this.editableImagePane.getChildren().add(container);
    }
    //This methods sets EditableImagePane to the center of the borderPane, scrollPane to the bottom 
    private void setBorderPane(){
        this.borderPane.setCenter(this.editableImagePane);
        this.borderPane.setBottom(scrollPane);
        
        //Set the alignment of both elements to center by using static method of BorderPane
        BorderPane.setAlignment(scrollPane, Pos.CENTER);
        BorderPane.setAlignment(editableImagePane, Pos.CENTER);
        
        //top right bottom left
        Insets insets = new Insets(BORDERPANE_INSETS_VALUE,0,BORDERPANE_INSETS_VALUE,0);
        this.borderPane.setPadding(insets);
    }
    //Invoke this method from constructor and when a deletion appending etc happens
    public void setSmallImageBox(){
        //set the smallImageBox's pos value
        this.smallImageBox.setAlignment(Pos.CENTER);
        
        this.smallImageBox.getChildren().clear(); // This will avoid children duplicate exception
        
        
        
        //get the VBox(SmallImageContainer) of the SmallImages and add them all to the smallImageBox
        for(int i = 0; i < this.project.getNumberOfImages(); i++){
            EditableImage currentImg = this.project.getImage(i);
            SmallImage currentSmallImg = (SmallImage) (currentImg.getSmallImage());
            
            //Update the index labels of each smallImage before setting them 
            currentSmallImg.updateIndexLabel();
            
            smallImageBox.getChildren().add(currentSmallImg.getSmallImagePaneContainer()); //Add smallImg's StackPane
        }
        this.smallImageBox.setSpacing(SMALL_IMAGE_BOX_SPACING);//set spacing of this pane to 5 pixels
        
        //set the min height of the smallImageBox to 140 pixels
        this.smallImageBox.setMinHeight(SMALL_IMAGE_BOX_MIN_HEIGHT);
        
        //Add the smallImageBox into the scrollPane
        this.scrollPane = new ScrollPane(this.smallImageBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);//never shot the vertical scrollbar
        this.scrollPane.setMinHeight(160);
    }
    //this method adds the borderPane and the choicePaneContainer to big HBox
    private void setBigContainer(){
        this.bigContainer.getChildren().addAll(borderPane,choicePaneContainer);
    }
    /**
     * This method initializes the choice pane selector(combo box) and sets the choicePanes etc.
     */
    private void setChoicePanes(){
        //set the comboBox's width
        this.choicePaneSelector.setMinWidth(DrawingChoicePane.THIS_WIDTH);
        this.choicePaneSelector.setEditable(CHOICE_PANE_SELECTOR_EDITABLE);
        //intiailize comboBox with selections
        
        //ToDo (ADD MORE CHOICEPANES LATER ON)
        String drawingString = "Add Drawings";
        this.choicePanes.add(new DrawingChoicePane(drawingString));
        choicePaneSelector.getItems().add(drawingString);//add comboBox item
        
        String fpsString = "Fps & Audio";
        this.choicePanes.add(new FpsAudioChoicePane(this.project, fpsString, this)); //create new choice pane
        choicePaneSelector.getItems().add(fpsString);
       
        String filterString = "Apply Filter";
        this.choicePanes.add(new FilterChoicePane(filterString, project));
        choicePaneSelector.getItems().add(filterString);
        
        String imageOrderingString = "Order Images";
        this.choicePanes.add(new ImageOrderingChoicePane(this.project, imageOrderingString, this));
        this.choicePaneSelector.getItems().add(imageOrderingString);
        
        //set the initial selection to the first element
        this.choicePaneSelector.getSelectionModel().selectFirst(); //Might be problematic !!!!!!!!!!!!!!!!!!!!
        
        this.selectedChoicePane = this.choicePanes.get(0);//drawing pane is the initial choice
        
        //Add the choicePaneSelector to the project, this will enable us to access the combobox selection from EditableImages of this project
        this.project.setchoicePaneSelector(this.choicePaneSelector);
        
        //set the comboBox's event handling procedure
        this.choicePaneSelector.setOnAction( e -> {
            //this method body will be called when a selection occurs in combobox
            String selection = this.choicePaneSelector.getValue();
            setSelectedChoicePane(selection);
        });
    }
    /**
     * 
     * @return  void will update the selected choice pane's index on choicePanes arraylist, given a String, will be used by the choicePaneSelector combobox
     */
    private void setSelectedChoicePane(String selection){
        int size = this.choicePanes.size();
        for(int i = 0; i < size; i++){
            Pane choicePane = this.choicePanes.get(i);
            String value = choicePane.toString(); //DON'T FORGET TO OVERRIDE TOSTRING IN CHOICEPANES
            if(selection.equals(value)){
                this.selectedChoicePane = choicePane; //update the current selection
            }
        }
        //update the choice pane container (might be problematic)
        this.choicePaneContainer.getChildren().remove(1); //this arraylist contains the combobox in index 0, selected choice pane in index 1
        this.choicePaneContainer.getChildren().add(this.selectedChoicePane);
        
    }
    //Adds the comboBox and adds the initial selected choice pane, initial choice pane will be add drawings
    private void setChoicePaneContainer(){
        this.choicePaneContainer.getChildren().add(this.choicePaneSelector);
        this.choicePaneContainer.getChildren().add(this.selectedChoicePane);
    }
    /**
     * Sends the circle object to the corresponding Project, invoke this during initializations in start method.
     */
    private void sendDrawingCircle(){
        //Find the drawing choice pane from the arraylist, then invoke setDrawingCircle on the Project object
        for(int i = 0; i < this.choicePanes.size(); i++){
            Pane choicePane = this.choicePanes.get(i);
            if(choicePane instanceof DrawingChoicePane){
                //Set the DrawijngChoicePane's circle to this Project instance
                Circle circle = ((DrawingChoicePane)choicePane).getCircle();
                this.project.setDrawingCircle(circle);
            }
        }
        
    }
    /**
    *This method receives the project object when an existent project is selected. Then it performs initializations
    * Works similar to a constructor
    */
    public void receiveEventSource(Project project){
        this.project = project;
        this.selectedImg = (project.getImages(0, 1)).get(0);//initially the first element is the selected image
    }
    //Invoke when a smallImage is clicked, this will return the current selected image's index
    public int getIndexOfSelectedImage(){
        return this.project.indexOf(this.selectedImg);
    }
    /**
     * Will be used to determine whether a drawing can be added when an event is fired from EditableImage
     * @return selected choice pane's String representation
     */
    public String getChoicePaneSelection(){
        String selection = this.choicePaneSelector.getValue();
        return selection;
    }
    /**
     * !!!Important!!!
     * Invoke this from fpsAudioChoicePane and FilterChoicePane to obtain the current selected editable image that we are performing an action on
     * @return the current selected EditableImage
     */
    public EditableImage getSelectedImage(){
        return this.selectedImg;
    }
    //Returns the stage of the EditScreen, will be useful for obtaining file input from user during edit screen runtime
    public Stage getPrimaryStage(){
        return this.primaryStage;
    }
    /**
     * This method will be invoked when the editscreen is  being closed
     * This method saves the project to our database
     */
    public void closingOperation(){
        ArrayList<EditableImage> images = project.getAllImages();
        String userName = project.getUserName();
        Database.saveChangesInProject(images, userName, project.getName(), project.getFpsRate());
    }
}