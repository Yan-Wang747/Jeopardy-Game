/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.model;

import java.io.*;
/**
 *
 * @author student
 */
public class Player implements Comparable, Serializable{
    private String name;
    private int credits;
    private char key;
    public final int playerIndex;
    
    public Player(String name, char key, int credits, int playerIndex){
        this.name = name;
        this.credits = credits;
        this.key = key;
        this.playerIndex = playerIndex;
    }
    
    public void setCredits(int credits){
        this.credits = credits;
    }
    
    public int getCredits(){
        return credits;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setKey(char key){
        this.key = key;
    }
    
    public char getKey(){
        return this.key;
    }

    @Override
    public int compareTo(Object o) {
        Player anotherPlayer = (Player)o;
        return this.getCredits() > anotherPlayer.getCredits() ? 1 : -1;
    }
}
