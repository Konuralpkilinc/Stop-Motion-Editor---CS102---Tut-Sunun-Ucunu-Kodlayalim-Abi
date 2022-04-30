/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.guideneme;

import com.mycompany.guideneme.LoginScreenFrame;

/**
 *
 * @author Burak Oruk
 */
public class RegisterScreenFrame extends javax.swing.JFrame {
    String userName;
    String password;
    String rePassword;
    LoginScreenFrame loginScreenFrame;

    /**
     * Creates new form NewJFrame
     */
    public RegisterScreenFrame(LoginScreenFrame loginScreenFrame) {
        this.loginScreenFrame = loginScreenFrame;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        registerBasePanel = new javax.swing.JPanel();
        registerUsernameLabel = new javax.swing.JLabel();
        registerUsernameScrollpane = new javax.swing.JScrollPane();
        registerUsernameTextArea = new javax.swing.JTextArea();
        registerPasswordLabel = new javax.swing.JLabel();
        registerPasswordScrollpane = new javax.swing.JScrollPane();
        registerPasswordTextArea = new javax.swing.JTextArea();
        registerRePasswordScrollpane = new javax.swing.JScrollPane();
        registerRePasswordTextArea = new javax.swing.JTextArea();
        registerRePasswordLabel = new javax.swing.JLabel();
        registerBackButton = new javax.swing.JButton();
        registerDoneButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        registerBasePanel.setBackground(new java.awt.Color(102, 236, 133));

        registerUsernameLabel.setFont(new java.awt.Font("Ink Free", 3, 36)); // NOI18N
        registerUsernameLabel.setForeground(new java.awt.Color(51, 0, 51));
        registerUsernameLabel.setText("SELECT USERNAME");

        registerUsernameScrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        registerUsernameScrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        registerUsernameTextArea.setBackground(new java.awt.Color(0, 255, 153));
        registerUsernameTextArea.setColumns(20);
        registerUsernameTextArea.setFont(new java.awt.Font("Constantia", 0, 36)); // NOI18N
        registerUsernameTextArea.setForeground(new java.awt.Color(0, 102, 102));
        registerUsernameTextArea.setRows(5);
        registerUsernameTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameHandler(evt);
            }
        });
        registerUsernameScrollpane.setViewportView(registerUsernameTextArea);

        registerPasswordLabel.setFont(new java.awt.Font("Ink Free", 3, 36)); // NOI18N
        registerPasswordLabel.setForeground(new java.awt.Color(51, 0, 51));
        registerPasswordLabel.setText("SELECT PASSWORD");

        registerPasswordScrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        registerPasswordScrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        registerPasswordTextArea.setBackground(new java.awt.Color(0, 255, 153));
        registerPasswordTextArea.setColumns(20);
        registerPasswordTextArea.setFont(new java.awt.Font("Constantia", 0, 36)); // NOI18N
        registerPasswordTextArea.setForeground(new java.awt.Color(0, 102, 102));
        registerPasswordTextArea.setRows(5);
        registerPasswordTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                registerPasswordTextAreausernameHandler(evt);
            }
        });
        registerPasswordScrollpane.setViewportView(registerPasswordTextArea);

        registerRePasswordScrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        registerRePasswordScrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        registerRePasswordTextArea.setBackground(new java.awt.Color(0, 255, 153));
        registerRePasswordTextArea.setColumns(20);
        registerRePasswordTextArea.setFont(new java.awt.Font("Constantia", 0, 36)); // NOI18N
        registerRePasswordTextArea.setForeground(new java.awt.Color(0, 102, 102));
        registerRePasswordTextArea.setRows(5);
        registerRePasswordTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                registerRePasswordTextAreausernameHandler(evt);
            }
        });
        registerRePasswordScrollpane.setViewportView(registerRePasswordTextArea);

        registerRePasswordLabel.setFont(new java.awt.Font("Ink Free", 3, 36)); // NOI18N
        registerRePasswordLabel.setForeground(new java.awt.Color(51, 0, 51));
        registerRePasswordLabel.setText("CONFIRM PASSWORD");

        registerBackButton.setBackground(new java.awt.Color(0, 204, 0));
        registerBackButton.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N
        registerBackButton.setForeground(new java.awt.Color(153, 255, 0));
        registerBackButton.setText("BACK");
        registerBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBackButtonActionPerformed(evt);
            }
        });

        registerDoneButton.setBackground(new java.awt.Color(0, 204, 0));
        registerDoneButton.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N
        registerDoneButton.setForeground(new java.awt.Color(102, 255, 0));
        registerDoneButton.setLabel("DONE");
        registerDoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerDoneButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registerBasePanelLayout = new javax.swing.GroupLayout(registerBasePanel);
        registerBasePanel.setLayout(registerBasePanelLayout);
        registerBasePanelLayout.setHorizontalGroup(
            registerBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerBasePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(registerBackButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(registerBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(registerBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerBasePanelLayout.createSequentialGroup()
                            .addComponent(registerUsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(145, 145, 145))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerBasePanelLayout.createSequentialGroup()
                            .addComponent(registerUsernameScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(126, 126, 126)))
                    .addGroup(registerBasePanelLayout.createSequentialGroup()
                        .addGroup(registerBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(registerPasswordScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                            .addComponent(registerRePasswordScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                            .addComponent(registerRePasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(registerBasePanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(registerPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerDoneButton)
                        .addGap(16, 16, 16))))
        );
        registerBasePanelLayout.setVerticalGroup(
            registerBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerBasePanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(registerBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(registerDoneButton)
                    .addGroup(registerBasePanelLayout.createSequentialGroup()
                        .addComponent(registerUsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerUsernameScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(registerPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerPasswordScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(registerRePasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerRePasswordScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(registerBackButton))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registerBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registerBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameHandler(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameHandler
        
        userName = registerUsernameTextArea.getText();
    }//GEN-LAST:event_usernameHandler

    private void registerPasswordTextAreausernameHandler(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registerPasswordTextAreausernameHandler
        
        password = registerPasswordTextArea.getText();
    }//GEN-LAST:event_registerPasswordTextAreausernameHandler

    private void registerRePasswordTextAreausernameHandler(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registerRePasswordTextAreausernameHandler
        
        rePassword = registerRePasswordTextArea.getText();
    }//GEN-LAST:event_registerRePasswordTextAreausernameHandler

    private void registerBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBackButtonActionPerformed
        
        setVisible(false);
        loginScreenFrame.setVisible(true);
    }//GEN-LAST:event_registerBackButtonActionPerformed

    private void registerDoneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerDoneButtonActionPerformed
        if(!password.equals(rePassword)){
            registerPasswordTextArea.setText("Doesnt match with repassword");
            registerRePasswordTextArea.setText("Doesnt match with password");

        }
        else{
            if(Database.isUsernameUnique(userName)){
                Database.registerUser(userName, password);
                setVisible(false);
                loginScreenFrame.setVisible(true);
            }
            else{
                registerUsernameTextArea.setText("Username name must be unique");
            }
        }
    }//GEN-LAST:event_registerDoneButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton registerBackButton;
    private javax.swing.JPanel registerBasePanel;
    private javax.swing.JButton registerDoneButton;
    private javax.swing.JLabel registerPasswordLabel;
    private javax.swing.JScrollPane registerPasswordScrollpane;
    private javax.swing.JTextArea registerPasswordTextArea;
    private javax.swing.JLabel registerRePasswordLabel;
    private javax.swing.JScrollPane registerRePasswordScrollpane;
    private javax.swing.JTextArea registerRePasswordTextArea;
    private javax.swing.JLabel registerUsernameLabel;
    private javax.swing.JScrollPane registerUsernameScrollpane;
    private javax.swing.JTextArea registerUsernameTextArea;
    // End of variables declaration//GEN-END:variables

}

