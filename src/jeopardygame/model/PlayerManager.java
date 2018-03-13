/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;
import jeopardygame.exception.DuplicateKeyException;
import jeopardygame.exception.DuplicateNameException;
import jeopardygame.exception.EmptyPlayerNameException;
import jeopardygame.exception.NotEnoughPlayersException;

/**
 *
 * @author student
 */
public class PlayerManager extends Observable implements Serializable{
    private final ArrayList<Player> allPlayers;
    private final ArrayList<Player> players;
    private int answeringPlayerIndex;
    private final ArrayList<Character> forbiddenKeys;
    private boolean started;
    
    public PlayerManager(){
        allPlayers = new ArrayList();
        players = new ArrayList();
        forbiddenKeys = new ArrayList<>();
        started = false;
    }
    
    public void start() throws NotEnoughPlayersException{
        if(players.size() <= 1){
            throw new NotEnoughPlayersException();
        }
        
        Random rnd = new Random();
        this.answeringPlayerIndex = rnd.nextInt(players.size());
        started = true;
    }
    
    public void end() {
        if(started)
            players.forEach((player) -> {
                int loc = this.locatePlayerInAllPlayerList(player);
                if(loc != -1){
                    int oldCredits = allPlayers.get(loc).getCredits();
                    int largerCredits = player.getCredits() > oldCredits ? player.getCredits() : oldCredits;
                    player.setCredits(largerCredits);
                    allPlayers.remove(loc);
                }
                
                this.allPlayers.add(player);
            });
        
        
        players.clear();
        this.deleteObservers();
    }
    
    public boolean setAnsweringPlayer(char key){
        boolean found = false;
        for(int i = 0; i < players.size() && !found && !forbiddenKeys.contains(key); i++){
            if(players.get(i).getKey() == key){
                this.answeringPlayerIndex = i;
                found = true;
            }
        }
        
        return found;
    }
    
    public int getAnsweringPlayerIndex(){
        return this.answeringPlayerIndex;
    }
    
    private int locatePlayerInAllPlayerList(Player player){
        int res = -1;
        
        for(int i = 0; i < allPlayers.size() && res == -1; i++)
            if(allPlayers.get(i).getName().equals(player.getName()))
                res = i;

        return res;
    }
    
    private void validatePlayer(ArrayList<Player> players, Player newPlayer)throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException{
        boolean nameIsFound = false, keyIsFound = false;
        
        if(newPlayer.getName().equals(""))
            throw new EmptyPlayerNameException();
        
        int i;
        for(i = 0; i < players.size() && !nameIsFound && !keyIsFound; i++){
            if(i == newPlayer.playerIndex)
                continue;
            
            nameIsFound = players.get(i).getName().equals(newPlayer.getName());
            keyIsFound = players.get(i).getKey() == newPlayer.getKey();
            
        }
        
        if(nameIsFound)
            throw new DuplicateNameException(i - 1);
        if(keyIsFound)
            throw new DuplicateKeyException();
    }
    
    public void addPlayer(Player newPlayer) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException{
        newPlayer.setName(newPlayer.getName().trim().toUpperCase());
        validatePlayer(this.players, newPlayer);
        
        if(newPlayer.playerIndex < this.players.size())
            players.set(newPlayer.playerIndex, newPlayer);
        else
            players.add(newPlayer);
        
        this.setChanged();
        this.notifyObservers(this.getNumOfCurrentPlayers());
    }
    
    public int getNumOfCurrentPlayers(){
        return players.size();
    }
    
    public Player getPlayer(int playerIndex){
        return players.get(playerIndex);
    }
    
    public ArrayList<Player> getOrderedPlayers(boolean forAll){
        ArrayList<Player> res;
        if(forAll)
            res = this.allPlayers;
        else
            res = (ArrayList<Player>)this.players.clone();
         
        res.sort(null);
        Collections.reverse(res);
        
        return res;
    }
    
    public void changeCredit(int offset){
        if(offset < 0){
            char key = players.get(this.answeringPlayerIndex).getKey();
            forbiddenKeys.add(key);
        }
        
        int newCredits = players.get(this.answeringPlayerIndex).getCredits() + offset;
        players.get(this.answeringPlayerIndex).setCredits(newCredits);
    }
    
    public void clearForbiddenPlayers(){
        this.forbiddenKeys.clear();
    }
    
    public int numberOfAllowablePlayers(){
        return players.size() - forbiddenKeys.size();
    }
}
