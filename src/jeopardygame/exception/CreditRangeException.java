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
public class CreditRangeException extends Exception {
    public CreditRangeException(){
        super("Amount has to be larger than 0 and less than total Credits");
    }
}
