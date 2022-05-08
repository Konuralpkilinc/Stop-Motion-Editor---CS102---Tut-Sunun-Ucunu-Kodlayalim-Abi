package stopmotioneditor;

/**
 *
 * @author yigit
 * This class represents the image 1280 x 720 which will have drawings on it
 */
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline; //drawings
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class EditableImage extends ImageView implements Cloneable{
    public static final double EDITABLE_IMAGE_WIDTH = 1280;
    public static final double EDITABLE_IMAGE_HEIGHT = 720;
    public static final double SMALL_IMAGE_EDITABLE_IMAGE_RATIO = FinalImage.SMALL_IMAGE_HEIGHT / EDITABLE_IMAGE_HEIGHT;
    public static final double BIG_IMAGE_EDITABLE_IMAGE_RATIO = FinalImage.BIG_IMAGE_HEIGHT / EDITABLE_IMAGE_HEIGHT;
    //ratio will be preserved on width aswell
    
    private Project project;
    private ArrayList<Polyline> lines = new ArrayList<>();//drawings that have been made
    private Polyline lastLine = new Polyline(); //represents the last line that is added to the lines
    private String filePath;
    private SmallImage smallImage;
    private String mediaFilePath;
    private MediaPlayer mediaPlayer;
    private BigImage bigImage;
    private Image fxImage;
    private int index;//!!!! IMPORTANT, this must be set when project is being created and must be updated during deletion etc.
    //indexOnProject represents the number of this specific image instance
    private Pane editableImageContainer = new Pane(); // !!!!!This pane will contain the editable Image and its Polylines, change this pane
    //when the SmallImage is clicked, add this pane into the one in the EditScreen
    private MediaPlayer audioClip; //This will represent an audio attached to a specific image. This audio will be played when animation displays a specific BigImage
    
    public EditableImage(Image fxImage, Project project,int index){
        super(fxImage);
        this.project = project;
        this.fxImage = fxImage;
        //set the index, from file input's for loop
        this.index = index;
        //create small and bigImages
        this.smallImage = new SmallImage(this, this.fxImage);
        this.bigImage = new BigImage(this, this.fxImage);//true false specifies whether the final image is bigImage
        //set the properties
        this.setProperties();
        this.setEventHandling();
        this.setContainer();
    }
    
    public EditableImage(String filePath, Project project,int index){
        super(new Image(filePath));
        //set the index, from file input's for loop
        this.index = index;
        this.project = project;
        this.filePath = filePath;
        this.fxImage = new Image(filePath); //This will avoid exception when ordering images !!!
        //create small and bigImages
        this.smallImage = new SmallImage(this, this.filePath);
        this.bigImage = new BigImage(this, this.filePath);//true false specifies whether the final image is bigImage
        //set the properties
        this.setProperties();
        this.setEventHandling();
        this.setContainer(); 
    }
    
    public EditableImage(String filePath,int index){
        super(new Image(filePath));
        //set the index, from file input's for loop
        this.index = index;
        this.filePath = filePath;
        //create small and bigImages
        this.smallImage = new SmallImage(this, this.filePath);
        this.bigImage = new BigImage(this, this.filePath);//true false specifies whether the final image is bigImage
        //set the properties
        this.setProperties();
        this.setEventHandling();
        this.setContainer(); 
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
    public String getMediaFilePath() {
        return this.mediaFilePath;
    }
    public ArrayList<Polyline> getLines() {
        return this.lines;
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
    public String getFilePath(){
        return filePath;
    }
    public void setMediaFilePath (String filepath) {
        this.mediaFilePath = filepath;
    }
    public void setLines (ArrayList<Polyline> polylines) {
        this.lines = polylines;
    }
    public void setProject(Project aProject) {
        this.project = aProject;
    }
    /**
     * 
     * @return the Pane object containing this EditableImage and its Polylines, will be called from the EditScreen 
     */
    public Pane getContainer(){
        return this.editableImageContainer;
    }
    //This method sets the event handling procedure of the EditableImage, call from constructor
    private void setEventHandling(){
        this.setOnMouseClicked(e -> {
            String selection = this.project.getChoicePaneSelection();
            if(selection.equals("Add Drawings")){
                //Choice Pane selection must be DrawingChoicePane
                
                Circle drawingCircle = this.project.getDrawingCircle();
                Paint lineColor = drawingCircle.getFill();
                double strokeWidth = drawingCircle.getRadius();
               
               //Set the properties to the lastLine object
                this.lastLine.setStrokeWidth(strokeWidth); //Null pointer exception
                //this.lastLine.setFill(lineColor);
                this.lastLine.setStroke(lineColor);
                
                //add this line into the editableImageContainer
                this.editableImageContainer.getChildren().add(this.lastLine);
                
                //Set each lastLine's event handling procedure to the following
                this.lastLine.setOnMouseClicked(mouseEvent ->{
                    String selectionWhenRightClicked = this.project.getChoicePaneSelection();
                    
                    //The following condition enables the lines to be removed if and only if they are right clicked and DrawingChoicePane is open
                    if(mouseEvent.getButton() == MouseButton.SECONDARY && selectionWhenRightClicked.equals("Add Drawings")){
                        //Remove the Polyline if its right clicked
                        int index = this.lines.indexOf(mouseEvent.getSource());
                        this.editableImageContainer.getChildren().remove(mouseEvent.getSource());
                        this.lines.remove(mouseEvent.getSource());
                        //remove from the smallImage and bigImage aswell by using the index
                        this.smallImage.removeLineAtIndex(index);
                        this.bigImage.removeLineAtIndex(index);
                    }
                });
            }
        });
        this.setOnMouseDragged( e-> {
           if(this.contains(e.getX(),e.getY()) && checkDrawingBounds(e.getX(), e.getY())){
                
               //Get the properties of the line with respect to the Circle object in the DrawingChoicePane
               /*Circle drawingCircle = this.project.getDrawingCircle();
               Paint lineColor = drawingCircle.getFill();
               double strokeWidth = drawingCircle.getRadius();
               
               //Set the properties to the lastLine object
                this.lastLine.setStrokeWidth(strokeWidth); //Null pointer exception
                this.lastLine.setFill(lineColor);
                this.lastLine.setStroke(lineColor);*/
                
                if(e.getButton() == MouseButton.PRIMARY){
                    //each Polyline object contains a double ArrayList that holds the X values of the points in even indexes, Y in odd indexes. So add 1 by 1.
                    this.lastLine.getPoints().add(e.getX());
                    this.lastLine.getPoints().add(e.getY());
                
                
                }
           }
        });
        this.setOnMouseReleased(e -> {
            //When the drawing is complete (Mouse is released) alter the proportion of each drawing and add them into the SmallImage and BigImage of this EditableImage
            this.smallImage.addLastLine();
            this.bigImage.addLastLine();
            
            //After passing the corresponding line to the FinalImage instances, create a new Polyline and add this to the arraylist
            this.lines.add(lastLine);
            this.lastLine = new Polyline();
        });
        
    }
    //Invoke this from the , will be used to set the image to its container
    private void setContainer(){
        this.editableImageContainer.getChildren().add(this); //Adds this EditableImage to its container
    }
    /**
     * 
     * @param x coordinate
     * @param y where the mouse is dragged y coordinate
     *  the thickness of the drawing being made
     * This method will prevent the drawing to exceed its boundaries when being made
     * !!!!!IMPORTANT!!! If you change the strokeWidth in the event handling statements, YOU MUST CHANGE IT HERE ASWELL
     */
    private boolean checkDrawingBounds(double x, double y){
        Circle drawingCircle = this.project.getDrawingCircle();
        double strokeWidth = drawingCircle.getRadius();
        strokeWidth /= 2; //this is because strokeWidth is approximately half the radius
        
        boolean isValid = true;
        //Check x
        double imgWidth = this.getFitWidth();
        double imgHeight = this.getFitHeight(); //might be problematic
        if(!(x >= strokeWidth && x <= imgWidth - strokeWidth)){
            //invalid
            isValid = false;
        }
        if(!((y >= strokeWidth && y <= imgHeight - strokeWidth))){
            isValid = false;
        }
        return isValid;
    }
    /**
     * This method sets the lines datafield and adds them to the EditableImageContainer
     * Will be invoked when an EditableImage is being cloned
     * @param givenLines givenPolylines (POLYLINES OF THE EDITABLE IMAGE, SMALL IMAGE AND BIG IMAGE WILL BE SET ACCORDINGLY) 
     */
    private void setPolylines(ArrayList<Polyline> givenLines){
        //From the constructor, setEventHandling is called and our EditableImage is already contained in its pane, just add the givenLines
        this.lines = givenLines;
        for(int i = 0; i < this.lines.size(); i++){
            Polyline currentLine = this.lines.get(i);
            this.editableImageContainer.getChildren().add(currentLine);
            
            //set the smallImage and bigImage's polylines with the same polyline but proportionate 
        }
    }
    
    @Override
    /**
     * This method will clone an existing EditableImage.
     * Cloned properties: lastLine(Polyline), lines(ArrayList<Polyline>), smallImage, bigImage
     * Properties passed from the existing object: fxImage(Image), project(Project), index(int)
     * @return EditableImage cloned image
     */
    public EditableImage clone(){
        //We can create multiple ImageView objects from a single Image, so invoke the constructor receiving Image
        //EditableImage clonedEditableImage = new EditableImage(this.fxImage,this.project,this.index); //we will change the index later on
        EditableImage clonedEditableImage = new EditableImage(this.filePath,this.project,this.index);
        ArrayList<Polyline> newLines = new ArrayList<>();
        
        //Clone the Polylines
        for(int i = 0; i < this.lines.size(); i++){
            Polyline currentLine = this.lines.get(i);
            Polyline clone = clonePolyline(currentLine);
            
            //add the cloned polyline's representation to the smallImage and bigImage of the clonedEditableImage aswell
            SmallImage clonedSmallImage = clonedEditableImage.getSmallImage();
            BigImage clonedBigImage = clonedEditableImage.getBigImage();
            clonedSmallImage.addLine(currentLine);
            clonedBigImage.addLine(currentLine); //!!!!!BUNU VE ÜSTÜNÜ clonedLine yap parametreyi
            
            newLines.add(clone); //add the clone
            
            //No need to set the lastLine property for now !!!!!!!!!!!!!!!
        }
        //add the newLines to our new editable image
        clonedEditableImage.setPolylines(newLines);
        return clonedEditableImage;
    }
    //This method creates a new polyline from a givenLine, it clones the points, paint and strokeWidth values.
    public static Polyline clonePolyline(Polyline givenLine){
        Polyline newLine = new Polyline();
        ObservableList<Double> coordinates = givenLine.getPoints();
        for(int i = 0; i < coordinates.size(); i++){
            //copy each coordinate
            double coordinate =  coordinates.get(i);
            newLine.getPoints().add(coordinate);
        }
        
        //Copy the paint value and the width
        Paint lineColor = givenLine.getStroke(); //!!!Change to getFill if problematic
        newLine.setStroke(lineColor);
        double stroke = givenLine.getStrokeWidth();
        newLine.setStrokeWidth(stroke);
        
        return newLine;
    }
    /**
     * Invoke when user sets the sound of this editableImage
     * @param filePath of the sound obtained from user 
     * May throw exception no exception handling here
     * !!!IMPORTANT!!! Filepath must contain the exact file's name at the end of its folder and its file extension.
     * Filepath must also include \\ instead of \ (escape char)
     */
    public void setAudio(String filePath){
        Media media = new Media(new File(filePath).toURI().toString());
        this.audioClip = new MediaPlayer(media);
    }
    /**
     * 
     * @return true if audio is removed successfully
     * false if no audio exists
     */
    public boolean removeAudio(){
        boolean value = false;
        
        if(this.audioClip != null){
            this.audioClip = null;
            value = true;
        }
        
        return value;
    }
    //Invoke this when a BigImage is displayed, this method will play the audio if it exists
    public void playAudio(){
        if(this.audioClip != null){
            this.audioClip.play();
        }
    }
}