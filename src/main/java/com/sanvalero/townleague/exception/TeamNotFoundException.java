package com.sanvalero.townleague.exception;

public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(long id){
        super("Team [" + id + "] not found exception");
    }
}
