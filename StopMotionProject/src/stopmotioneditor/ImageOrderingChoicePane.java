package stopmotioneditor;

/**
 *
 * @author yigit
 */
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ImageOrderingChoicePane extends StackPane {
    public final double VERTICAL_INSET = 150;
    public final double HORIZONTAL_INSET = 125;
    public final double GRIDPANE_VGAP = 75;
    public final double GRIDPANE_HGAP = 100;
    public final double VBOX_SPACING = 15;
    
    private Project project;
    private String comboBoxValue;
    private GridPane gridPane = new GridPane();
    
    //Textfield input values
    private String startIndexString = "";
    private String endIndexString = "";
    private String pasteIndexString = "";
    //Nodes
    private TextField tfStartIndex = new TextField();
    private TextField tfEndIndex = new TextField();
    private TextField tfPasteIndex = new TextField();
    
    private Button btReverse = new Button("Reverse");
    private Button btAddImage = new Button("Add Image"); //Will be used to add new images
    private Button btDelete = new Button("Delete");
    private Button btCopy = new Button("Copy");
    private Button btCut = new Button("Cut");
    private Button btPaste = new Button("Paste");
    
    private Label startLabel = new Label("Start Index");
    private Label endLabel = new Label("End Index");
    private Label pasteLabel = new Label("Paste At Index");
    private Label errorLabel = new Label(); //This label will indicate errors if any exist
    
    private DirectoryChooser fc;
    private EditScreen editScreen;
    
    public ImageOrderingChoicePane(Project project, String comboBoxValue, EditScreen editScreen){
        this.project = project;
        this.comboBoxValue = comboBoxValue;
        this.setTextFields();
        this.setButtons();
        this.setGridPaneProperties();
        this.setErrorLabel();
        this.setFileChooser();
        this.editScreen = editScreen;
        
        this.setPadding(new Insets(VERTICAL_INSET, HORIZONTAL_INSET, VERTICAL_INSET, HORIZONTAL_INSET));
        this.getChildren().add(this.gridPane);
    }
    private void setTextFields(){
        //Set the textfields' properties and set them into the vbox with corresponding labels
        VBox startContainer =  new VBox();
        startContainer.setSpacing(VBOX_SPACING);
        startContainer.getChildren().addAll(startLabel, tfStartIndex);
        
        VBox endContainer =  new VBox();
        endContainer.setSpacing(VBOX_SPACING);  
        endContainer.getChildren().addAll(endLabel, tfEndIndex);
        
        VBox pasteContainer =  new VBox();
        pasteContainer.setSpacing(VBOX_SPACING);
        pasteContainer.getChildren().addAll(pasteLabel, tfPasteIndex);
        
        //Now add the vboxes to the gridPane
        this.gridPane.add(startContainer, 0, 0);
        this.gridPane.add(endContainer, 1, 0);
        this.gridPane.add(pasteContainer, 0, 4);
        this.gridPane.add(this.errorLabel, 1, 4); //add the error label to the gridpane
        
        //Set textfields' invalidation listeners (change listeners)
        tfStartIndex.textProperty().addListener(ov -> {
            //Set the startIndexString to the textfield's value
            this.startIndexString = tfStartIndex.getText();
            
            //If the startIndex is nonnumeric, then paint the stroke of the start textfield to red
            
            if(!isNumericString(this.startIndexString)){
                this.tfStartIndex.setStyle("-fx-border-color: red;"); //this is basic css styling
            }
            else{
                this.tfStartIndex.setStyle("-fx-border-color: green;"); //input looks valid so far
            }
        });
        tfEndIndex.textProperty().addListener(ov -> {
            //Set the endIndexString to the textfield's value
            this.endIndexString = tfEndIndex.getText();
            
            //If the startIndex is nonnumeric, then paint the stroke of the end textfield to red
            
            if(!isNumericString(this.endIndexString)){
                this.tfEndIndex.setStyle("-fx-border-color: red;"); //this is basic css styling
            }
            else{
                this.tfEndIndex.setStyle("-fx-border-color: green;"); //input looks valid so far
            }
        });
        tfPasteIndex.textProperty().addListener(ov -> {
            //Set the endIndexString to the textfield's value
            this.pasteIndexString = tfPasteIndex.getText();
            
            //If the startIndex is nonnumeric, then paint the stroke of the end textfield to red
            
            if(!isNumericString(this.pasteIndexString)){
                this.tfPasteIndex.setStyle("-fx-border-color: red;"); //this is basic css styling
            }
            else{
                this.tfPasteIndex.setStyle("-fx-border-color: green;"); //input looks valid so far
            }
            
        });
    }
    private void setButtons(){
        EventHandler<ActionEvent> buttonHandler = new ImageOrderingHandler();
        
        btReverse.setOnAction(buttonHandler);
        btAddImage.setOnAction(buttonHandler);
        btDelete.setOnAction(buttonHandler);
        btCopy.setOnAction(buttonHandler);
        btCut.setOnAction(buttonHandler);
        btPaste.setOnAction(buttonHandler);
        
        //Add each button to their desired location
        this.gridPane.add(btReverse, 0, 1);
        this.gridPane.add(btAddImage, 1, 1);
        this.gridPane.add(btDelete, 0, 2);
        this.gridPane.add(btCopy, 1, 2);
        this.gridPane.add(btCut, 0 ,3);
        this.gridPane.add(btPaste, 1 , 3);
    }
    private void setGridPaneProperties(){
        this.gridPane.setAlignment(Pos.CENTER);
        this.gridPane.setHgap(GRIDPANE_HGAP);
        this.gridPane.setVgap(GRIDPANE_VGAP);
    }
    private void setErrorLabel(){
        this.errorLabel.setStyle("-fx- fill: red"); //might be problematic
    }
    //This method will throw an exception if our input values are invalid, we will handle the exception outside the method
    //If the method throws NumberFormat then given value cannot be parsed, otherwise it may throw indexout of bounds
    private void invalidateInput(String givenString) throws NumberFormatException, IndexOutOfBoundsException{
        //This method will throw an exception if our input values are invalid, we will handle the exception outside the method
        int numberOfImages = this.project.getNumberOfImages();
        int givenValue = Integer.parseInt(givenString);
        if(givenValue < 1 || givenValue > numberOfImages){
            throw new IndexOutOfBoundsException();
        }
    }
    /**
     * This method sets the errorLabel's text with respect to its current state
     * @param hasErrorOccured 
     */
    private void setErrorLabelState(boolean hasErrorOccured, String errorMessage){
        if(hasErrorOccured){
            errorLabel.setText(errorMessage);
        }
        else{
            errorLabel.setText(""); //empty string no error
        }
    }
    //Set fc properties
    private void setFileChooser(){
        fc = new DirectoryChooser();
        fc.setTitle("Open Folder");        
    }
    
    @Override
    public String toString(){
        return this.comboBoxValue;
    }
    //This static method checks whether a given string is numeric, will be used for input validation
    public static boolean isNumericString(String s){
        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    //Invoke when a new image is added
    public static ArrayList<File> getAddedFiles(File file){
        // Creating an arraylist to return the images in the folder
        ArrayList<File> images = new ArrayList<File>();

        // Specifying the supported extensions
        String[] extensions = new String[] { "jpg", "jpeg", "png"}; 

        // Filter to identify images based on their extensions
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                for (String ext : extensions) {
                    if (name.endsWith("." + ext)) {
                        return true;
                    }
                }
                return false;
            }
        };

        if (file.isDirectory()) {   // Making sure it is a directory
            for (File f : file.listFiles( filter)) {   // Reading each image from the directory
                images.add(f);
            }
        }
        return images;
    }
    //This class will dictate what will happen when a button is clicked
    class ImageOrderingHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Button eventSource =  (Button)event.getSource();
            
            if(eventSource.equals(btReverse)){
                //btReverse button pressed
                try{
                    int startIndex = Integer.parseInt(startIndexString);
                    int endIndex = Integer.parseInt(endIndexString);
                    
                    startIndex -= 1;
                    endIndex -= 1; //Since start is from 1
                    project.reverse(startIndex, endIndex);
                    
                    //From now on, if no exception occured so update the error label accordingly
                    setErrorLabelState(false,"");
                }
                catch(Exception ex){
                    setErrorLabelState(true, "Error: Invalid index");
                }
            }
            else if(eventSource.equals(btAddImage)){
                //ToDo by Konuralp Kılınç
                Stage editScreenStage = editScreen.getPrimaryStage();
                File folder = fc.showDialog(editScreenStage);
                
                
                ArrayList<EditableImage> addedImages = new ArrayList<>();
                ArrayList<File> addedImageFiles = ImageOrderingChoicePane.getAddedFiles(folder);
                
                for(int i = 0; i < addedImageFiles.size(); i++){
                    File file = addedImageFiles.get(i);
                    Image img = new Image(file.toURI().toString());
                    
                    EditableImage newImage = new EditableImage(img, project, 0); //index will be set later !!!
                    addedImages.add(newImage);
                }
                
                project.addNewImages(addedImages);
            }
            else if(eventSource.equals(btDelete)){
                //Try to delete specified images with respect to given input
                try{
                    int startIndex = Integer.parseInt(startIndexString);
                    int endIndex = Integer.parseInt(endIndexString);
                    
                    startIndex -= 1;
                    endIndex -= 1; //Since start is from 1
                    project.remove(startIndex, endIndex);
                    
                    //From now on, if no exception occured so update the error label accordingly
                    setErrorLabelState(false,"");
                }
                catch(Exception ex){
                    //No matter what exception occurs, we will perform the same action
                    setErrorLabelState(true, "Error: Invalid index");
                }
            }
            else if(eventSource.equals(btCopy)){
                try{
                    int startIndex = Integer.parseInt(startIndexString);
                    int endIndex = Integer.parseInt(endIndexString);
                    
                    startIndex -= 1;
                    endIndex -= 1; //Since start is from 1
                    project.copy(startIndex, endIndex);
                    
                    setErrorLabelState(false,"");
                }
                catch(Exception ex){
                    setErrorLabelState(true,"Error: Invalid index");
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            else if(eventSource.equals(btCut)){
                try{
                    int startIndex = Integer.parseInt(startIndexString);
                    int endIndex = Integer.parseInt(endIndexString);
                    
                    startIndex -= 1;
                    endIndex -= 1; //Since start is from 1
                    project.cut(startIndex, endIndex);
                    
                    setErrorLabelState(false, "");
                }
                catch(Exception ex){
                    setErrorLabelState(true,"Error: Invalid index");
                }
            }
            else if(eventSource.equals(btPaste)){
                //User may paste at any valid index, or may append to the end. In order to append to the end user must enter number of images
                try{
                    int pasteIndex = Integer.parseInt(pasteIndexString);
                    pasteIndex -= 1;
                    
                    project.paste(pasteIndex);
                    setErrorLabelState(false, "");
                }
                catch(Exception ex){
                    String exceptionMessage = ex.getMessage();
                    if(exceptionMessage.equals("Error: Nothing is copied")){
                        //nothing is copied exception
                        setErrorLabelState(true, exceptionMessage);
                    }
                    else{
                        setErrorLabelState(true, "Error: Invalid index");
                    }
                }
            }
        }
    }
}

/*
else if(eventSource.equals(btAddImage)){
                //ToDo by Konuralp Kılınç
                Stage editScreenStage = editScreen.getPrimaryStage();
                File file = fc.showDialog(editScreenStage);
                
                User user;
                user = MainMenuFrame.user;
                String userName = user.getUsername();
              
                ArrayList<EditableImage> addedImages = Database.addNewImagesToProject(file, userName, project);
                project.addNewImages(addedImages);
            }
*/