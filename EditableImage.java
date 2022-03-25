/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;

/**
 *
 * @author yigit
 * This class represents the image 1280 x 720 which will have drawings on it
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline; //drawings
import java.util.ArrayList;
public class EditableImage extends ImageView{
    public static final double EDITABLE_IMAGE_WIDTH = 1280;
    public static final double EDITABLE_IMAGE_HEIGHT = 720;
    public static final double SMALL_IMAGE_EDITABLE_IMAGE_RATIO = FinalImage.SMALL_IMAGE_HEIGHT / EDITABLE_IMAGE_HEIGHT;
    public static final double BIG_IMAGE_EDITABLE_IMAGE_RATIO = FinalImage.BIG_IMAGE_HEIGHT / EDITABLE_IMAGE_HEIGHT;
    //ratio will be preserved on width aswell
    
    private ArrayList<Polyline> lines = new ArrayList<>();//drawings that have been made
    private Polyline lastLine; //represents the last line that is added to the lines
    private FinalImage smallImage;
    private FinalImage bigImage;
    private String filePath;
            
    public EditableImage(String filePath){
        super(new Image(filePath));
        this.filePath = filePath;
        //create small and bigImages
        this.smallImage = new FinalImage(this,false,this.filePath);
        this.bigImage = new FinalImage(this,true,this.filePath);//true false specifies whether the final image is bigImage
    }
    //set the width and height properties
    public void setProperties(){
        this.setFitWidth(EDITABLE_IMAGE_WIDTH);
        this.setFitHeight(EDITABLE_IMAGE_HEIGHT);
    }
    public void addLine(Polyline line){
        this.lastLine = line;
        this.lines.add(lastLine);
    }
    /**
     * 
     * @return the last drawing that has been added to the editable image
     * This method will be invoked when adding corresponding lines of the editableImage to finalImages
     */
    public Polyline getLastLine(){
        return this.lastLine;
    }
}
