package org.game.common.util.exceptions;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException() {
        super("User Not Exists In Lists");
    }
}
