package com.sanvalero.townleague.exception;

public class RefereeNotFoundException extends RuntimeException{

    public RefereeNotFoundException(long id){
        super("Referee ["+ id + "] not found exception");
    }
}
