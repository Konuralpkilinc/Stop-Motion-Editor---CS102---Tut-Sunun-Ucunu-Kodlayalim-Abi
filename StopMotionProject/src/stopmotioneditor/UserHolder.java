package stopmotioneditor;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.FlowLayout;

/**
 * UserHolder
 * @author Bahadır
 */
public class UserHolder extends JScrollPane {

    JPanel panel;
    ArrayList<JPanel> userCards;
    ArrayList<ImageShowcase> avatars;
    ArrayList<JCheckBox> checkBoxes; 
    User user;

    public UserHolder(User user){
        this.user = user;
        checkBoxes = new ArrayList<>();
        avatars = new ArrayList<>();
        userCards = new ArrayList<>();
        initComponents();
    }
    private void initComponents(){


        setSize(300, 400);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel = new JPanel();
        panel.setSize(300, 400);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        createCheckBoxes();
        setViewportView(panel);

    }
    private void createCheckBoxes(){

        for(int i = 0; i < User.users.size(); i++){
            JCheckBox cBox = new JCheckBox(User.users.get(i).getUsername());
            cBox.setForeground( java.awt.Color.GRAY );
            checkBoxes.add(cBox );
            
            JPanel userCard = new JPanel(new FlowLayout(FlowLayout.LEFT));
            javax.swing.plaf.DimensionUIResource size = new javax.swing.plaf.DimensionUIResource (300,30);
            userCard.setMinimumSize(size);
            userCard.setPreferredSize(size);
            userCard.setMaximumSize(size);
            userCard.setBackground( new java.awt.Color ( 65, 0, 10 ));
            userCards.add(userCard);
            
            ImageShowcase avatar = new ImageShowcase( User.users.get(i).getAvatar(), 25, 25 );   
            avatars.add(avatar);
            
            userCard.add(checkBoxes.get(i));
            userCard.add(avatars.get(i));
            panel.add(userCards.get(i));
        }
    }
    
    public void disableCurrentUser( User user ){
        for ( int j = 0; j < checkBoxes.size(); j++){
                if(User.users.get(j).compareTo(user) == 0 ){checkBoxes.get(j).setEnabled(false);}
            }
    }

    public JCheckBox getCheckBox(int i){
        return checkBoxes.get(i);
    }
    
    public JPanel getPanel(){
        return this.panel;
    }
    
}