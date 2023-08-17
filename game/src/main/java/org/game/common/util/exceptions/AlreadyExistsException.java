package org.game.common.util.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(){
        super("Already Exists!");
    }
}
