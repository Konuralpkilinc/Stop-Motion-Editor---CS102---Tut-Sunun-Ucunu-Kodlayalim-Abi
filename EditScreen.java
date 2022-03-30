/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditorbackup.stopmotioneditor;

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
    
    private Project project;
    private EditableImage selectedImg; //this represents the selected image, will be the first image initially
    
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
        this.project = MainMenu.testMethod();
        
        //Initializations
        this.setEditableImagePane();
        this.setSmallImageBox();
        this.setBorderPane();
        this.setChoicePanes();
        this.setChoicePaneContainer();
        this.setBigContainer();
        
        //Set the Scene and show the primaryStage
        Scene scene = new Scene(bigContainer);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Project");
        primaryStage.show();
    }
    public static void main(String[] args){
        //For test only purposes
        launch(args);//launch the edit screen
    }
    private void setEditableImagePane(){
        this.selectedImg = this.project.getImage(0);//initially the first image is selected
        this.editableImagePane.getChildren().add(this.selectedImg); 
        
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
    private void setSmallImageBox(){
        //set the smallImageBox's pos value
        this.smallImageBox.setAlignment(Pos.CENTER);

        //get the VBox(SmallImageContainer) of the SmallImages and add them all to the smallImageBox
        for(int i = 0; i < this.project.getNumberOfImages(); i++){
            EditableImage currentImg = this.project.getImage(i);
            SmallImage currentSmallImg = (SmallImage) (currentImg.getSmallImage());
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
        //intiailize comboBox with selections
        
        //ToDo (ADD MORE CHOICEPANES LATER ON)
        this.choicePanes.add(new DrawingChoicePane());
        choicePaneSelector.getItems().add("Add Drawings");//add comboBox item
        
        this.selectedChoicePane = this.choicePanes.get(0);//drawing pane is the initial choice
    }
    //Adds the comboBox and adds the initial selected choice pane, initial choice pane will be add drawings
    private void setChoicePaneContainer(){
        this.choicePaneContainer.getChildren().add(this.choicePaneSelector);
        this.choicePaneContainer.getChildren().add(this.selectedChoicePane);
    }
    /**
    *This method receives the project object when an existent project is selected. Then it performs initializations
    * Works similar to a constructor
    */
    public void receiveEventSource(Project project){
        this.project = project;
        this.selectedImg = (project.getImages(0, 1)).get(0);//initially the first element is the selected image
    }
}
