/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamegui;
import javax.swing.JButton;
/**
 *
 * @author student
 */
public class QuestionButton extends JButton{
    public final int categoryIndex;
    public final int questionIndex;
    
    public QuestionButton(int categoryIndex, int questionIndex){
        super();
        this.categoryIndex = categoryIndex;
        this.questionIndex = questionIndex;
    }
    
}
