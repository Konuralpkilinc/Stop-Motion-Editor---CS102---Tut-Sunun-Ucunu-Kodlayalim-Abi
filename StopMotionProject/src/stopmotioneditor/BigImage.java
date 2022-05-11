/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
/**
 *
 * @author yigit
 * this class extends the final image and represents the images that will be displayed when the animation is being played
 */
public class BigImage extends FinalImage{
    
    //Inhereted fields from FinalImage
    /*protected ArrayList<Polyline> lines = new ArrayList<>(); //drawings that have been made
    protected Polyline lastLine; //represents the last line that is added to the lines
    protected EditableImage editableImage;// editable image of this final image*/ 
    public BigImage(EditableImage editableImg, Image fxImage){
        super(editableImg, fxImage);
        
        //set the dimensions
        this.setFitWidth(FinalImage.BIG_IMAGE_WIDTH);
        this.setFitHeight(FinalImage.BIG_IMAGE_HEIGHT);
    }
    public BigImage(EditableImage editableImg, String filePath){
        super(editableImg,filePath);
        this.finalImageContainer.getChildren().add(this); //!!!!!!!!!!!!!!!!!!!!11
        
        //set the dimensions
        this.setFitWidth(FinalImage.BIG_IMAGE_WIDTH);
        this.setFitHeight(FinalImage.BIG_IMAGE_HEIGHT);
    }
    
    //Invoke when a drawing has been made
    public void addLastLine(){
        super.addLastLine(EditableImage.BIG_IMAGE_EDITABLE_IMAGE_RATIO);
    }
    //Invoke when an EditableImage is copied, invoke for each polyline to be copied if any
    public void addLine(Polyline editableImagePolyline){
        super.addLine(editableImagePolyline, EditableImage.BIG_IMAGE_EDITABLE_IMAGE_RATIO);
    }
    //Invoke from playscreen
    public Pane getBigImageContainer(){
        return this.finalImageContainer;
    }
}