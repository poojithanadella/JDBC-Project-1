package com.moviesandshowsmanagement.main;

import com.moviesandshowsmanagement.services.BookingServiceImple;
import com.moviesandshowsmanagement.services.PaymentServiceImple;
import com.moviesandshowsmanagement.services.movieServicesImple;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Movies And Shows Management");
        System.out.println("enter 1 if your a Admin");
        System.out.println("enter 2 if your an User");
        System.out.println("enter 0 for exit");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
         movieServicesImple obj1=new movieServicesImple();
         BookingServiceImple obj2=new BookingServiceImple();
         PaymentServiceImple obj3=new PaymentServiceImple();
        switch(choice){
            case 1:{
                System.out.println("Enter 1 for Add movie");
                System.out.println("Enter 2 for create show");
                int choice1=sc.nextInt();
                switch(choice1){
                    case 1:{
                        obj1.addMovie();
                        break;
                    }
                    case 2:{
                        obj1.createShow();
                        break;
                    }
                }
                break;
            }
            case 2:{
                System.out.println("Enter 1 for view seat Availability");
                System.out.println("Enter 2 for booking show");
                System.out.println("Enter 3 if you already booked the seat but now you want do payment");
                System.out.println("Enter 4 for if you want to cancel the booking");
                int choice1=sc.nextInt();
                switch(choice1){
                    case 1:{
                        obj2.viewSeatAvailability();
                         break;
                    }
                    case 2:{
                        obj2.bookingTickets();
                        System.out.println("Enter 1 if you want to do payment");
                        System.out.println("Enter 0 if you dont want payment now");
                        int choice2=sc.nextInt();
                        switch(choice2){
                            case 1:{
                                obj3.paymentConfirmation();
                                System.out.println("Enter 1 if you want to cancel the tickets");
                                System.out.println("Enter 0 if you dont want to cancel");
                                int choice3=sc.nextInt();
                                switch(choice3){
                                    case 1:{
                                        obj3.cancelBooking();
                                        break;
                                    }
                                    case 0:{
                                        System.out.println("Thank you for using our services");
                                        System.exit(0);
                                    }
                                }
                                break;
                            }
                            case 0:{
                                System.exit(0);
                                break;
                            }
                        }

                        break;
                    }
                    case 3:{
                        obj3.paymentConfirmation();
                        break;

                    }
                    case 4:{
                        obj3.cancelBooking();
                        break;
                    }

                }
            }
        }



    }
}
