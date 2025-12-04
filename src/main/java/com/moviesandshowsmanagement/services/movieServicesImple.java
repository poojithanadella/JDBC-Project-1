package com.moviesandshowsmanagement.services;

import com.moviesandshowsmanagement.Dao.*;
import com.moviesandshowsmanagement.model.*;
import java.util.Scanner;

public class movieServicesImple implements movieServices {
    Scanner sc1 = new Scanner(System.in);

    public void addMovie() {
        System.out.println("Enter Movie Details");
        System.out.println("add movie name");
        String title = sc1.next();
        System.out.println("add movie language ");
        String language = sc1.next();
        System.out.println("add movie duration_min");
        Integer duration_min = sc1.nextInt();
        System.out.println("add movie certification");
        String certification = sc1.next();
        System.out.println("add movie status");
        String status = sc1.next();
        System.out.println("add movie created_at");
        String created_at = sc1.next();
        MovieDTO mdto = new MovieDTO();
        mdto.setTitle(title);
        mdto.setLanguage(language);
        mdto.setDuration_min(duration_min);
        mdto.setCertification(certification);
        mdto.setStatus(status);
        mdto.setCreated_at(created_at);
        MovieDTOValidation.validate(mdto);
        MovieDAO mdao = new MovieDAO();
        mdao.addMovie(mdto);
        System.out.println("Movie added successfully");
    }

    public void createShow() {
        System.out.println("Enter Show Details");
        System.out.println("enter the movie id ");
        Integer movie_id = sc1.nextInt();
        System.out.println("enter the auditorium id ");
        Integer auditorium_id = sc1.nextInt();
        sc1.nextLine();
        System.out.println("enter the start time in this format:yyyy-MM-dd HH:mm:ss ");
        String show_time = sc1.nextLine();
        System.out.println("enter the end time  in this format:yyyy-MM-dd HH:mm:ss ");
        String end_time = sc1.nextLine();
        ShowDTO shdto = new ShowDTO();
        shdto.setMovie_id(movie_id);
        shdto.setAuditorium_id(auditorium_id);
        shdto.setShow_time(show_time);
        shdto.setEnd_time(end_time);
        //ShowDTOValidator.validate(shdto);
        ShowDAO sdao = new ShowDAO();
        sdao.createShow(shdto);
    }



}

