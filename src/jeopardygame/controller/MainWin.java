/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.controller;

import java.awt.Color;
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
    private final JButton[] categoryTextButtons = new JButton[6];
    private boolean gameIsEnded = false;
         
    public MainWin(JeopardyGame gameCore) {
        initComponents();
        JeopardyColors.setComponentColor(rootPane);
        this.gameCore = gameCore;
        this.creditPanel.setPreferredSize(JeopardyGameConstants.CREDIT_PANEL_SIZE);
        initQuestionButtons();
        initQuestionArray();
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.categoryTextPanel.setBackground(Color.BLACK);
        this.questionPanel.setBackground(Color.BLACK);
        this.creditPanel.setBackground(Color.DARK_GRAY);
        this.jPanel1.setBackground(Color.DARK_GRAY);
    }
    
    private void putPlayers(){
        ArrayList<Player> players = this.gameCore.getOrderedPlayers(false);
        int fontSize = (int)(80 - 60.0 / 24 * (this.gameCore.getNumOfCurrentPlayers() - 2));
        this.creditPanel.removeAll();
        
        for(Player player : players) {
            JLabel playerLabel = new JLabel();
            playerLabel.setForeground(JeopardyColors.FONT);
            playerLabel.setFont(new java.awt.Font("Lucida Grande", 0, fontSize));
            playerLabel.setText(player.getName() + ": " + player.getCredits());
            this.creditPanel.add(playerLabel);
        }
        
        this.creditPanel.repaint();
    }
    
    @Override
    public void dispose(){
        if(gameIsEnded){
            gameCore.end();
            disposeWindow();
        }else {
            new EndGameDialog(this).setVisible(true);
            this.setVisible(false);
        }
    }
    
    public void endGame(){
        for(int categoryIndex = 0; categoryIndex < this.gameCore.getNumOfCategories(); categoryIndex++){
            Category category = gameCore.getCategory(categoryIndex);

            for(int questionIndex = 0; questionIndex < category.getNumberOfQuestions(); questionIndex++) 
                buttons[categoryIndex][questionIndex].setVisible(false);
        }
        
        gameIsEnded = true;
        this.pickingNameLabel.setText("Game ends, please click on the close button to return.");
    }
    
    private void disposeWindow(){
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
        
        categoryTextButtons[0] = category1TextButton;
        categoryTextButtons[1] = category2TextButton;
        categoryTextButtons[2] = category3TextButton;
        categoryTextButtons[3] = category4TextButton;
        categoryTextButtons[4] = category5TextButton;
        categoryTextButtons[5] = category6TextButton;
    }
    
    private void initQuestionArray(){
        for(int categoryIndex = 0; categoryIndex < this.gameCore.getNumOfCategories(); categoryIndex++){
             Category category = gameCore.getCategory(categoryIndex);
             categoryTextButtons[categoryIndex].setText(category.getCategoryText());
             
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
        categoryTextPanel = new javax.swing.JPanel();
        category1TextButton = new javax.swing.JButton();
        category2TextButton = new javax.swing.JButton();
        category3TextButton = new javax.swing.JButton();
        category4TextButton = new javax.swing.JButton();
        category5TextButton = new javax.swing.JButton();
        category6TextButton = new javax.swing.JButton();
        questionPanel = new javax.swing.JPanel();
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
        jPanel1 = new javax.swing.JPanel();
        pickingNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

        categoryTextPanel.setBackground(new java.awt.Color(0, 0, 0));
        categoryTextPanel.setLayout(new java.awt.GridLayout(1, 6, 10, 10));

        category1TextButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category1TextButton.setFocusable(false);
        categoryTextPanel.add(category1TextButton);

        category2TextButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category2TextButton.setFocusable(false);
        categoryTextPanel.add(category2TextButton);

        category3TextButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category3TextButton.setFocusable(false);
        categoryTextPanel.add(category3TextButton);

        category4TextButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category4TextButton.setFocusable(false);
        categoryTextPanel.add(category4TextButton);

        category5TextButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category5TextButton.setFocusable(false);
        categoryTextPanel.add(category5TextButton);

        category6TextButton.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        category6TextButton.setFocusable(false);
        category6TextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                category6TextButtonActionPerformed(evt);
            }
        });
        categoryTextPanel.add(category6TextButton);

        questionPanel.setBackground(new java.awt.Color(0, 0, 0));
        questionPanel.setLayout(new java.awt.GridLayout(5, 6, 10, 5));

        c0q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q0ActionPerformed(evt);
            }
        });
        questionPanel.add(c0q0);

        c1q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q0ActionPerformed(evt);
            }
        });
        questionPanel.add(c1q0);

        c2q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q0ActionPerformed(evt);
            }
        });
        questionPanel.add(c2q0);

        c3q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q0ActionPerformed(evt);
            }
        });
        questionPanel.add(c3q0);

        c4q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q0ActionPerformed(evt);
            }
        });
        questionPanel.add(c4q0);

        c5q0.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q0ActionPerformed(evt);
            }
        });
        questionPanel.add(c5q0);

        c0q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q1ActionPerformed(evt);
            }
        });
        questionPanel.add(c0q1);

        c1q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q1ActionPerformed(evt);
            }
        });
        questionPanel.add(c1q1);

        c2q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q1ActionPerformed(evt);
            }
        });
        questionPanel.add(c2q1);

        c3q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q1ActionPerformed(evt);
            }
        });
        questionPanel.add(c3q1);

        c4q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q1ActionPerformed(evt);
            }
        });
        questionPanel.add(c4q1);

        c5q1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q1ActionPerformed(evt);
            }
        });
        questionPanel.add(c5q1);

        c0q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q2ActionPerformed(evt);
            }
        });
        questionPanel.add(c0q2);

        c1q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q2ActionPerformed(evt);
            }
        });
        questionPanel.add(c1q2);

        c2q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q2ActionPerformed(evt);
            }
        });
        questionPanel.add(c2q2);

        c3q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q2ActionPerformed(evt);
            }
        });
        questionPanel.add(c3q2);

        c4q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q2ActionPerformed(evt);
            }
        });
        questionPanel.add(c4q2);

        c5q2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q2ActionPerformed(evt);
            }
        });
        questionPanel.add(c5q2);

        c0q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q3ActionPerformed(evt);
            }
        });
        questionPanel.add(c0q3);

        c1q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q3ActionPerformed(evt);
            }
        });
        questionPanel.add(c1q3);

        c2q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q3ActionPerformed(evt);
            }
        });
        questionPanel.add(c2q3);

        c3q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q3ActionPerformed(evt);
            }
        });
        questionPanel.add(c3q3);

        c4q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q3ActionPerformed(evt);
            }
        });
        questionPanel.add(c4q3);

        c5q3.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q3ActionPerformed(evt);
            }
        });
        questionPanel.add(c5q3);

        c0q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c0q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c0q4ActionPerformed(evt);
            }
        });
        questionPanel.add(c0q4);

        c1q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c1q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1q4ActionPerformed(evt);
            }
        });
        questionPanel.add(c1q4);

        c2q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c2q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2q4ActionPerformed(evt);
            }
        });
        questionPanel.add(c2q4);

        c3q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c3q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c3q4ActionPerformed(evt);
            }
        });
        questionPanel.add(c3q4);

        c4q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c4q4.setToolTipText("");
        c4q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c4q4ActionPerformed(evt);
            }
        });
        questionPanel.add(c4q4);

        c5q4.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        c5q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c5q4ActionPerformed(evt);
            }
        });
        questionPanel.add(c5q4);

        pickingNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        pickingNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pickingNameLabel.setText("Name");
        jPanel1.add(pickingNameLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(443, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(categoryTextPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(questionPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(444, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(categoryTextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(questionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if(!gameIsEnded){
            Player pickingPlayer = gameCore.getPlayer(gameCore.getAnsweringPlayerIndex());
            this.pickingNameLabel.setText(pickingPlayer.getName() + ", please pick a question");
        }
        
        this.putPlayers();
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
        
        if(answeredQuestions == gameCore.getNumOfTotalQuestions())
            endGame();
    }//GEN-LAST:event_formWindowActivated

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

    private void category6TextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_category6TextButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_category6TextButtonActionPerformed

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
    private javax.swing.JButton category1TextButton;
    private javax.swing.JButton category2TextButton;
    private javax.swing.JButton category3TextButton;
    private javax.swing.JButton category4TextButton;
    private javax.swing.JButton category5TextButton;
    private javax.swing.JButton category6TextButton;
    private javax.swing.JPanel categoryTextPanel;
    private javax.swing.JPanel creditPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pickingNameLabel;
    private javax.swing.JPanel questionPanel;
    // End of variables declaration//GEN-END:variables
}
