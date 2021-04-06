package com.sanvalero.townleague.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(long id){
        super("Player [" + id + "] not found exception");
    }
}
