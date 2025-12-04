package com.moviesandshowsmanagement.exceptions;

public class AlreadyMovieIsRunningException extends RuntimeException{
    public AlreadyMovieIsRunningException(String message){
        super(message);
    }
}
