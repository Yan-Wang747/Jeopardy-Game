/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author student
 */
public class NotEnoughPlayersException extends Exception {
    public NotEnoughPlayersException(){
        super("Not enough players");
    }
}
