/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
    
    private Label indexLabel;//This is the label that specifies the index of image, eg index/length
    private StackPane smallImagePane;//this essentially holds the SmallImage and rectangles, rectangles will be used to determine selection and whether sound is being used
    private Rectangle selectionRectangle;
    private Rectangle soundRectangle;//These rectangles are same for every instance of small image
    private boolean hasSound; //Set with respect to EditableImage's sound data field
    //Inhereted fields from FinalImage
    /*protected ArrayList<Polyline> lines = new ArrayList<>(); //drawings that have been made
    protected Polyline lastLine; //represents the last line that is added to the lines
    protected EditableImage editableImage;// editable image of this final image*/ 
    
    /**
     *
     */
    public SmallImage(EditableImage editableImg,String filePath){
        super(editableImg,filePath);
        
        //set the dimensions
        this.setFitWidth(FinalImage.SMALL_IMAGE_WIDTH);
        this.setFitHeight(FinalImage.SMALL_IMAGE_HEIGHT);
        
        //initialize rectangles, regardless of selection
        selectionRectangle = new Rectangle(SELECTION_RECTANGLE_WIDTH,SELECTION_RECTANGLE_HEIGHT,SELECTION_RECTANGLE_COLOR);
        soundRectangle = new Rectangle(SOUND_RECTANGLE_WIDTH,SOUND_RECTANGLE_HEIGHT,SOUND_RECTANGLE_COLOR);
    }
    //Invoke when a drawing has been made
    public void addLastLine(){
        super.addLastLine(EditableImage.SMALL_IMAGE_EDITABLE_IMAGE_RATIO);
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
    public StackPane getSmallImagePane(){
        return this.smallImagePane;
    }
}
