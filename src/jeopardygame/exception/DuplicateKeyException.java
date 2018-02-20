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
public class DuplicateKeyException extends Exception {
    public DuplicateKeyException(){
        super("Key already exists");
    }
}
