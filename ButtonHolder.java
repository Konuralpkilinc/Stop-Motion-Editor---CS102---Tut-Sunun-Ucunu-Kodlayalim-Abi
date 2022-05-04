import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.DimensionUIResource;
import java.awt.FlowLayout;

/**
 * Aim of this class is to make a panel that will hold various amounts of JButtons and make it possible to use them.
 * This abstract class extends JPanel for the purposes of esay usage. It will be initialized and added to a frame easily.
 * @author Burak Oruk
 * 02.05.2022
 */
class ButtonHolder extends JPanel{

    private ArrayList<JButton> buttons;
    private JPanel panel;
    private JScrollPane scroller;
    private JButton createButton;
    private int panelWidth; 
    private int panelHeight; 
    private int buttonWidth; 
    private int buttonHeight;
    private FlowLayout layout;

    public ButtonHolder( int width, int height, int buttonWidth, int buttonHeight ){

        this.panelWidth = width;
        this.panelHeight = height;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;


        //initializing the objects
        buttons = new ArrayList<JButton>();

        // this button will be resposible for opening the project creating screen
        createButton = new JButton("New Project");
        createButton.setPreferredSize( new DimensionUIResource(buttonWidth, buttonHeight));

        DimensionUIResource preferredSize = new DimensionUIResource(width, height);
        layout = new FlowLayout();

        panel = new JPanel();
        panel.setPreferredSize(preferredSize);
        panel.setLayout( layout );

        scroller = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        // adding the gaps for the scrollbars, in order to make the mainContainer fully visible 
        preferredSize = 
        new DimensionUIResource(width + (int) (scroller.getVerticalScrollBar().getPreferredSize().getWidth()),
        height + (int) (scroller.getHorizontalScrollBar().getPreferredSize().getHeight()) );
        scroller.setPreferredSize(preferredSize); 


        // adding components to their places
        buttons.add(createButton);
        panel.add(createButton);
        scroller.setViewportView(panel);
        this.add(scroller);
    }

    /** 
    * this method recalculates the size of the panel to make all the buttons fit to to screen
    * then proceedes to add the given button to the panel and to the scrollpane
    * @Override
    */
    
    public void add( JButton c ){

        buttons.add(c);

        int rows = this.noOfRows();

        int gap = layout.getHgap(); // since there are gaps betwwen the elements in FlowLayout, they have to be added to to size

        this.panelHeight = ( buttonHeight * rows ) + ( gap * ( rows + 1 ) ); // (rows + 1) for the gap at the top

        panel.setPreferredSize( new DimensionUIResource(this.panelWidth, this.panelHeight) );

        panel.add(c);

    }

    /**
    * does almost the same thing with "public void add(JButton c)" except this method removes the given button instead of adding it
    * @Override
    */
    public void remove( JButton c ){

        buttons.remove(c);
        
        int rows = this.noOfRows();

        int gap = layout.getHgap();

        this.panelHeight = ( buttonHeight * rows ) + ( gap * ( rows + 1 ) );

        panel.setPreferredSize( new DimensionUIResource(panelWidth, panelHeight) );

        panel.remove(c);

    }

    /*
    * this method adds the elements in the given array one by 
    * it exist for the purpose of showasing already existing projects of the user
    */
    public void addArray( JButton[] buttonArray ){

        for ( int i = 0; i < buttonArray.length; i++){
            this.add( buttonArray[i] );
        }
    }

    /*
    * this method removes the elements in the given array one by 
    * it exist for no purpose particular, it just might be useful
    */
    public void removeArray( JButton[] buttonArray ){

        for ( int i = 0; i < buttonArray.length; i++){
            this.remove( buttonArray[i] );
        }
    }

    /*
    * this method calculates how many rows there will be with respect to given measures of the panel and buttons
    */
    private int noOfRows(){

        System.out.println( panelWidth);System.out.println(buttonWidth);

        int columns = this.panelWidth / this.buttonWidth; // this turns out wrong
        System.out.println(columns);

        double x = (double)buttons.size();

        return (int) Math.ceil( ( x ) / columns );
    }

}
