/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;

/**
 *This class will be used to represent the small images(in the ScrollPane) on the editing section and the big images when film is played 
 * @author yigit
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline; //drawings
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class FinalImage extends ImageView{
    public static final double BIG_IMAGE_WIDTH = 1920;
    public static final double BIG_IMAGE_HEIGHT = 1080;
    public static final double SMALL_IMAGE_WIDTH = 208;
    public static final double SMALL_IMAGE_HEIGHT = 117;
    public boolean preserveRatio = true; //preserve ratio when resized
    
    protected ArrayList<Polyline> lines = new ArrayList<>(); //drawings that have been made
    protected Polyline lastLine; //represents the last line that is added to the lines
    protected EditableImage editableImage;// editable image of this final image
    protected Pane finalImageContainer = new Pane(); //this will contain the FinalImage itself and its polylines
    
    public FinalImage(EditableImage editableImage, Image fxImage){//same fxImage with the editableImage
        super(fxImage);
        this.editableImage = editableImage;
        this.setPreserveRatio(preserveRatio);
        //this.setProperties();
    }
    public FinalImage(EditableImage editableImage, String filePath){//same filepath with the editableImage
        super(new Image(filePath));
        this.editableImage = editableImage;
        this.setPreserveRatio(preserveRatio);
        //this.setProperties();
    }
    //set dimensions of the final image
    
    /**
     *
     * @param ratio ratio of small image to editable image, or big image to editable image(use constants in EditableImage)
     * adds the most recent drawing coming from EditableImage
     * will be invoked when the mouse has been released after having been dragged
     */
    public void addLastLine(double ratio){
        Polyline givenLine = this.editableImage.getLastLine();
        Polyline temp = new Polyline();//empty polyline
        //set the colors
        
        Paint color = givenLine.getStroke();
        temp.setStroke(color);
        
        //copy all of the points with respect to ratio of images
        //shift each point with respect to ratio
        for(int i = 0; i < givenLine.getPoints().size(); i++){
            double coordinate = givenLine.getPoints().get(i);
            temp.getPoints().add(coordinate * ratio);
        }
        //set the width of the polyline wrt to ratio(MAY BE PROBLEMATIC LATER ON)
        temp.setStrokeWidth(givenLine.getStrokeWidth() * ratio);
        
        //update the last line and add it
        this.lastLine = temp;
        this.lines.add(lastLine);
        //Add the lastLine to the finalImageContainer
        this.finalImageContainer.getChildren().add(this.lastLine);
    }
    /**
     * 
     * @param givenImageLine editableImage line representation of the line to be copied
     * @param ratio of the line to be copied. Determined by whether this is a smallImage or bigImage
     * Will be invoked from subclasses when an image is copied from the imageOrderingPane
     */
    public void addLine(Polyline givenLine, double ratio){
        Polyline temp = new Polyline();//empty polyline
        //set the colors
        
        Paint color = givenLine.getStroke();
        temp.setStroke(color);
        
        //copy all of the points with respect to ratio of images
        //shift each point with respect to ratio
        for(int i = 0; i < givenLine.getPoints().size(); i++){
            double coordinate = givenLine.getPoints().get(i);
            temp.getPoints().add(coordinate * ratio);
        }
        //set the width of the polyline wrt to ratio(MAY BE PROBLEMATIC LATER ON)
        temp.setStrokeWidth(givenLine.getStrokeWidth() * ratio);
        
       //add the temp
       
        this.lines.add(temp);
        //Add the temp to the finalImageContainer
        this.finalImageContainer.getChildren().add(temp);

    }
    //Return the container of this FinalImage
    public Pane getContainer(){
        return this.finalImageContainer;
    }
    /**
     * Invoke when user right clicks a drawing that has been made.
     * Invoke on both the smallImage and the bigImage
     * @param index of the line to be removed 
     */
    public void removeLineAtIndex(int index){
        Polyline removedLine = this.lines.remove(index);
        //remove the line from the container
        this.finalImageContainer.getChildren().remove(removedLine);
    }
    /**
     * Invoke from editableImage's setLines method. Invoke on both smallImage and bigImage
     * @param editableImageLines editableImage representation of polylines
     * @param ratio ratio of the lines to be drawn
     */
    public void setLines(ArrayList<Polyline> editableImageLines, double ratio){
        for(int i = 0; i < editableImageLines.size(); i++){
            Polyline currentLine = editableImageLines.get(i);
            this.addLine(currentLine, ratio);
        }
    }
}
