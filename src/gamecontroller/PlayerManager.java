/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecontroller;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import exception.*;
import java.util.Observable;
import java.util.Set;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author student
 */
public class PlayerManager extends Observable{
    private final HashMap<String, Integer> allPlayerNameMarks;
    private final ArrayList<String> currPlayerNames;
    private final ArrayList<Character> currPlayerKeys;
    private final ArrayList<Integer> currPlayerCredits;
    private final int initialMark = 1000;
    private int answeringPlayerIndex;
    private ArrayList<Character> forbiddenKeys;
    
    private class NameMarkPairComparator implements Comparator<Player>{
        @Override
        public int compare(Player a, Player b){
            return a.mark > b.mark ? 1 : -1;
        }
    }
    
    public PlayerManager(){
        allPlayerNameMarks = new HashMap();
        currPlayerNames = new ArrayList();
        currPlayerKeys = new ArrayList();
        currPlayerCredits = new ArrayList();
        forbiddenKeys = new ArrayList<>();
        answeringPlayerIndex = -1;
    }
    
    public void start() throws NotEnoughPlayersException{
        if(currPlayerNames.size() <= 1){
            throw new NotEnoughPlayersException();
        }
        
        Random rnd = new Random();
        this.answeringPlayerIndex = rnd.nextInt(currPlayerKeys.size());
    }
    
    public void end() {
        Iterator<String> nameIterator = currPlayerNames.iterator();
        Iterator<Integer> markIterator = currPlayerCredits.iterator();

        while(nameIterator.hasNext()){
            String name = nameIterator.next();
            int mark = markIterator.next();
            allPlayerNameMarks.put(name, mark);
        }
        
        currPlayerNames.clear();
        currPlayerKeys.clear();
        currPlayerCredits.clear();
    }
    
    private void validatePlayer(String name, char key) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException, EmptyPlayerKeyException{
        
        if(name.length() == 0)
            throw new EmptyPlayerNameException();
        
        if(key == 0)
            throw new EmptyPlayerKeyException();
            
        if(currPlayerNames.contains(name))
            throw new DuplicateNameException();

        
        if(currPlayerKeys.contains(key))
            throw new DuplicateKeyException();
        
    }
    
    public void addNewPlayer(String name, char key) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException, EmptyPlayerKeyException{
        validatePlayer(name, key);
        
        currPlayerNames.add(name);
        currPlayerKeys.add(key);
        currPlayerCredits.add(initialMark);
        
        this.setChanged();
        this.notifyObservers(this.getNumOfCurrentPlayers());
    }
    
    public int getNumOfCurrentPlayers(){
        return currPlayerNames.size();
    }
    
    public void modifyPlayer(int playerIndex, String newName, char newKey) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException, EmptyPlayerKeyException{
        String oldName = this.getCurrentPlayerName(playerIndex);
        char oldKey = this.getCurrentPlayerKey(playerIndex);
        
        try{            
            currPlayerNames.set(playerIndex, "");
        
            currPlayerKeys.set(playerIndex, (char)0);

            validatePlayer(newName, newKey);

            this.currPlayerNames.set(playerIndex, newName);
            this.currPlayerKeys.set(playerIndex, newKey);
        }
        catch(DuplicateNameException | DuplicateKeyException | EmptyPlayerNameException | EmptyPlayerKeyException e){
            currPlayerNames.set(playerIndex, oldName);
            currPlayerKeys.set(playerIndex, oldKey);
            
            throw e;
        }
        
    }
    
    public char getCurrentPlayerKey(int playerIndex){
        return currPlayerKeys.get(playerIndex);
    }
    
    public String getCurrentPlayerName(int playerIndex){
        return currPlayerNames.get(playerIndex);
    }
    
    public int getCurrentPlayerCredits(int playerIndex){
        return this.currPlayerCredits.get(playerIndex);
    }
    
    public ArrayList<Player> getOrderedPlayers(){
        Set<String> keys = allPlayerNameMarks.keySet();
        String[] names = keys.toArray(new String[0]);
        
        List<String> nameList = Arrays.asList(names);
        Iterator<String> nameIterator = nameList.iterator();
        
        ArrayList<Player> nameMarks = new ArrayList();
        while(nameIterator.hasNext()){
            String name = nameIterator.next();
            
            nameMarks.add(new Player(name, allPlayerNameMarks.get(name)));
        }

        nameMarks.sort(new NameMarkPairComparator().reversed());
        
        
        return nameMarks;
    }
    
    public boolean setAnsweringPlayer(char key){
        boolean success = false;
        if(!forbiddenKeys.contains(key)){
            this.answeringPlayerIndex = currPlayerKeys.indexOf(key);
            success =  this.answeringPlayerIndex != -1;
        }
        
        return success;
    }
    
    public int getAnsweringPlayerIndex(){
        return answeringPlayerIndex;
    }
    
    public void wrong(int offset){
        char key = currPlayerKeys.get(answeringPlayerIndex);
        forbiddenKeys.add(key);
        int mark = currPlayerCredits.get(answeringPlayerIndex) + offset;
        currPlayerCredits.set(answeringPlayerIndex, mark);
    }
    
    public void right(int offset){
        int mark = currPlayerCredits.get(answeringPlayerIndex) + offset;
        currPlayerCredits.set(answeringPlayerIndex, mark);
    }
    
    public void clearForbiddenPlayers(){
        this.forbiddenKeys.clear();
    }
    
    public int numberOfAllowablePlayers(){
        return currPlayerNames.size() - forbiddenKeys.size();
    }
}
