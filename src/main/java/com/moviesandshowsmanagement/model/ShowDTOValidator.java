package com.moviesandshowsmanagement.model;

import java.util.Objects;

public class ShowDTOValidator {
    public static void validate(ShowDTO shdto){
        Integer movie_id = shdto.getMovie_id();
        validateMovieId(movie_id);

        Integer auditorium_id = shdto.getAuditorium_id();
        validateAuditoriumId(auditorium_id);

        String show_time = shdto.getShow_time();
        String end_time = shdto.getEnd_time();
        validateShowTimeAndEndTime(show_time,end_time);

//        String status = shdto.getStatus();
//        validateStatus(status);
    }
    public static  void validateMovieId(Integer movie_id){
        if(movie_id <= 0)
            throw new RuntimeException("Movie Id can't be less than 1");
    }
    public static void validateAuditoriumId(Integer auditorium_id){
        if(auditorium_id <= 0)
            throw new RuntimeException("Auditorium Id can't be less than 1");
    }
    public static void validateShowTimeAndEndTime(String show_time,String end_time){
        if( Objects.equals(end_time, show_time))
            throw new RuntimeException("Show Time should not equals to end time");
    }

//    public static void validateStatus(String status){
//        if(status == null || status.trim().isEmpty())
//            throw new RuntimeException("Status can't be null or empty");
//        if(status.length() < 5 || status.length() > 10)
//            throw new RuntimeException("Status can't be less than 5 or 10 chars");
//    }

}
