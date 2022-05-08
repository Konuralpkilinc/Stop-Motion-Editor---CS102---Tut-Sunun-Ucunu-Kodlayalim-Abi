package stopmotioneditor;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File; 
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;


/**
 *
 * @author bahadır
 */
public class ImageFiltering {

    static ImageInputStream iis;
    static Iterator<ImageReader> iterator;
    static ImageReader reader; 
    static String imageFormat; 
    static BufferedImage image;
    static int width; 
    static int height; 

    public ImageFiltering(){}  //empty constructor 
    
    public static void filtering(EditableImage input){
        try{
           iis = ImageIO.createImageInputStream(input.getImage());   //creates location for image
           iterator = ImageIO.getImageReaders(iis);       //as I understand this decodes image
           reader = iterator.next();                      //idk
           imageFormat = reader.getFormatName();          //when writing image, type is important
           
           image = ImageIO.read(iis);                       
           width = (int)EditableImage.EDITABLE_IMAGE_WIDTH;
           height = (int)EditableImage.EDITABLE_IMAGE_HEIGHT;
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    public static void redFiltering(EditableImage input){
        
        filtering(input);

        for (int y = 0 ; y < height ; y++){                //these nested loops analyzes all pixels and 
            for (int x = 0; x < width ; x++){              //modifies is according to the method
                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                Color redColor = new Color(red,0,0);
                image.setRGB(x,y,redColor.getRGB());
            }
        }
        WritableImage fxImage = SwingFXUtils.toFXImage(image, null);
        input.setImage(fxImage);    //compiler just want this and I add it all these
    }                                                       //try catch statements
    public static void greenFiltering(EditableImage input){

        filtering(input);

        for (int y = 0 ; y < height ; y++){
            for (int x = 0; x < width ; x++){
                Color color = new Color(image.getRGB(x, y));
                int green = color.getGreen();
                Color greenColor = new Color(0,green,0);
                image.setRGB(x,y,greenColor.getRGB());
            }
        }
        WritableImage fxImage = SwingFXUtils.toFXImage(image, null);
        input.setImage(fxImage);
    }

    public static void blueFiltering(EditableImage input){

        filtering(input);

        for (int y = 0 ; y < height ; y++){
            for (int x = 0; x < width ; x++){
                Color color = new Color(image.getRGB(x, y));
                int blue = color.getBlue();
                Color blueColor = new Color(0,0,blue);
                image.setRGB(x,y,blueColor.getRGB());
            }
        }
        WritableImage fxImage = SwingFXUtils.toFXImage(image, null);
        input.setImage(fxImage);
    }

    public static void grayFiltering(EditableImage input){

        filtering(input);

        for(int y = 0; y < height; y++){                  //Just here I have to use all three rgb value
            for (int x = 0; x < width ; x++){
                Color color = new Color(image.getRGB(x, y));
                int red = (int)(color.getRed()*0.2126);
                int green = (int)(color.getGreen()*0.7152);
                int blue = (int)(color.getBlue()*0.0722);
                int sum = red + green + blue;
                Color gray = new Color(sum,sum,sum);
                image.setRGB(x,y,gray.getRGB());
            }
        }
        WritableImage fxImage = SwingFXUtils.toFXImage(image, null);
        input.setImage(fxImage);
    }  
}