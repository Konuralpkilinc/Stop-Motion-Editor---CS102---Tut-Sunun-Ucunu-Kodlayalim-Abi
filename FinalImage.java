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
public class FinalImage extends ImageView{
    public static final double BIG_IMAGE_WIDTH = 1920;
    public static final double BIG_IMAGE_HEIGHT = 1080;
    public static final double SMALL_IMAGE_WIDTH = 208;
    public static final double SMALL_IMAGE_HEIGHT = 117;
    public boolean preserveRatio = true; //preserve ratio when resized
    
    protected ArrayList<Polyline> lines = new ArrayList<>(); //drawings that have been made
    protected Polyline lastLine; //represents the last line that is added to the lines
    protected EditableImage editableImage;// editable image of this final image
    
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
        temp.setFill(givenLine.getFill());
        temp.setStroke(givenLine.getStroke());
        
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
    }
}
