package com.sanvalero.townleague.exception;

import com.sanvalero.townleague.domain.Match;

public class MatchMandatoryFieldsException extends RuntimeException {
    public MatchMandatoryFieldsException(){
        super("Todos los campos son obligatorios");
    }

}
