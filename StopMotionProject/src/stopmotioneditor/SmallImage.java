/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author yigit
 * this class extends FinalImage and represents the small images below the editing screen
 */
public class SmallImage extends FinalImage{
    public final double SOUND_RECTANGLE_HEIGHT = 120;
    public final double SOUND_RECTANGLE_WIDTH = 209;
    public final double SELECTION_RECTANGLE_HEIGHT = 123;
    public final double SELECTION_RECTANGLE_WIDTH = 212;
    public final Color SOUND_RECTANGLE_COLOR = Color.GREEN;
    public final Color SELECTION_RECTANGLE_COLOR = Color.BLUE;
    public final double SMALL_IMAGE_PANE_CONTAINER_SPACING = 5;
    
    private Label indexLabel = new Label();//This is the label that specifies the index of image, eg index/length
    private StackPane smallImagePane = new StackPane();//this essentially holds the SmallImage and rectangles, rectangles will be used to determine selection and whether sound is being used
    private VBox smallImagePaneContainer = new VBox();//contains the finalImageContaine coming from subclass and indexLabel
    private static Rectangle selectionRectangle;
    private static Rectangle soundRectangle;//These rectangles are same for every instance of small image
    private boolean hasSound; //Set with respect to EditableImage's sound data field
    //Inhereted fields from FinalImage
    /*protected ArrayList<Polyline> lines = new ArrayList<>(); //drawings that have been made
    protected Polyline lastLine; //represents the last line that is added to the lines
    protected EditableImage editableImage;// editable image of this final image*/ 
    
    /**
     *
     */
    public SmallImage(EditableImage editableImg, Image fxImage){
        super(editableImg, fxImage);
        
        //set the dimensions
        this.setFitWidth(FinalImage.SMALL_IMAGE_WIDTH);
        this.setFitHeight(FinalImage.SMALL_IMAGE_HEIGHT);
        
        //initialize rectangles, regardless of selection
        selectionRectangle = new Rectangle(SELECTION_RECTANGLE_WIDTH,SELECTION_RECTANGLE_HEIGHT,SELECTION_RECTANGLE_COLOR);
        soundRectangle = new Rectangle(SOUND_RECTANGLE_WIDTH,SOUND_RECTANGLE_HEIGHT,SOUND_RECTANGLE_COLOR);
        
        //initialize the stackpane, no selection or sound at first
        this.initializeSmallImagePane();
        this.setSmallImagePaneContainer();
        this.setEventHandling();
    }
    
    public SmallImage(EditableImage editableImg,String filePath){
        super(editableImg,filePath);
        
        //set the dimensions
        this.setFitWidth(FinalImage.SMALL_IMAGE_WIDTH);
        this.setFitHeight(FinalImage.SMALL_IMAGE_HEIGHT);
        
        //initialize rectangles, regardless of selection
        selectionRectangle = new Rectangle(SELECTION_RECTANGLE_WIDTH,SELECTION_RECTANGLE_HEIGHT,SELECTION_RECTANGLE_COLOR);
        soundRectangle = new Rectangle(SOUND_RECTANGLE_WIDTH,SOUND_RECTANGLE_HEIGHT,SOUND_RECTANGLE_COLOR);
        
        //initialize the stackpane, no selection or sound at first
        this.initializeSmallImagePane();
        this.setSmallImagePaneContainer();
        this.setEventHandling();
    }
    //Invoke when a drawing has been made
    public void addLastLine(){
        super.addLastLine(EditableImage.SMALL_IMAGE_EDITABLE_IMAGE_RATIO);
    }
    //Invoke when an EditableImage is copied, invoke for each polyline to be copied if any
    public void addLine(Polyline editableImagePolyline){
        super.addLine(editableImagePolyline, EditableImage.SMALL_IMAGE_EDITABLE_IMAGE_RATIO);
    }
    
    /*
        This method is invoked when a small image is selected, to fill the outer rectangle
        true for selection
    */
    public void setSelected(boolean isSelected){
        this.smallImagePane.getChildren().clear();//remove everything from the StackPane
        
        if(isSelected){            
            //then add necessary things again
            this.smallImagePane.getChildren().add(this.selectionRectangle);
        }
        if(this.hasSound){
            this.smallImagePane.getChildren().add(this.soundRectangle);
        }
        this.smallImagePane.getChildren().add(this);
        
        //After selection, invoke the updateSmallImagePaneContainer to update stuff
        this.updateSmallImagePaneContainer();
    }
    /**
     * this method is invoked when sound is added or removed from the corresponding editable image
     * @param hasSound true when EditableImage
     */
    public void setSoundState(boolean hasSound){
        //selection comes before the sound
        this.smallImagePane.getChildren().clear();
        this.hasSound = hasSound;
        if(hasSound){
            //add the selection then, the sound
            smallImagePane.getChildren().addAll(this.selectionRectangle,this.soundRectangle,this);
        }
        else{
            smallImagePane.getChildren().addAll(this.selectionRectangle, this);
        }
    }
    public VBox getSmallImagePaneContainer(){
        return this.smallImagePaneContainer;
    }
    //Initializes the smallImagePane and indexLabel
    public void initializeSmallImagePane(){
        int index = this.editableImage.getIndex(); //index of this specific  image
        //initialize index label
        this.updateIndexLabel();
        if(index == 0){
            // first image is selected initially
            this.smallImagePane.getChildren().add(this.selectionRectangle);
        }
        this.smallImagePane.getChildren().add(this);
    }
    //adds the finalImageContainer and indexLabel to paneContainer
    public void setSmallImagePaneContainer(){
        //this.smallImagePaneContainer.getChildren().clear(); //!!!!! MIGHT BE PROBLEMATIC!!!
        //this.smallImagePaneContainer.getChildren().addAll(this.smallImagePane,this.indexLabel);
        this.finalImageContainer.getChildren().add(this.smallImagePane);
        this.smallImagePaneContainer.getChildren().addAll(this.finalImageContainer,this.indexLabel);
    }
    public void updateSmallImagePaneContainer(){
        this.smallImagePaneContainer.getChildren().clear();
        this.smallImagePaneContainer.getChildren().addAll(this.finalImageContainer,this.indexLabel);
    }
    /**
     * This method updates the indexLabel of this small image, with respect to its EditableImage's
     * index data field. Must be invoked when an operation is made on the project's array list of EditableImage(Deletion, adding, cutting etc.)
     */
    public void updateIndexLabel(){
        int index = this.editableImage.getIndex();
        Project project = this.editableImage.getProject();//this smallImage's project
        
        int totalImagesInProject = project.getNumberOfImages();
        String value = "(" + (index + 1)+ "," + totalImagesInProject + ")";
        this.indexLabel.setText(value);
    }
    private void setEventHandling(){
        //When a smallImage is clicked
        this.setOnMouseClicked(e -> {
            //Change the state of previous selectedSmallImage to false
            Project project = this.editableImage.getProject();
            int prevIndex = project.getSelectedImageIndex();
            EditableImage prevImage = project.getImage(prevIndex);
            SmallImage prevSmallImage = prevImage.getSmallImage();
            prevSmallImage.setSelected(false);
            
            //Select the current image
            this.setSelected(true); // this SmallImage is selected since it's clicked
            
            //Open the corresponding editable Image
            int currentIndex = this.editableImage.getIndex();
            project.invokeUpdateEditableImagePane(currentIndex);
        });
        
    }
}