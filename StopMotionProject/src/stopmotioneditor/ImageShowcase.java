package stopmotioneditor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * ImageShowcase
 */
public class ImageShowcase extends JPanel {

    private JLabel imagePlace;
    private DimensionUIResource size;
    private BufferedImage image;
    
    public ImageShowcase ( BufferedImage image, int width, int height ) {
        this.size = new DimensionUIResource(width, height);

        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);

        this.image = this.resize(image, width, height);

        this.imagePlace = new JLabel( new ImageIcon(this.image) );

        this.add(imagePlace);
    }
    
    public BufferedImage resize(BufferedImage image, int newW, int newH) { 
        Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage img = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return img;
    }  
}