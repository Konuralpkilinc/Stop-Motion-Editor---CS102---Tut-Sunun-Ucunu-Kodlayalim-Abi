/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package stopmotioneditor;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

/**
 *
 * @author Burak Oruk
 * @contributor Bahadır Günenc
 */
public class MainMenuFrame extends javax.swing.JFrame {
    User user;

    /**
     * Creates new form MainMenu
     */
    public MainMenuFrame(User user) {
        this.user = user;
        initComponents();
    }
    
    // this probably should not exist
    public MainMenuFrame(){
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenuBasePanel = new javax.swing.JPanel();
        mainMenuProjectsScrollpane = new ButtonHolder(600, 400, 160, 140);
        mainMenuProjectsScrollpane.setMainMenu(this);
        mainMenuButtonsPanel = new javax.swing.JPanel();
        mainMenuEditProjectButton = new javax.swing.JButton();
        mainMenuShareProjectButton = new javax.swing.JButton();
        mainMenuPlayProjectButton = new javax.swing.JButton();
        mainMenuDeleteProjectButton = new javax.swing.JButton();
        mainMenuUserListScrollpane = new UserHolder(user);
        mainMenuUserListLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stop Motion Film Editor");
        setResizable(false);

        mainMenuBasePanel.setBackground(new java.awt.Color(102, 0, 0));
        mainMenuBasePanel.setPreferredSize(new java.awt.Dimension(1000, 850));

        mainMenuButtonsPanel.setBackground(new java.awt.Color(51, 0, 0));

        mainMenuEditProjectButton.setBackground(new java.awt.Color(153, 0, 0));
        mainMenuEditProjectButton.setFont(new java.awt.Font("Impact", 2, 15)); // NOI18N
        mainMenuEditProjectButton.setText("Edit Project");
        mainMenuEditProjectButton.setMaximumSize(new java.awt.Dimension(120, 120));
        mainMenuEditProjectButton.setMinimumSize(new java.awt.Dimension(120, 120));
        mainMenuEditProjectButton.setOpaque(true);
        mainMenuEditProjectButton.setPreferredSize(new java.awt.Dimension(120, 120));
        mainMenuEditProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuEditProjectButtonActionPerformed(evt);
            }
        });

        mainMenuShareProjectButton.setBackground(new java.awt.Color(153, 0, 0));
        mainMenuShareProjectButton.setFont(new java.awt.Font("Impact", 2, 15)); // NOI18N
        mainMenuShareProjectButton.setText("Share Project");
        mainMenuShareProjectButton.setMaximumSize(new java.awt.Dimension(120, 120));
        mainMenuShareProjectButton.setMinimumSize(new java.awt.Dimension(120, 120));
        mainMenuShareProjectButton.setOpaque(true);
        mainMenuShareProjectButton.setPreferredSize(new java.awt.Dimension(120, 120));
        mainMenuShareProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuShareProjectButtonActionPerformed(evt);
            }
        });

        mainMenuPlayProjectButton.setBackground(new java.awt.Color(153, 0, 0));
        mainMenuPlayProjectButton.setFont(new java.awt.Font("Impact", 2, 15)); // NOI18N
        mainMenuPlayProjectButton.setText("Play Project");
        mainMenuPlayProjectButton.setToolTipText("");
        mainMenuPlayProjectButton.setMaximumSize(new java.awt.Dimension(120, 120));
        mainMenuPlayProjectButton.setMinimumSize(new java.awt.Dimension(120, 120));
        mainMenuPlayProjectButton.setOpaque(true);
        mainMenuPlayProjectButton.setPreferredSize(new java.awt.Dimension(120, 120));
        mainMenuPlayProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuPlayProjectButtonActionPerformed(evt);
            }
        });

        mainMenuDeleteProjectButton.setBackground(new java.awt.Color(153, 0, 0));
        mainMenuDeleteProjectButton.setFont(new java.awt.Font("Impact", 2, 15)); // NOI18N
        mainMenuDeleteProjectButton.setText("Delete Project");
        mainMenuDeleteProjectButton.setMaximumSize(new java.awt.Dimension(150, 150));
        mainMenuDeleteProjectButton.setMinimumSize(new java.awt.Dimension(150, 150));
        mainMenuDeleteProjectButton.setOpaque(true);
        mainMenuDeleteProjectButton.setPreferredSize(new java.awt.Dimension(150, 150));
        mainMenuDeleteProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuDeleteProjectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainMenuButtonsPanelLayout = new javax.swing.GroupLayout(mainMenuButtonsPanel);
        mainMenuButtonsPanel.setLayout(mainMenuButtonsPanelLayout);
        mainMenuButtonsPanelLayout.setHorizontalGroup(
            mainMenuButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuButtonsPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(mainMenuPlayProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(mainMenuEditProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(mainMenuDeleteProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(mainMenuShareProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        mainMenuButtonsPanelLayout.setVerticalGroup(
            mainMenuButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuButtonsPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(mainMenuButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainMenuPlayProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainMenuEditProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainMenuDeleteProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainMenuShareProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        mainMenuUserListLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        mainMenuUserListLabel.setText("     USER LIST");

        javax.swing.GroupLayout mainMenuBasePanelLayout = new javax.swing.GroupLayout(mainMenuBasePanel);
        mainMenuBasePanel.setLayout(mainMenuBasePanelLayout);
        mainMenuBasePanelLayout.setHorizontalGroup(
            mainMenuBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuBasePanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(mainMenuBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainMenuProjectsScrollpane)
                    .addComponent(mainMenuButtonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(mainMenuBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainMenuUserListScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(mainMenuUserListLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );
        mainMenuBasePanelLayout.setVerticalGroup(
            mainMenuBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuBasePanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(mainMenuBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainMenuBasePanelLayout.createSequentialGroup()
                        .addComponent(mainMenuUserListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mainMenuUserListScrollpane))
                    .addGroup(mainMenuBasePanelLayout.createSequentialGroup()
                        .addComponent(mainMenuProjectsScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(mainMenuButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainMenuEditProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuEditProjectButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainMenuEditProjectButtonActionPerformed

    private void mainMenuPlayProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuPlayProjectButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainMenuPlayProjectButtonActionPerformed

    private void mainMenuShareProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuShareProjectButtonActionPerformed
        // TODO add your handling code here:
        ArrayList<Integer> indexes = new ArrayList<>();
        Project sharedProject =  null;

        for(int i = 0; i < User.users.size(); i++){
            if(mainMenuUserListScrollpane.getCheckBox(i).isSelected()){
                indexes.add(i);
            }
        }        
        for(int i = 0; i < mainMenuProjectsScrollpane.getButtons().size(); i++){
            if(mainMenuProjectsScrollpane.getButtons().get(i).isSelected()){
                sharedProject = user.getProjects().get(i);
                break;
            }
        }
        for(int i = 0; i < indexes.size(); i++){
            User.users.get(indexes.get(i)).addProject(sharedProject);
        }
    }//GEN-LAST:event_mainMenuShareProjectButtonActionPerformed

    private void mainMenuDeleteProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuDeleteProjectButtonActionPerformed
        
        int removeIndex = 0;
        ArrayList<JRadioButton> radioBoxes = mainMenuProjectsScrollpane.getButtons();

        for(int i = 0 ; i < radioBoxes.size(); i++){
            if(radioBoxes.get(i).isSelected()){
                removeIndex = i;
                break;
            }
        }

        mainMenuProjectsScrollpane.remove(radioBoxes.get(removeIndex));
        user.removeProject(removeIndex);
    }//GEN-LAST:event_mainMenuDeleteProjectButtonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenuFrame().setVisible(true);
            }
        });
    }

    public ButtonHolder getButtonHolder(){
        return mainMenuProjectsScrollpane;
    }

    public User getUser(){
        return user;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainMenuBasePanel;
    private javax.swing.JPanel mainMenuButtonsPanel;
    private javax.swing.JButton mainMenuDeleteProjectButton;
    private javax.swing.JButton mainMenuEditProjectButton;
    private javax.swing.JButton mainMenuPlayProjectButton;
    private ButtonHolder mainMenuProjectsScrollpane;
    private javax.swing.JButton mainMenuShareProjectButton;
    private javax.swing.JLabel mainMenuUserListLabel;
    private UserHolder mainMenuUserListScrollpane;
    // End of variables declaration//GEN-END:variables
}
