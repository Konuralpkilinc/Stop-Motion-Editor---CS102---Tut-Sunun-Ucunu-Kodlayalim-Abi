package stopmotioneditor;

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

        this.image = image;

        this.imagePlace = new JLabel( new ImageIcon(this.image) );

        this.add(imagePlace);
    }
}