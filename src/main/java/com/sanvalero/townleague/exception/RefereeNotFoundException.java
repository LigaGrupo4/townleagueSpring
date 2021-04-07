package com.sanvalero.townleague.exception;

public class RefereeNotFoundException extends RuntimeException{

    public RefereeNotFoundException(long id){
        super("Referee ["+ id + "] not found exception");
    }

    public RefereeNotFoundException(String name, String lastName){
        super("Referee [" + name + " " + lastName + "] not found exception");
    }
}
