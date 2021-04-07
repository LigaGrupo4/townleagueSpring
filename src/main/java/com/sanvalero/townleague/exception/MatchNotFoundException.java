package com.sanvalero.townleague.exception;

public class MatchNotFoundException extends RuntimeException {


    public MatchNotFoundException(long id){
        super("Matches not found " + id );
    }
}



