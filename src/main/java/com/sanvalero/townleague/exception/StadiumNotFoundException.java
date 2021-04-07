package com.sanvalero.townleague.exception;

public class StadiumNotFoundException extends RuntimeException{


    public StadiumNotFoundException(long id){
            super("Stadiums not found " + id );
        }

    public  StadiumNotFoundException(String name){
        super("Stadium [" + name + "] not found exception");
    }
}

