/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.controller;
import jeopardygame.visualeffect.JeopardyColors;
import jeopardygame.model.JeopardyGame;
import jeopardygame.model.Player;
import jeopardygame.sharedview.*;
import jeopardygame.constant.JeopardyGameConstants;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MainWin extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form GameGUI
     */
    private final JeopardyGame gameCore;
         
    public MainWin(JeopardyGame gameCore) {
        initComponents();
        this.gameCore = gameCore;
        this.creditPanel.setPreferredSize(JeopardyGameConstants.CREDIT_PANEL_SIZE);
        initQuestionArray();
        JeopardyColors.setComponentColor(this.rootPane);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.questionButtonAction(e);
    }
    
    private void putPlayers(){
        ArrayList<Player> players = this.gameCore.getOrderedPlayers(false);
        int fontSize = (int)(80 - 60.0 / 24 * (this.gameCore.getNumOfCurrentPlayers() - 2));
        this.creditPanel.removeAll();
        players.stream().map((player) -> {
            JLabel playerLabel = new JLabel();
            playerLabel.setForeground(JeopardyColors.FONT);
            playerLabel.setText(player.getName() + ": " + player.getCredits());
            return playerLabel; 
        }).map((playerLabel) -> {
            playerLabel.setFont(new java.awt.Font("Lucida Grande", 0, fontSize));
            return playerLabel;
        }).forEachOrdered((playerLabel) -> {
            this.creditPanel.add(playerLabel);
        });
    }
    
    @Override
    public void dispose(){
        this.gameCore.end();
        new FameHallWin(this.gameCore).setVisible(true);
        super.dispose();
    }
    
    private void showQuestionWindow(int categoryIndex, int questionIndex){
        new QuestionWin(categoryIndex, questionIndex, gameCore, this, false).setVisible(true);
        this.setVisible(false);
    }
    
    private void initQuestionArray(){
        for(int categoryIndex = 0; categoryIndex < this.gameCore.getNumOfCategories(); categoryIndex++){
            CategoryPanel newPanel = new CategoryPanel(this.gameCore.getCategory(categoryIndex).getCategoryText(), categoryIndex,JeopardyGameConstants.GAP, JeopardyGameConstants.CATEGORY_PANEL_SIZE);
            newPanel.disableTextField();
            newPanel.setCategoryTextFieldFont(JeopardyGameConstants.TEXT_FIELD_FONT);
            for(int questionIndex = 0; questionIndex < this.gameCore.getNumOfQuestions(categoryIndex); questionIndex++)
                newPanel.addNewQuestionButton(Integer.toString(gameCore.getQuestion(categoryIndex, questionIndex).getCredits()), this, JeopardyGameConstants.BUTTON_SIZE).setFont(JeopardyGameConstants.DEFAULT_FONT);
            
           allQuestionPanel.add(newPanel);
        }
    }                             

    private void showDoubleWindow(int categoryIndex, int questionIndex){
        new BetWin(categoryIndex, questionIndex, gameCore, this).setVisible(true);
        this.setVisible(false);
    }
    
    private void questionButtonAction(java.awt.event.ActionEvent evt){
        int categoryIndex = ((CategoryPanel)((QuestionButton)evt.getSource()).getParent()).categoryIndex;
        int questionIndex = ((QuestionButton)evt.getSource()).questionIndex;
        ((QuestionButton)evt.getSource()).setVisible(false);
        
        if(this.gameCore.isDoubleJeopardy(categoryIndex, questionIndex))
            showDoubleWindow(categoryIndex, questionIndex);
        else
            showQuestionWindow(categoryIndex, questionIndex);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        allQuestionPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        creditPanel = new javax.swing.JPanel();
        pickingPlayerName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setResizable(false);
        setSize(new java.awt.Dimension(1920, 1080));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jScrollPane1.setBorder(null);

        allQuestionPanel.setPreferredSize(null);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        allQuestionPanel.setLayout(flowLayout1);
        jScrollPane1.setViewportView(allQuestionPanel);

        jScrollPane2.setBorder(null);

        creditPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 5));
        jScrollPane2.setViewportView(creditPanel);

        pickingPlayerName.setFont(new java.awt.Font("SimSun", 0, 48)); // NOI18N
        pickingPlayerName.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pickingPlayerName)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(pickingPlayerName)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        Player pickingPlayer = gameCore.getPlayer(gameCore.getAnsweringPlayerIndex());
        this.pickingPlayerName.setText(pickingPlayer.getName() + ", please pick a question");
        this.putPlayers();
        this.setLocationRelativeTo(null);
        this.creditPanel.setVisible(true);
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allQuestionPanel;
    private javax.swing.JPanel creditPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pickingPlayerName;
    // End of variables declaration//GEN-END:variables
}
