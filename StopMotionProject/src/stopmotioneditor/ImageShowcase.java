package stopmotioneditor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * ImageShowcase
 * @author Burak Oruk
 * this class simply provides a space for the gicen BufferedImage to be displayed in given size
 */
public class ImageShowcase extends JLabel {

    private DimensionUIResource size;
    private BufferedImage image;
    
    public ImageShowcase ( BufferedImage image, int width, int height ) {
        this.size = new DimensionUIResource(width, height);

        // we don't want the size to change, thus it is all set to the given size
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);

        this.image = this.resize(image, width, height); // resizing the image so it would just fit the given sized borders

        this.setIcon ( ( new ImageIcon(this.image) ) ); // and then adding it 
    }
    
    /**
     * this method resizes the given image to given dimensions with the help of Java libraries Image and Graphics2D
     */
    public BufferedImage resize(BufferedImage image, int newW, int newH) { 
        Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage img = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return img;
    }  
}