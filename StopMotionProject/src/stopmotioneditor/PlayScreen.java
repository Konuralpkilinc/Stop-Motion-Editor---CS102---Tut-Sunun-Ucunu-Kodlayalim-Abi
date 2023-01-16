package stopmotioneditor;

/**
 *This class will play the animation
 * @author yigit
 */
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PlayScreen extends Application{
    public double PLAY_SCENE_HEIGHT = 1080;
    public double PLAY_SCENE_WIDTH = 1920;
    
    private Project project;
    private ArrayList<EditableImage> editableImages;
    private ArrayList<BigImage> bigImages = new ArrayList<>();
    private int numberOfImages;
    private Pane playPane = new Pane(); //this is the pane holding bigImages one by one
    private int imageCounter = 0; //Increment this when PlayHandler's handle method is invoked 
    private Timeline timer; //similar to swing Timer, determines animation fps
    
    @Override
    public void start(Stage primaryStage){
        //Receive the project first from database etc. !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        this.project = Database.getProject("emir", "project");  // ENTER USERNAME AND PROJECTNAME HERE 
        
        this.initializeProperties();
        this.initializeTimer();
        //this.project.setPlayScreen(this);
        
        //PLay the project 
        this.timer.play();
        
        Scene scene = new Scene(playPane, PLAY_SCENE_WIDTH, PLAY_SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public static void main(String[] args){
        launch(args);
    }
    //Initialize the numberOfImages
    private void initializeProperties(){
        editableImages = this.project.getAllImages();
        this.numberOfImages = editableImages.size();
        for(int i = 0; i < numberOfImages ; i++){
            EditableImage currentEditableImg = editableImages.get(i);
            BigImage currentBigImage = currentEditableImg.getBigImage();
            this.bigImages.add(currentBigImage);
        }
        
    }
    
    private void initializeTimer(){
        double fps = this.project.getFpsRate();
        PlayHandler playHandler = new PlayHandler();
        this.timer = new Timeline(new KeyFrame(Duration.millis(1000 / fps), playHandler));
        this.timer.setCycleCount(numberOfImages);
    }
    //Invoke from setFps of project
    public EventHandler<ActionEvent> getPlayHandler(){
        return new PlayHandler();
    }
    
    class PlayHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            playPane.getChildren().clear();
            BigImage bigImage = bigImages.get(imageCounter);
            EditableImage editableImage = editableImages.get(imageCounter++);
            
            
            Pane bigImageContainer = bigImage.getBigImageContainer(); //bigImage and its polylines
            playPane.getChildren().add(bigImageContainer);
            //playPane.getChildren().add(bigImage);
            //Play the audio
            editableImage.playAudio();
        }
        
    }
   
}