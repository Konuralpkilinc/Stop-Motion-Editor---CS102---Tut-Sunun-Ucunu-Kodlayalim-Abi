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
/**
 *This is a panel for setting the drawing values and adding drawings
 * @author yigit
 */
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
public class DrawingChoicePane extends Pane{
    private static final double RADIUS_SLIDER_MAX_VALUE = 200;
    private static final double INITIAL_CIRCLE_RADIUS_VALUE = 80;
    private static final double RADIUS_SLIDER_MIN_VALUE = 5;
    private static final double OPACITY_SLIDER_MAX_VALUE = 100;
    private static final double OPACITY_SLIDER_MIN_VALUE = 10;
    private static final double SLIDER_INITIAL_VALUE = 50;
    private static final double FIXED_RECTANGLES_WIDTH = 30;
    private static final double SEPERATOR_HEIGHT = 15;
    private static final double FIXED_COLOR_PANE_HGAP = 20;
    private static final double FIXED_COLOR_PANE_VGAP = 20;
    public static final double THIS_WIDTH = 600; // 600 pixel
    private static final double CIRCLE_PANE_MIN_SIZE = 400;
    //private static final double THIS_HEIGHT = ?;
    //private static final double RGB_PANE_SPACING = ?;
    //private static final double SMALL_VBOX_SPACING = ?;
    
    //Controls
    private ArrayList<Rectangle> fixedColors = new ArrayList<>();
    private Circle circle = new Circle(INITIAL_CIRCLE_RADIUS_VALUE);//circle in the center, will be used to demonstrate thickness and color, 250 is max radius
    private Slider radiusSlider = new Slider(RADIUS_SLIDER_MIN_VALUE,RADIUS_SLIDER_MAX_VALUE,SLIDER_INITIAL_VALUE);//slider specifies the radius of the circle
    private Slider redSlider = new Slider();
    private Slider blueSlider = new Slider();
    private Slider greenSlider = new Slider();
    private Slider opacitySlider = new Slider(OPACITY_SLIDER_MIN_VALUE, OPACITY_SLIDER_MAX_VALUE, 100);
    private Label redLabel = new Label("  Red \n(0-255)");
    private Label blueLabel = new Label(" Blue\n(0-255)");
    private Label greenLabel = new Label(" Green\n(0-255)");
    private Label opacityLabel =  new Label(" Opacity\n(10-100)");
    //Panes
    private VBox bigVBox = new VBox();
    private VBox smallVBox = new VBox();//contains the circlePane and the slider
    private GridPane fixedColorPane = new GridPane();
    private GridPane rgbPane = new GridPane();
    private Rectangle seperator = new Rectangle();// separates the slider and circle with the below nodes
    private Pane circlePane = new Pane();//contains the circle pane, smallVbox contains this pane
    //Other
    private String comboBoxValue; //this pane's String representation on combobox
    public DrawingChoicePane(String comboBoxValue){
        this.comboBoxValue = comboBoxValue;
        
        this.setWidth(THIS_WIDTH);
        
        this.setSmallVBox();
        
        fixedColorPane.setVgap(FIXED_COLOR_PANE_VGAP);
        fixedColorPane.setHgap(FIXED_COLOR_PANE_HGAP);
        fixedColorPane.setAlignment(Pos.CENTER);
        
        this.setFixedRectangles(FIXED_RECTANGLES_WIDTH);
        this.setRGBPane();
        this.setSeparator();
        
        bigVBox.setSpacing(30);//changel ater on
        bigVBox.getChildren().addAll(smallVBox,seperator,fixedColorPane,rgbPane);
        this.getChildren().addAll(bigVBox);
        this.addBindings();
    }
    
    private void setSmallVBox(){
        circlePane.getChildren().add(this.circle);
        
        //This helps the circle to stay on middle after radius change
        this.circle.centerXProperty().bind(circlePane.widthProperty().divide(2));
        this.circle.centerYProperty().bind(circlePane.heightProperty().divide(2));
        circlePane.setMinHeight(CIRCLE_PANE_MIN_SIZE);
        
        smallVBox.getChildren().addAll(circlePane,radiusSlider);
        //this enables the slider to stay on its desired spot
        //smallVBox.spacingProperty().bind(new SimpleDoubleProperty(420).subtract(circle.radiusProperty().multiply(2)));
        //desired spacing is 20, circle radius is at most 200, so 220 - radius is the spacing
        
    }
    private void setFixedRectangles(double width){//sets the fixed color squares(rectangles) with specified width
        EventHandler<MouseEvent> handler = new ColorBoxHandler();
        for(int i = 0; i < 25; i++){
            Rectangle rect = new Rectangle(width,width);
            this.fixedColors.add(rect);
            int rowIndex = i / 5;
            int columnIndex = i % 5;
            fixedColorPane.add(rect, rowIndex, columnIndex); // 5 rows 5 columns
            
            //Set the outer color of the rectangle to black, change according to the background when necessary
            rect.setStroke(Color.BLACK);
            
            //assign this rectangle's event handler to ColorBoxHandler
            rect.setOnMouseClicked(handler); //whenever the mouse is clicked on the color boxes, the specified handle method will be performed
        }
        //Set the color of each rectangle
        fixedColors.get(0).setFill(Color.BLACK);
        fixedColors.get(1).setFill(Color.DARKGRAY);
        fixedColors.get(2).setFill(Color.LIGHTGRAY);
        fixedColors.get(3).setFill(Color.DARKRED);
        fixedColors.get(4).setFill(Color.RED);
        fixedColors.get(5).setFill(Color.PINK);
        fixedColors.get(6).setFill(Color.GREEN);
        fixedColors.get(7).setFill(Color.LIMEGREEN);
        fixedColors.get(8).setFill(Color.YELLOW);
        fixedColors.get(9).setFill(Color.BLUE);
        fixedColors.get(10).setFill(Color.LIGHTBLUE);
        fixedColors.get(11).setFill(Color.CYAN);
        fixedColors.get(12).setFill(Color.PURPLE);
        fixedColors.get(13).setFill(Color.BROWN);
        fixedColors.get(14).setFill(Color.BEIGE);
        fixedColors.get(15).setFill(Color.CORNSILK);
        fixedColors.get(16).setFill(Color.DARKMAGENTA);
        fixedColors.get(17).setFill(Color.GOLD);
        fixedColors.get(18).setFill(Color.ORANGE);
        fixedColors.get(19).setFill(Color.BLUEVIOLET);
        fixedColors.get(20).setFill(Color.CADETBLUE);
        fixedColors.get(21).setFill(Color.DARKTURQUOISE);
        fixedColors.get(22).setFill(Color.YELLOWGREEN);
        fixedColors.get(23).setFill(Color.OLIVE);
        fixedColors.get(24).setFill(Color.TOMATO);
        
        //properties of gridPane
        fixedColorPane.setHgap(20);//change this later on
        fixedColorPane.setVgap(20);//change later on
    }
    private void setSeparator(){
        this.seperator.widthProperty().bind(this.widthProperty());//bind this seperators width to choice pane's width 
        this.seperator.setHeight(SEPERATOR_HEIGHT);
        this.seperator.setFill(Color.LIGHTGREY);
    }
    private void setRGBPane(){
        redSlider.setMax(255);
        redSlider.setMin(0);
        blueSlider.setMax(255);
        blueSlider.setMin(0);
        greenSlider.setMax(255);
        greenSlider.setMin(0);
        
        
        rgbPane.add(redSlider, 0, 0);
        rgbPane.add(redLabel, 1, 0);
        rgbPane.add(blueSlider, 0, 1);
        rgbPane.add(blueLabel, 1, 1);
        rgbPane.add(greenSlider, 0, 2);
        rgbPane.add(greenLabel, 1, 2);
        rgbPane.add(opacitySlider, 0, 3);
        rgbPane.add(opacityLabel,1,3);
        
        rgbPane.setAlignment(Pos.CENTER);//set the elements onto the center
    }
    /**
     * 
     * @return Circle object of the DrawingChoicePane, will be used to assess the properties of the drawing to be made.
     * Will be called from the EditableImage event handling
     */
    public Circle getCircle(){
        return this.circle;
    }
    /**
     * This method adds some bindings and event handling to the sliders etc., might be problematic later on.
     */
    private void addBindings(){
        radiusSlider.valueProperty().addListener( ov -> {
            double value = radiusSlider.getValue();
            this.circle.setRadius(value);
            
        });
        //set the color sliders
        this.redSlider.valueProperty().addListener(ov -> {
           //set the color
           Color color = (Color)this.circle.getFill();
           double currentGreen = color.getGreen(); //returns value within 0-1
           double currentBlue = color.getBlue();
           double currentOpacity = color.getOpacity();
           double newRed = redSlider.getValue();
           
           currentGreen *= 255;
           currentBlue *= 255;
           
           //set the color of the circle to the new color
           this.circle.setFill(Color.rgb((int)newRed, (int)currentGreen , (int)currentBlue, currentOpacity));
        });
        //set the green slider
        this.greenSlider.valueProperty().addListener(ov -> {
           //set the color
           Color color = (Color)this.circle.getFill();
           double currentRed = color.getRed();
           double currentBlue = color.getBlue();
           double currentOpacity = color.getOpacity();
           double newGreen = greenSlider.getValue();
           
           currentRed *= 255;
           currentBlue *= 255;
           
           //set the color of the circle to the new color
           this.circle.setFill(Color.rgb((int)currentRed,(int)newGreen, (int)currentBlue, currentOpacity ));
        });
        //set the blue slider
        this.blueSlider.valueProperty().addListener(ov -> {
           //set the color
           Color color = (Color)this.circle.getFill();
           double currentGreen = color.getGreen();
           double currentRed = color.getRed();
           double currentOpacity = color.getOpacity();
           double newBlue = blueSlider.getValue();
           
            currentRed *= 255;
            currentGreen *= 255;
            
           //set the color of the circle to the new color
           this.circle.setFill(Color.rgb((int)currentRed,(int)currentGreen, (int)newBlue, currentOpacity));
        });
        //set the opacity slider
        this.opacitySlider.valueProperty().addListener(ov -> {
           //set the color
           Color color = (Color)this.circle.getFill();
           double currentGreen = color.getGreen();
           double currentBlue = color.getBlue();
           double newOpacity = opacitySlider.getValue();
           double currentRed = color.getRed();
           
           currentGreen *= 255;
           currentBlue *= 255;
           currentRed *= 255;
           newOpacity /= 100;
           
           //set the color of the circle to the new color
           this.circle.setFill(Color.rgb((int)currentRed, (int)currentGreen, (int)currentBlue, newOpacity));
          
        });
    }
    //this event handler class will enable us to determine what happens when the user clicks on a color box, must be assigned to the rectangles
    class ColorBoxHandler implements EventHandler<MouseEvent>{ 
        @Override
        public void handle(MouseEvent e){
            Rectangle rect = (Rectangle)e.getSource();
            Color specifiedColor = (Color) rect.getFill(); // MIGHT BE PROBLEMATIC
            
            //set the circle's color to this color
            circle.setFill(specifiedColor);
        }
    }
    @Override
    public String toString(){
        return this.comboBoxValue;
    }
}