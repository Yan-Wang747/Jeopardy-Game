/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.exception;

/**
 *
 * @author student
 */
public class DuplicateNameException extends Exception {
    public final int playerIndex;
            
    public DuplicateNameException(int playerIndex){
        super("Name already exists");
        this.playerIndex = playerIndex;
    }
}
