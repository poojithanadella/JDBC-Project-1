package com.moviesandshowsmanagement.services;

import com.moviesandshowsmanagement.Dao.BookingDAO;
import com.moviesandshowsmanagement.Dao.SeatDAO;
import com.moviesandshowsmanagement.model.BookingDTO;
import com.moviesandshowsmanagement.model.SeatDTO;

import java.util.Scanner;

public class BookingServiceImple implements BookingService {
    Scanner sc1=new Scanner(System.in);
    public void viewSeatAvailability() {
        System.out.println("view  Seat Availability");
        System.out.println("enter show id");
        Integer show_id = sc1.nextInt();
        SeatDTO sdto = new SeatDTO();
        sdto.setShow_id(show_id);
        SeatDAO sdao = new SeatDAO();
        sdao.viewSeatAvailability(sdto);
    }

    public void bookingTickets() {
        System.out.println("booking tickets");
        System.out.println("enter show id");
        Integer show_id = sc1.nextInt();
        System.out.println("enter seat id");
        Integer seat_id= sc1.nextInt();
        SeatDTO sdao = new SeatDTO();
        sdao.setSeat_id(seat_id);
        sdao.setShow_id(show_id);
        System.out.println("enter the user details");
        System.out.println("enter the user name");
        String username = sc1.next();
        System.out.println("enter the user mobile number");
        Long user_phone = sc1.nextLong();
        //BookingDAO bdao = new BookingDAO();
        BookingDTO bdto = new BookingDTO();
        bdto.setUser_name(username);
        bdto.setUser_phone(user_phone);
        BookingDAO bdao = new BookingDAO();
        bdao.checkingSeat(sdao,bdto);


    }
}
