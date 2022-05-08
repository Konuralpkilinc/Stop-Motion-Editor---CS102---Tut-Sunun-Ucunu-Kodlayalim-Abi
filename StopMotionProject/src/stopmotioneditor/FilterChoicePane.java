package stopmotioneditor;

/**
 *
 * @author yigit
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane; //Optional for later use
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
public class FilterChoicePane extends Pane{
    public static final double THIS_WIDTH = 600; // 600 pixel
    public static double VERTICAL_INSET = 100;
    public static double HORIZONTAL_INSET = 75;
    public static double FIXED_IMAGE_OPACITY = 0.5;
    public static double FIXED_IMAGE_SCALE = 100; //fitWidth fitHeight of fixedImage
    public static double GRID_PANE_HGAP = 100;
    public static double GRID_PANE_VGAP = 75;
    
    private String comboBoxValue;
    private Paint RED_FILTER_REPRESENTATION = Color.rgb(255,0,0, FIXED_IMAGE_OPACITY);
    private Paint GREEN_FILTER_REPRESENTATION = Color.rgb(0,255,0, FIXED_IMAGE_OPACITY); 
    private Paint BLUE_FILTER_REPRESENTATION = Color.rgb(0,0,255, FIXED_IMAGE_OPACITY); 
    private Paint GRAY_FILTER_REPRESENTATION = Color.rgb(122,122,122, FIXED_IMAGE_OPACITY); 
    private final int OPTION_COUNT = 5; //5 options right now
    
    private ToggleGroup btGroup = new ToggleGroup(); //Object grouping the radio buttons
    private RadioButton btRed = new RadioButton();
    private RadioButton btBlue = new RadioButton();
    private RadioButton btGreen = new RadioButton();
    private RadioButton btGray = new RadioButton();
    private RadioButton btNoFilter = new RadioButton();
    
    private GridPane gridPane = new GridPane(); // will hold the radio buttons and their corresponding images
    public FilterChoicePane(String comboBoxValue){
        this.comboBoxValue = comboBoxValue;
        this.setProperties();
        this.setButtons();
        
        //Images next to radio buttons
        ImageView redFilterImage = new ImageView(new Image("Images/FilterImage.jpg"));
        ImageView blueFilterImage = new ImageView(new Image("Images/FilterImage.jpg"));
        ImageView greenFilterImage = new ImageView(new Image("Images/FilterImage.jpg"));
        ImageView grayFilterImage = new ImageView(new Image("Images/FilterImage.jpg"));
        ImageView noFilterImage = new ImageView(new Image("Images/FilterImage.jpg")); //!!!! This image will be contained in program's jar file, this is not external source
        
        //Set each of these images' fitwidth fitheight properties to 100
        redFilterImage.setFitHeight(FIXED_IMAGE_SCALE);
        redFilterImage.setFitWidth(FIXED_IMAGE_SCALE);
        blueFilterImage.setFitHeight(FIXED_IMAGE_SCALE);
        blueFilterImage.setFitWidth(FIXED_IMAGE_SCALE);
        greenFilterImage.setFitHeight(FIXED_IMAGE_SCALE);
        greenFilterImage.setFitWidth(FIXED_IMAGE_SCALE);
        grayFilterImage.setFitHeight(FIXED_IMAGE_SCALE);
        grayFilterImage.setFitWidth(FIXED_IMAGE_SCALE);
        noFilterImage.setFitHeight(FIXED_IMAGE_SCALE);
        noFilterImage.setFitWidth(FIXED_IMAGE_SCALE);
        
        //ImageView[] fixedImages = {redFilterImage, blueFilterImage, greenFilterImage, grayFilterImage, noFilterImage}; //!!UPDATE this when adding new filter options 
        Pane[] fixedImages = new Pane[OPTION_COUNT];
        fixedImages[0] = this.getFilteredImage(redFilterImage, RED_FILTER_REPRESENTATION);
        fixedImages[1] = this.getFilteredImage(blueFilterImage, BLUE_FILTER_REPRESENTATION);
        fixedImages[2] = this.getFilteredImage(greenFilterImage, GREEN_FILTER_REPRESENTATION);
        fixedImages[3] = this.getFilteredImage(grayFilterImage, GRAY_FILTER_REPRESENTATION);
        
        Pane noFilterImagePane = new Pane();
        noFilterImagePane.getChildren().add(noFilterImage);
        fixedImages[4] = noFilterImagePane;
        
        //Fix the contents into the gridPane
        
        for(int i = 0; i < fixedImages.length; i++){
            //ToggleGroup and fixedImages must have the same number of elements always
            RadioButton radioButton = (RadioButton)btGroup.getToggles().get(i);
            Pane fixedImagePane = fixedImages[i];
            
            gridPane.add(radioButton, 0, i);
            gridPane.add(fixedImagePane, 1, i);
        }
        this.setGridPaneProperties();
        
        //add the gridPane to this object
        this.getChildren().add(this.gridPane);
    }
    private void setProperties(){
        //Set the properties of this pane
        this.setWidth(THIS_WIDTH);
        double top = VERTICAL_INSET, right = HORIZONTAL_INSET, bottom = top, left = right;
        Insets insets = new Insets(top, right, bottom, left);
        this.setPadding(insets); //inner bounds
    }
    private void setButtons(){
        btRed.setText("Red Filter");
        btBlue.setText("Blue Filter");
        btGreen.setText("Green Filter");
        btGray.setText("Gray Filter");
        btNoFilter.setText("No Filter");
        
        //Group the buttons
        btRed.setToggleGroup(btGroup);
        btBlue.setToggleGroup(btGroup);
        btGreen.setToggleGroup(btGroup);
        btGray.setToggleGroup(btGroup);
        btNoFilter.setToggleGroup(btGroup);
        
        //set Event handling for each button
        EventHandler filterHandler = new FilterButtonHandler();
        for(int i = 0; i < btGroup.getToggles().size(); i++){
            RadioButton bt = (RadioButton)btGroup.getToggles().get(i);
            bt.setOnAction(filterHandler);
        }
    }
    private void setGridPaneProperties(){
        this.gridPane.setHgap(GRID_PANE_HGAP);
        this.gridPane.setVgap(GRID_PANE_VGAP);
        this.gridPane.setAlignment(Pos.CENTER);
    }
    /**
     * This is just a method that simulates a filter look on an Image, and returns a stack pane
     * Will be used to display images next to radioButtons
     * Will throw Duplicate exception if img is already contained in a Pane
     */
    public StackPane getFilteredImage(ImageView img, Paint paint){
        StackPane pane = new StackPane();
        
        double height = img.getFitHeight();
        double width = img.getFitWidth();
        Rectangle rect = new Rectangle(width, height, paint);
        
        pane.getChildren().add(img);
        pane.getChildren().add(rect);
        return pane;
    }
    @Override
    public String toString(){
        return this.comboBoxValue;
    }
    class FilterButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(btRed.isSelected()){
                //Apply Red Filter
                //ToDo by Bahadır
            }
            else if(btBlue.isSelected()){
                //Apply blue filter
                //ToDo
            }
            else if(btGreen.isSelected()){
                //Apply green filter
                //ToDo
            }
            else if(btGray.isSelected()){
                //Apply gray filter
                //ToDo
            }
            else if(btNoFilter.isSelected()){
                //Apply no filter
                //ToDo
            }
        }
    }
}