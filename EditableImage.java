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
    
    private Project project;
    private ArrayList<Polyline> lines = new ArrayList<>();//drawings that have been made
    private Polyline lastLine; //represents the last line that is added to the lines
    private SmallImage smallImage;
    private BigImage bigImage;
    private String filePath;
    private int index;//!!!! IMPORTANT, this must be set when project is being created and must be updated during deletion etc.
    //indexOnProject represents the number of this specific image instance
    
    public EditableImage(String filePath, Project project,int index){
        super(new Image(filePath));
        this.project = project;
        this.filePath = filePath;
        //set the index, from file input's for loop
        this.index = index;
        //create small and bigImages
        this.smallImage = new SmallImage(this, this.filePath);
        this.bigImage = new BigImage(this, this.filePath);//true false specifies whether the final image is bigImage
        //set the properties
        this.setProperties();
        
        
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
    public BigImage getBigImage(){
        return this.bigImage;
    }
    public SmallImage getSmallImage(){
        return this.smallImage;
    }
    //Invoke this method when a deletion appending etc happens
    public void updateIndex(){
        this.project.indexOf(this);
    }
    public int getIndex(){
        return this.index;
    }
    /**
     * Updates the index of this specific editable image. Invoke the updateIndexesOfImages method in project after
     * performing adding deleting etc. operations. That method will invoke this one.
     */
    public void setIndex(int index){
        this.index = index;
    }
    public Project getProject(){
        return this.project;
    }
}