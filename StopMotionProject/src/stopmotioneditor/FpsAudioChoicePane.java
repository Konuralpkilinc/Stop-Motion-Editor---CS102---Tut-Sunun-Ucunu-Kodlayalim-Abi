/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stopmotioneditor;

/**
 *
 * @author yigit
 */
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
public class FpsAudioChoicePane extends StackPane{
    public final double VERTICAL_INSET = 225;
    public final double HORIZONTAL_INSET = 125;
    
    public final double MIN_FPS_VALUE = 5;
    public final double MAX_FPS_VALUE = 45;
    public final double SLIDER_MAJOR_TICK_UNIT = 5;
    public final double SLIDER_MIN_TICK_UNIT = 2.5;
    
    private final double FPS_SLIDER_CONTAINER_SPACING = 30;
    private final double AUDIO_BUTTON_CONTAINER_SPACING = 100;
    private final double HBOX_CONTAINER_SPACING = 100;
    
    private Button btAddSound;
    private Button btRemoveSound;
    private Slider fpsSlider = new Slider(MIN_FPS_VALUE, MAX_FPS_VALUE, Project.INITIAL_FPS_RATE);
    private Project project;
    private EditScreen editScreen;
    private String comboBoxValue; //this pane's String representation on combobox
    private Label fpsLabel = new Label("FPS");
    
    private FileChooser fc; //filechooser to choose audio file
    private File file;
    
    //Layout
    private HBox fpsSliderContainer = new HBox(); //will contain the slider and label
    private HBox audioButtonContainer = new HBox();
    private VBox hboxContainer = new VBox(); //will contain the above horizontal boxes
    
    public FpsAudioChoicePane(Project project, String comboBoxValue, EditScreen editScreen){
        this.editScreen = editScreen;
        this.project = project;
        this.comboBoxValue = comboBoxValue;
        this.setPadding(new Insets(VERTICAL_INSET, HORIZONTAL_INSET, VERTICAL_INSET, HORIZONTAL_INSET));
        
        this.setButtons();
        this.setSlider();
        this.setElements();
        this.setFileChooser();
    }
    public void setButtons(){
        this.btAddSound = new Button("Add Sound");
        this.btRemoveSound = new Button("Remove Sound");
        EventHandler<ActionEvent> soundButtonHandler = new SoundButtonHandler();
        this.btAddSound.setOnAction(soundButtonHandler);
        this.btRemoveSound.setOnAction(soundButtonHandler);
    }
    public void setSlider(){
        //set the properties of the slider
        this.fpsSlider.setShowTickMarks(true);
        this.fpsSlider.setShowTickLabels(true);
        this.fpsSlider.setMajorTickUnit(SLIDER_MAJOR_TICK_UNIT);
        
        //set the fps rate of project
        this.fpsSlider.valueProperty().addListener(ov -> {
            double value = fpsSlider.getValue();
            this.project.setFpsRate(value);
        });
    }
    //This method sets the elements to our pane
    public void setElements(){
        this.fpsSliderContainer.setSpacing(FPS_SLIDER_CONTAINER_SPACING);
        this.audioButtonContainer.setSpacing(AUDIO_BUTTON_CONTAINER_SPACING);
        this.hboxContainer.setSpacing(HBOX_CONTAINER_SPACING);
        
        this.fpsSliderContainer.getChildren().addAll(this.fpsLabel, this.fpsSlider);
        this.audioButtonContainer.getChildren().addAll(this.btAddSound, this.btRemoveSound);
        this.hboxContainer.getChildren().addAll(fpsSliderContainer, audioButtonContainer);
        
        //finally add the hboxContainer to this object
        this.getChildren().add(hboxContainer);
    }
    public void setFileChooser(){
        fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Audio Files", "*.wav","*.mp3","*.aac"));
    }
    
    class SoundButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
             //Either remove sound or add sound button is pressed
            Button eventSource = (Button)e.getSource();
            EditableImage selectedEditableImage = editScreen.getSelectedImage();
            SmallImage selectedSmallImage = selectedEditableImage.getSmallImage();
            
            if(eventSource.equals(btAddSound)){
                Stage editScreenStage = editScreen.getPrimaryStage();
                
                if(eventSource.equals(btAddSound)){
                    
                    file = fc.showOpenDialog(editScreenStage);
                    Database.addMediaToEditableImage(selectedEditableImage , file); 
                    selectedEditableImage.setAudio(file.getAbsolutePath());
                    //Update the smallImage's audio state
                    selectedSmallImage.setSoundState(true);
                }

            }
            else{
                //remove sound
                selectedEditableImage.removeAudio();
                selectedSmallImage.setSoundState(false);
            }
        }
    }
    @Override
    public String toString(){
        return this.comboBoxValue;
    }
}