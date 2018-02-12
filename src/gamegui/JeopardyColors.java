/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamegui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JTextField;

/**
 *
 * @author student
 */
public class JeopardyColors {
    public static final Color BACKGROUND = new Color(3, 67, 122);
    public static final Color FONT =  new Color(255,204,51);
    
    public static void setComponentColor(Component component){
        component.setBackground(JeopardyColors.BACKGROUND);

        component.setForeground(JeopardyColors.FONT);


        if(component instanceof Container){
            Component[] subComponents = ((Container)component).getComponents();

            for(Component comp : subComponents)
                setComponentColor(comp);
        }

        return;
    }
}
