/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamecontroller;

/**
 *
 * @author iqapp
 */
import exception.*;
import java.io.*;

public class JeopardyGame {
    private final QuestionManager theQuestionManager;
    private final PlayerManager thePlayerManager;
    private boolean isStarted;

    public JeopardyGame(){
        theQuestionManager = new QuestionManager();
        thePlayerManager = new PlayerManager();
        this.isStarted = false;
    }
    
    public PlayerManager getPlayerManager(){
        return thePlayerManager;
    }
    
    public QuestionManager getQuestionManager(){
        return this.theQuestionManager;
    }
    
    public void start(String filename) throws NotEnoughPlayersException, FileNotFoundException, IOException{
        try{
            theQuestionManager.start(filename);
            thePlayerManager.start();
            this.isStarted = true;
        }
        catch(NotEnoughPlayersException e){
            theQuestionManager.end();
            throw e;
        }
    }
    
    public void end(){
        theQuestionManager.end();
        thePlayerManager.end();
        this.isStarted = false;
    }
    
    public boolean isStarted(){
        return isStarted;
    }
    
}
