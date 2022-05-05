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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
public class FpsAudioChoicePane extends Pane{
    private Button btAddSound;
    private Button btRemoveSound;
    private Slider fpsSlider;
    private Project project;
    private String comboBoxValue; //this pane's String representation on combobox
    
    public FpsAudioChoicePane(Project project, String comboBoxValue){
        this.project = project;
        this.comboBoxValue = comboBoxValue;
        
        this.setButtons();
        this.setSlider();
    }
    public void setButtons(){
        this.btAddSound = new Button("Add Sound");
        this.btRemoveSound = new Button("Remove Sound");
        EventHandler<ActionEvent> soundButtonHandler = new SoundButtonHandler();
        this.btAddSound.setOnAction(soundButtonHandler);
        this.btRemoveSound.setOnAction(soundButtonHandler);
    }
    public void setSlider(){
        //bind the
    }
    class SoundButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
            //Either remove sound or add sound button is pressed
            //ToDo
        }
    }
    @Override
    public String toString(){
        return this.comboBoxValue;
    }
}