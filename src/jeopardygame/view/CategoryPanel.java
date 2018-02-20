/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.view;

import java.awt.Panel;
import javax.swing.JButton;
import java.awt.Dimension;
/**
 *
 * @author student
 */
public class CategoryPanel extends Panel{
    private JButton categoryButton;
    
    public CategoryPanel(String categoryString){
        super();
        this.setLayout(new java.awt.GridLayout(6, 0, 0, 10));
        this.setPreferredSize(new Dimension(350, 625));
        categoryButton = new JButton();
        categoryButton.setText(categoryString);
        categoryButton.setEnabled(false);
        this.add(categoryButton);
    } 
}
