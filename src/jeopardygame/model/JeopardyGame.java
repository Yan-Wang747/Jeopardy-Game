/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeopardygame.model;

/**
 *
 * @author iqapp
 */

import jeopardygame.exception.NotEnoughPlayersException;
import java.io.*;
import java.util.*;
import jeopardygame.sharedmodel.*;
import jeopardygame.exception.*;

public class JeopardyGame extends Observable{
    private QuestionManager theQuestionManager;
    private final PlayerManager thePlayerManager;
    private boolean isStarted;
    private int fileIndex;

    public JeopardyGame(){
        theQuestionManager = new QuestionManager();
        thePlayerManager = new PlayerManager();
        this.isStarted = false;
        fileIndex = 0;
    }
    
    public void start() throws ClassNotFoundException, NotEnoughPlayersException, FileNotFoundException, IOException{
        String filename = "Question" + Integer.toString(fileIndex);
        thePlayerManager.start();
        QuestionFileReader theQuestionFileReader = new QuestionFileReader(new File(filename));
        try{
            theQuestionManager = theQuestionFileReader.read();
            theQuestionManager.start();
            this.isStarted = true;
            fileIndex++;
        }catch (FileNotFoundException e){
            if(fileIndex != 0){
                fileIndex = 0;
                start();
            }else
                throw e;
   
        }
    }
    
    public void end(){
        theQuestionManager = null;
        thePlayerManager.end();
        this.isStarted = false;
    }
    
    public boolean setAnsweringPlayer(char key){
        return this.thePlayerManager.setAnsweringPlayer(key);
    }
    
    public int getAnsweringPlayerIndex(){
        return this.thePlayerManager.getAnsweringPlayerIndex();
    }
    
    public boolean isStarted(){
        return isStarted;
    }
    
    @Override
    public void addObserver(Observer o){
        this.thePlayerManager.addObserver(o);
    }
    
    @Override
    public void deleteObservers(){
        this.thePlayerManager.deleteObservers();
    }
    
    public int getNumOfCurrentPlayers(){
        return this.thePlayerManager.getNumOfCurrentPlayers();
    }
    
    public void addPlayer(Player newPlayer) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException{
        this.thePlayerManager.addPlayer(newPlayer);
    }
    
    public Player getPlayer(int playerIndex){
        return this.thePlayerManager.getPlayer(playerIndex);
    }
    
    public ArrayList<Player> getOrderedPlayers(boolean forAll){
        return this.thePlayerManager.getOrderedPlayers(forAll);
    }
    
    public boolean isDoubleJeopardy(int categoryIndex, int questionIndex){
        return this.theQuestionManager.isDoubleJeopardy(categoryIndex, questionIndex);
    }
    
    public Category getCategory(int index){
        return this.theQuestionManager.getCategory(index);
    }
    
    public Question getQuestion(int categoryIndex, int questionIndex){
        return this.theQuestionManager.getQuestion(categoryIndex, questionIndex);
    }
    
    public int getNumOfCategories(){
        return this.theQuestionManager.getNumOfCategories();
    }
    
    public int getNumOfQuestions(int categoryIndex){
        return this.theQuestionManager.getNumOfQuestions(categoryIndex);
    }
    
    public void clearForbiddenPlayers(){
        this.thePlayerManager.clearForbiddenPlayers();
    }
    
    public int numberOfAllowablePlayers(){
        return this.thePlayerManager.numberOfAllowablePlayers();
    }
    
    public void changeCredit(int offset){
        this.thePlayerManager.changeCredit(offset);
    }
    
    public void setCredits(int categoryIndex, int quesitonIndex, int newCredits){
        this.theQuestionManager.setCredits(categoryIndex, quesitonIndex, newCredits);
    }
}
