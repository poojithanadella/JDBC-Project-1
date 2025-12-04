package com.moviesandshowsmanagement.services;

import com.moviesandshowsmanagement.Dao.BookingDAO;
import com.moviesandshowsmanagement.Dao.PaymentDAO;
import com.moviesandshowsmanagement.model.PaymentDTO;

import java.util.Scanner;

public class PaymentServiceImple implements PaymentService{
    Scanner sc=new Scanner(System.in);
    public void paymentConfirmation(){
        System.out.print("Enter 1 for UPI ");
        System.out.print("Enter 2 for CARD");
        System.out.print("Enter 3 for CASH ");
        System.out.print("Enter Payment method : ");
       Integer paymentMethod=sc.nextInt();
       String method="";
        switch(paymentMethod){
            case 1:{
                method="UPI";
                System.out.println("enter the booking id");
                Integer bookingId1=sc.nextInt();
                PaymentDTO pdto=new PaymentDTO();
                pdto.setBookingId(bookingId1);
                pdto.setMethod(method);
                PaymentDAO pdao=new PaymentDAO();
                pdao.paymentConfirmation(pdto);
                break;
            }
            case 2:{
                method="CARD";
                System.out.println("enter the booking id");
                Integer bookingId1=sc.nextInt();
                PaymentDTO pdto=new PaymentDTO();
                pdto.setMethod(method);
                PaymentDAO pdao=new PaymentDAO();
                pdao.paymentConfirmation(pdto);
                break;
            }
            case 3:{
                method="CASH";
                System.out.println("enter the booking id");
                Integer bookingId1=sc.nextInt();
                PaymentDTO pdto=new PaymentDTO();
                pdto.setMethod(method);
                PaymentDAO pdao=new PaymentDAO();
                pdao.paymentConfirmation(pdto);
                break;
            }
        }


    }
    public void cancelBooking(){
        System.out.println("enter the booking id");
        Integer bookingId=sc.nextInt();
        PaymentDTO pdto=new PaymentDTO();
        pdto.setBookingId(bookingId);
        PaymentDAO pdao=new PaymentDAO();
        pdao.cancelBooking(pdto);
    }
}
