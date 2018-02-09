/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamegui;

import gamecontroller.*;
import exception.*;
/**
 *
 * @author student
 */
public class BetWin extends javax.swing.JFrame {


    /**
     * Creates new form BetWin
     */
    private final int categoryIndex;
    private final int questionIndex;
    private final JeopardyGame gameCore;
    private final MainWin theMainWindow;
    private final PlayerManager thePlayerManager;
    private final QuestionManager theQuestionManager;
    private final int totalCredits;
    
    public BetWin(int categoryIndex, int questionIndex, JeopardyGame gameCore, MainWin theMainWindow) {
        initComponents();
        this.categoryIndex = categoryIndex;
        this.questionIndex = questionIndex;
        this.gameCore = gameCore;
        this.theMainWindow = theMainWindow;
        this.thePlayerManager = gameCore.getPlayerManager();
        this.theQuestionManager = gameCore.getQuestionManager();
        int answeringPlayerIndex = this.thePlayerManager.getAnsweringPlayerIndex();
        totalCredits = this.thePlayerManager.getPlayerCredits(answeringPlayerIndex);
        this.creditsTextField.setText(Integer.toString(totalCredits));
        this.creditSlider.setMaximum(totalCredits);
        this.creditSlider.setValue(totalCredits);
        this.creditsTextField.selectAll();
        this.nameLabel.setText(this.thePlayerManager.getPlayerName(answeringPlayerIndex) + ", please specify the amount.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        creditsTextField = new javax.swing.JTextField();
        creditSlider = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        okButton.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        nameLabel.setText("Name");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel2.setText("Amount:");

        creditsTextField.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        creditsTextField.setText("0");

        creditSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                creditSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(creditSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                            .addComponent(creditsTextField))
                        .addContainerGap(33, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(nameLabel)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(creditsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(creditSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(okButton)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void creditSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_creditSliderStateChanged
        // TODO add your handling code here:
        this.creditsTextField.setText(Integer.toString(this.creditSlider.getValue()));
    }//GEN-LAST:event_creditSliderStateChanged

    private int getCredits() throws CreditRangeException{
        int res = Integer.parseInt(this.creditsTextField.getText());
        if(res > totalCredits || res < 0)
            throw new CreditRangeException();
        
        return res;
    }
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        try{
            int newMark = 2 * getCredits();
            this.theQuestionManager.setWeight(categoryIndex, questionIndex, newMark);
            new QuestionWin(this.categoryIndex, this.questionIndex, gameCore, this.theMainWindow, true).setVisible(true);
            this.dispose();
        }
        catch(CreditRangeException | NumberFormatException e){
            this.creditsTextField.setText(Integer.toString(this.totalCredits));
            this.creditSlider.setValue(this.totalCredits);
            this.creditsTextField.selectAll();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider creditSlider;
    private javax.swing.JTextField creditsTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
