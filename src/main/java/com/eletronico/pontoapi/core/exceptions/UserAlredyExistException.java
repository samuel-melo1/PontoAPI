package com.eletronico.pontoapi.core.exceptions;

public class UserAlredyExistException extends RuntimeException{
    public UserAlredyExistException(String email){
        super("User alredy exist: " + email);
    }
}
