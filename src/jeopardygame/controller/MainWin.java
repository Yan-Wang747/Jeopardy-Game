/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.controller;

import jeopardygame.visualeffect.JeopardyColors;
import jeopardygame.model.JeopardyGame;
import jeopardygame.model.Player;
import jeopardygame.constant.JeopardyGameConstants;
import java.util.ArrayList;
import javax.swing.*;
import jeopardygame.sharedmodel.Category;
import jeopardygame.sharedmodel.Question;

public class MainWin extends javax.swing.JFrame{

    /**
     * Creates new form GameGUI
     */
    private final JeopardyGame gameCore;
    private int answeredQuestions = 0;
    private final JToggleButton[][] buttons = new JToggleButton[6][5];
    private final JTextArea[] categoryTextAreas = new JTextArea[6];
         
    public MainWin(JeopardyGame gameCore) {
        initComponents();
        this.gameCore = gameCore;
        this.creditPanel.setPreferredSize(JeopardyGameConstants.CREDIT_PANEL_SIZE);
        initQuestionButtons();
        initQuestionArray();
        JeopardyColors.setComponentColor(rootPane);
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
        if(gameCore.isDoubleJeopardy(categoryIndex, questionIndex))
            new BetWin(categoryIndex, questionIndex, gameCore, this).setVisible(true);
        else
            new QuestionWin(categoryIndex, questionIndex, gameCore, this, false).setVisible(true);
        
        this.setVisible(false);
        answeredQuestions++;
    }
    
    private void initQuestionButtons(){
        buttons[0][0] = c0q0;
        buttons[0][1] = c0q1;
        buttons[0][2] = c0q2;
        buttons[0][3] = c0q3;
        buttons[0][4] = c0q4;
        buttons[1][0] = c1q0;
        buttons[1][1] = c1q1;
        buttons[1][2] = c1q2;
        buttons[1][3] = c1q3;
        buttons[1][4] = c1q4;
        buttons[2][0] = c2q0;
        buttons[2][1] = c2q1;
        buttons[2][2] = c2q2;
        buttons[2][3] = c2q3;
        buttons[2][4] = c2q4;
        buttons[3][0] = c3q0;
        buttons[3][1] = c3q1;
        buttons[3][2] = c3q2;
        buttons[3][3] = c3q3;
        buttons[3][4] = c3q4;
        buttons[4][0] = c4q0;
        buttons[4][1] = c4q1;
        buttons[4][2] = c4q2;
        buttons[4][3] = c4q3;
        buttons[4][4] = c4q4;
        buttons[5][0] = c5q0;
        buttons[5][1] = c5q1;
        buttons[5][2] = c5q2;
        buttons[5][3] = c5q3;
        buttons[5][4] = c5q4;
        
        for (JToggleButton[] button : buttons) {
            for (JToggleButton button1 : button) {
                button1.setVisible(false);
            }
        }
        
        categoryTextAreas[0] = category0Text;
        categoryTextAreas[1] = category1Text;
        categoryTextAreas[2] = category2Text;
        categoryTextAreas[3] = category3Text;
        categoryTextAreas[4] = category4Text;
        categoryTextAreas[5] = category5Text;
    }
    
    private void initQuestionArray(){
        for(int categoryIndex = 0; categoryIndex < this.gameCore.getNumOfCategories(); categoryIndex++){
             Category category = gameCore.getCategory(categoryIndex);
             categoryTextAreas[categoryIndex].setText(category.getCategoryText());
             
             for(int questionIndex = 0; questionIndex < category.getNumberOfQuestions(); questionIndex++) {
                 Question question = category.questions.get(questionIndex);
                 buttons[categoryIndex][questionIndex].setText("$" + Integer.toString(question.getCredits()));
                 buttons[categoryIndex][questionIndex].setVisible(true);
             }
        }
    }                             

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        creditPanel = new javax.swing.JPanel();
        pickingPlayerName = new javax.swing.JLabel();
        endGameButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        category0Text = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        category1Text = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        category2Text = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        category3Text = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        category4Text = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        category5Text = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        c0q0 = new javax.swing.JToggleButton();
        c1q0 = new javax.swing.JToggleButton();
        c2q0 = new javax.swing.JToggleButton();
        c3q0 = new javax.swing.JToggleButton();
        c4q0 = new javax.swing.JToggleButton();
        c5q0 = new javax.swing.JToggleButton();
        c0q1 = new javax.swing.JToggleButton();
        c1q1 = new javax.swing.JToggleButton();
        c2q1 = new javax.swing.JToggleButton();
        c3q1 = new javax.swing.JToggleButton();
        c4q1 = new javax.swing.JToggleButton();
        c5q1 = new javax.swing.JToggleButton();
        c0q2 = new javax.swing.JToggleButton();
        c1q2 = new javax.swing.JToggleButton();
        c2q2 = new javax.swing.JToggleButton();
        c3q2 = new javax.swing.JToggleButton();
        c4q2 = new javax.swing.JToggleButton();
        c5q2 = new javax.swing.JToggleButton();
        c0q3 = new javax.swing.JToggleButton();
        c1q3 = new javax.swing.JToggleButton();
        c2q3 = new javax.swing.JToggleButton();
        c3q3 = new javax.swing.JToggleButton();
        c4q3 = new javax.swing.JToggleButton();
        c5q3 = new javax.swing.JToggleButton();
        c0q4 = new javax.swing.JToggleButton();
        c1q4 = new javax.swing.JToggleButton();
        c2q4 = new javax.swing.JToggleButton();
        c3q4 = new javax.swing.JToggleButton();
        c4q4 = new javax.swing.JToggleButton();
        c5q4 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setResizable(false);
        setSize(new java.awt.Dimension(1920, 1080));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jScrollPane2.setBorder(null);

        creditPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 5));
        jScrollPane2.setViewportView(creditPanel);

        pickingPlayerName.setFont(new java.awt.Font("SimSun", 0, 48)); // NOI18N
        pickingPlayerName.setText("Name");

        endGameButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        endGameButton.setText("End Game");
        endGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endGameButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new java.awt.GridLayout(1, 6, 10, 10));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        category0Text.setColumns(20);
        category0Text.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category0Text.setLineWrap(true);
        category0Text.setRows(5);
        category0Text.setSize(new java.awt.Dimension(240, 70));
        jScrollPane1.setViewportView(category0Text);

        jPanel2.add(jScrollPane1);

        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        category1Text.setColumns(20);
        category1Text.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category1Text.setLineWrap(true);
        category1Text.setRows(5);
        jScrollPane8.setViewportView(category1Text);

        jPanel2.add(jScrollPane8);

        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        category2Text.setColumns(20);
        category2Text.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category2Text.setLineWrap(true);
        category2Text.setRows(5);
        jScrollPane9.setViewportView(category2Text);

        jPanel2.add(jScrollPane9);

        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        category3Text.setColumns(20);
        category3Text.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category3Text.setLineWrap(true);
        category3Text.setRows(5);
        jScrollPane10.setViewportView(category3Text);

        jPanel2.add(jScrollPane10);

        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        category4Text.setColumns(20);
        category4Text.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category4Text.setLineWrap(true);
        category4Text.setRows(5);
        jScrollPane11.setViewportView(category4Text);

        jPanel2.add(jScrollPane11);

        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        category5Text.setColumns(20);
        category5Text.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category5Text.setLineWrap(true);
        category5Text.setRows(5);
        jScrollPane12.setViewportView(category5Text);

        jPanel2.add(jScrollPane12);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new java.awt.GridLayout(5, 6, 10, 5));

        c0q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q0ActionPerformed(evt);
            }
        });
        jPanel1.add(c0q0);

        c1q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q0ActionPerformed(evt);
            }
        });
        jPanel1.add(c1q0);

        c2q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q0ActionPerformed(evt);
            }
        });
        jPanel1.add(c2q0);

        c3q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q0ActionPerformed(evt);
            }
        });
        jPanel1.add(c3q0);

        c4q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q0ActionPerformed(evt);
            }
        });
        jPanel1.add(c4q0);

        c5q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q0ActionPerformed(evt);
            }
        });
        jPanel1.add(c5q0);

        c0q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q1ActionPerformed(evt);
            }
        });
        jPanel1.add(c0q1);

        c1q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q1ActionPerformed(evt);
            }
        });
        jPanel1.add(c1q1);

        c2q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q1ActionPerformed(evt);
            }
        });
        jPanel1.add(c2q1);

        c3q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q1ActionPerformed(evt);
            }
        });
        jPanel1.add(c3q1);

        c4q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q1ActionPerformed(evt);
            }
        });
        jPanel1.add(c4q1);

        c5q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q1ActionPerformed(evt);
            }
        });
        jPanel1.add(c5q1);

        c0q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q2ActionPerformed(evt);
            }
        });
        jPanel1.add(c0q2);

        c1q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q2ActionPerformed(evt);
            }
        });
        jPanel1.add(c1q2);

        c2q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q2ActionPerformed(evt);
            }
        });
        jPanel1.add(c2q2);

        c3q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q2ActionPerformed(evt);
            }
        });
        jPanel1.add(c3q2);

        c4q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q2ActionPerformed(evt);
            }
        });
        jPanel1.add(c4q2);

        c5q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q2ActionPerformed(evt);
            }
        });
        jPanel1.add(c5q2);

        c0q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q3ActionPerformed(evt);
            }
        });
        jPanel1.add(c0q3);

        c1q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q3ActionPerformed(evt);
            }
        });
        jPanel1.add(c1q3);

        c2q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q3ActionPerformed(evt);
            }
        });
        jPanel1.add(c2q3);

        c3q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q3ActionPerformed(evt);
            }
        });
        jPanel1.add(c3q3);

        c4q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q3ActionPerformed(evt);
            }
        });
        jPanel1.add(c4q3);

        c5q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q3ActionPerformed(evt);
            }
        });
        jPanel1.add(c5q3);

        c0q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q4ActionPerformed(evt);
            }
        });
        jPanel1.add(c0q4);

        c1q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q4ActionPerformed(evt);
            }
        });
        jPanel1.add(c1q4);

        c2q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q4ActionPerformed(evt);
            }
        });
        jPanel1.add(c2q4);

        c3q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q4ActionPerformed(evt);
            }
        });
        jPanel1.add(c3q4);

        c4q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q4.setToolTipText("");
        c4q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q4ActionPerformed(evt);
            }
        });
        jPanel1.add(c4q4);

        c5q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q4ActionPerformed(evt);
            }
        });
        jPanel1.add(c5q4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(endGameButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pickingPlayerName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(endGameButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pickingPlayerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
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

    private void endGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endGameButtonActionPerformed
        // TODO add your handling code here:
        new EndGameDialog(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_endGameButtonActionPerformed

    private void c1q0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1q0ActionPerformed
        // TODO add your handling code here:
        this.buttons[1][0].setVisible(false);
        this.showQuestionWindow(1, 0);
    }//GEN-LAST:event_c1q0ActionPerformed

    private void c0q0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c0q0ActionPerformed
        // TODO add your handling code here:
        this.buttons[0][0].setVisible(false);
        this.showQuestionWindow(0, 0);
    }//GEN-LAST:event_c0q0ActionPerformed

    private void c0q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c0q1ActionPerformed
        // TODO add your handling code here:
        this.buttons[0][1].setVisible(false);
        this.showQuestionWindow(0, 1);
    }//GEN-LAST:event_c0q1ActionPerformed

    private void c0q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c0q2ActionPerformed
        // TODO add your handling code here:
        this.buttons[0][2].setVisible(false);
        this.showQuestionWindow(0, 2);
    }//GEN-LAST:event_c0q2ActionPerformed

    private void c0q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c0q3ActionPerformed
        // TODO add your handling code here:
        this.buttons[0][3].setVisible(false);
        this.showQuestionWindow(0, 3);
    }//GEN-LAST:event_c0q3ActionPerformed

    private void c0q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c0q4ActionPerformed
        // TODO add your handling code here:
        this.buttons[0][4].setVisible(false);
        this.showQuestionWindow(0, 4);
    }//GEN-LAST:event_c0q4ActionPerformed

    private void c1q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1q1ActionPerformed
        // TODO add your handling code here:
        this.buttons[1][1].setVisible(false);
        this.showQuestionWindow(1, 1);
    }//GEN-LAST:event_c1q1ActionPerformed

    private void c1q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1q2ActionPerformed
        // TODO add your handling code here:
        this.buttons[1][2].setVisible(false);
        this.showQuestionWindow(1, 2);
    }//GEN-LAST:event_c1q2ActionPerformed

    private void c1q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1q3ActionPerformed
        // TODO add your handling code here:
        this.buttons[1][3].setVisible(false);
        this.showQuestionWindow(1, 3);
    }//GEN-LAST:event_c1q3ActionPerformed

    private void c1q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1q4ActionPerformed
        // TODO add your handling code here:
        this.buttons[1][4].setVisible(false);
        this.showQuestionWindow(1, 4);
    }//GEN-LAST:event_c1q4ActionPerformed

    private void c2q0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2q0ActionPerformed
        // TODO add your handling code here:
        this.buttons[2][0].setVisible(false);
        this.showQuestionWindow(2, 0);
    }//GEN-LAST:event_c2q0ActionPerformed

    private void c2q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2q1ActionPerformed
        // TODO add your handling code here:
        this.buttons[2][1].setVisible(false);
        this.showQuestionWindow(2, 1);
    }//GEN-LAST:event_c2q1ActionPerformed

    private void c2q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2q2ActionPerformed
        // TODO add your handling code here:
        this.buttons[2][2].setVisible(false);
        this.showQuestionWindow(2, 2);
    }//GEN-LAST:event_c2q2ActionPerformed

    private void c2q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2q3ActionPerformed
        // TODO add your handling code here:
        this.buttons[2][3].setVisible(false);
        this.showQuestionWindow(2, 3);
    }//GEN-LAST:event_c2q3ActionPerformed

    private void c2q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2q4ActionPerformed
        // TODO add your handling code here:
        this.buttons[2][4].setVisible(false);
        this.showQuestionWindow(2, 4);
    }//GEN-LAST:event_c2q4ActionPerformed

    private void c3q0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3q0ActionPerformed
        // TODO add your handling code here:
        this.buttons[3][0].setVisible(false);
        this.showQuestionWindow(3, 0);
    }//GEN-LAST:event_c3q0ActionPerformed

    private void c3q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3q1ActionPerformed
        // TODO add your handling code here:
        this.buttons[3][1].setVisible(false);
        this.showQuestionWindow(3, 1);
    }//GEN-LAST:event_c3q1ActionPerformed

    private void c3q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3q2ActionPerformed
        // TODO add your handling code here:
        this.buttons[3][2].setVisible(false);
        this.showQuestionWindow(3, 2);
    }//GEN-LAST:event_c3q2ActionPerformed

    private void c3q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3q3ActionPerformed
        // TODO add your handling code here:
        this.buttons[3][3].setVisible(false);
        this.showQuestionWindow(3, 3);
    }//GEN-LAST:event_c3q3ActionPerformed

    private void c3q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c3q4ActionPerformed
        // TODO add your handling code here:
        this.buttons[3][4].setVisible(false);
        this.showQuestionWindow(3, 4);
    }//GEN-LAST:event_c3q4ActionPerformed

    private void c4q0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4q0ActionPerformed
        // TODO add your handling code here:
        this.buttons[4][0].setVisible(false);
        this.showQuestionWindow(4, 0);
    }//GEN-LAST:event_c4q0ActionPerformed

    private void c4q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4q1ActionPerformed
        // TODO add your handling code here:
        this.buttons[4][1].setVisible(false);
        this.showQuestionWindow(4, 1);
    }//GEN-LAST:event_c4q1ActionPerformed

    private void c4q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4q2ActionPerformed
        // TODO add your handling code here:
        this.buttons[4][2].setVisible(false);
        this.showQuestionWindow(4, 2);
    }//GEN-LAST:event_c4q2ActionPerformed

    private void c4q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4q3ActionPerformed
        // TODO add your handling code here:
        this.buttons[4][3].setVisible(false);
        this.showQuestionWindow(4, 3);
    }//GEN-LAST:event_c4q3ActionPerformed

    private void c4q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c4q4ActionPerformed
        // TODO add your handling code here:
        this.buttons[4][4].setVisible(false);
        this.showQuestionWindow(4, 4);
    }//GEN-LAST:event_c4q4ActionPerformed

    private void c5q0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c5q0ActionPerformed
        // TODO add your handling code here:
        this.buttons[5][0].setVisible(false);
        this.showQuestionWindow(5, 0);
    }//GEN-LAST:event_c5q0ActionPerformed

    private void c5q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c5q1ActionPerformed
        // TODO add your handling code here:
        this.buttons[5][1].setVisible(false);
        this.showQuestionWindow(5, 1);
    }//GEN-LAST:event_c5q1ActionPerformed

    private void c5q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c5q2ActionPerformed
        // TODO add your handling code here:
        this.buttons[5][2].setVisible(false);
        this.showQuestionWindow(5, 2);
    }//GEN-LAST:event_c5q2ActionPerformed

    private void c5q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c5q3ActionPerformed
        // TODO add your handling code here:
        this.buttons[5][3].setVisible(false);
        this.showQuestionWindow(5, 3);
    }//GEN-LAST:event_c5q3ActionPerformed

    private void c5q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c5q4ActionPerformed
        // TODO add your handling code here:
        this.buttons[5][4].setVisible(false);
        this.showQuestionWindow(5, 4);
    }//GEN-LAST:event_c5q4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton c0q0;
    private javax.swing.JToggleButton c0q1;
    private javax.swing.JToggleButton c0q2;
    private javax.swing.JToggleButton c0q3;
    private javax.swing.JToggleButton c0q4;
    private javax.swing.JToggleButton c1q0;
    private javax.swing.JToggleButton c1q1;
    private javax.swing.JToggleButton c1q2;
    private javax.swing.JToggleButton c1q3;
    private javax.swing.JToggleButton c1q4;
    private javax.swing.JToggleButton c2q0;
    private javax.swing.JToggleButton c2q1;
    private javax.swing.JToggleButton c2q2;
    private javax.swing.JToggleButton c2q3;
    private javax.swing.JToggleButton c2q4;
    private javax.swing.JToggleButton c3q0;
    private javax.swing.JToggleButton c3q1;
    private javax.swing.JToggleButton c3q2;
    private javax.swing.JToggleButton c3q3;
    private javax.swing.JToggleButton c3q4;
    private javax.swing.JToggleButton c4q0;
    private javax.swing.JToggleButton c4q1;
    private javax.swing.JToggleButton c4q2;
    private javax.swing.JToggleButton c4q3;
    private javax.swing.JToggleButton c4q4;
    private javax.swing.JToggleButton c5q0;
    private javax.swing.JToggleButton c5q1;
    private javax.swing.JToggleButton c5q2;
    private javax.swing.JToggleButton c5q3;
    private javax.swing.JToggleButton c5q4;
    private javax.swing.JTextArea category0Text;
    private javax.swing.JTextArea category1Text;
    private javax.swing.JTextArea category2Text;
    private javax.swing.JTextArea category3Text;
    private javax.swing.JTextArea category4Text;
    private javax.swing.JTextArea category5Text;
    private javax.swing.JPanel creditPanel;
    private javax.swing.JButton endGameButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel pickingPlayerName;
    // End of variables declaration//GEN-END:variables
}
