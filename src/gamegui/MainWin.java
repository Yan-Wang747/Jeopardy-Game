/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamegui;

/**
 *
 * @author iqapp
 */
import gamecontroller.QuestionManager;
import gamecontroller.JeopardyGame;
import gamecontroller.Player;
import gamecontroller.PlayerManager;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class MainWin extends javax.swing.JFrame implements  Observer {

    /**
     * Creates new form GameGUI
     */
    private final JeopardyGame gameCore;
    private final QuestionManager theQuestionManager;
    private final PlayerManager thePlayerManager;
         
    public MainWin(JeopardyGame gameCore) {
        initComponents();
        this.gameCore = gameCore;
        this.theQuestionManager = gameCore.getQuestionManager();
        this.thePlayerManager = gameCore.getPlayerManager();
        this.thePlayerManager.addObserver(this);
        this.markPanel.setPreferredSize(new Dimension(1871, 124));
        initButtonArray();
        JeopardyColors.setComponentColor(this.rootPane);
        putPlayers();
    }
    
    @Override
    public void update(Observable o, Object arg){
        this.markPanel.removeAll();
        this.markPanel.repaint();
        putPlayers();
    }
    
    private void putPlayers(){
        ArrayList<Player> players = this.thePlayerManager.getCurrentOrderedPlayers();
        int fontSize = (int)(80 - 60.0 / 24 * (this.thePlayerManager.getNumOfCurrentPlayers() - 2));
        for(Player player : players){
            JLabel playerLabel = new JLabel();
            playerLabel.setForeground(JeopardyColors.FONT);
            playerLabel.setText(player.name + ": " + player.mark);
            playerLabel.setFont(new java.awt.Font("Lucida Grande", 0, fontSize)); 
            this.markPanel.add(playerLabel);
        }
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
    
    private void initButtonArray(){
        JPanel allQuestionPanel = new JPanel();
        allQuestionPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        for(int categoryIndex = 0; categoryIndex < this.theQuestionManager.getNumberOfCategories(); categoryIndex++){
            CategoryPanel newPanel = new CategoryPanel(this.theQuestionManager.getCategory(categoryIndex));
            for(int questionIndex = 0; questionIndex < this.theQuestionManager.getNumberOfQuestions(categoryIndex); questionIndex++){
                QuestionButton newQuestionButton = new QuestionButton(categoryIndex, questionIndex);
                newQuestionButton.setText(Integer.toString(this.theQuestionManager.getWeight(categoryIndex, questionIndex)));
                
                newQuestionButton.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        ((JButton)evt.getSource()).setEnabled(false);
                        questionButtonAction(evt);
                    }
                });
                
                newPanel.add(newQuestionButton);
            }
            
           allQuestionPanel.add(newPanel);
        }
        
        jScrollPane2.setViewportView(allQuestionPanel);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pickingPlayerName = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        markPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setSize(new java.awt.Dimension(1920, 1080));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pickingPlayerName.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        pickingPlayerName.setText("Name");

        jScrollPane2.setBorder(null);

        jScrollPane1.setBorder(null);

        markPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));
        jScrollPane1.setViewportView(markPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1871, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pickingPlayerName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(pickingPlayerName)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.pickingPlayerName.setText(thePlayerManager.getPlayerName(thePlayerManager.getAnsweringPlayerIndex()) + ", please pick a question");
        this.setVisible(true);
    }//GEN-LAST:event_formWindowActivated

    private void showDoubleWindow(int categoryIndex, int questionIndex){
        new BetWin(categoryIndex, questionIndex, gameCore, this).setVisible(true);
        this.setVisible(false);
    }
    
    private void questionButtonAction(java.awt.event.ActionEvent evt){
        int categoryIndex = ((QuestionButton)evt.getSource()).categoryIndex;
        int questionIndex = ((QuestionButton)evt.getSource()).questionIndex;
        
        if(this.theQuestionManager.isDoubleJeopardy(categoryIndex, questionIndex))
            showDoubleWindow(categoryIndex, questionIndex);
        else
            showQuestionWindow(categoryIndex, questionIndex);
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel markPanel;
    private javax.swing.JLabel pickingPlayerName;
    // End of variables declaration//GEN-END:variables
}
