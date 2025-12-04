package com.moviesandshowsmanagement.Dao;
import com.moviesandshowsmanagement.model.PaymentDTO;
import com.moviesandshowsmanagement.util.DBConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentDAO {
    public void paymentConfirmation(PaymentDTO pdto) {
        Connection con=null;
        Double price=0.0;
        String checkSql="select price from booking_items where booking_id=?";
        String insertSQL = "INSERT INTO payments VALUES (0, ?, ?,?,'SUCCESS', ?, NOW())";
        String updateSQL = "UPDATE bookings SET status = 'CONFIRMED' WHERE id = ?";

        try {
            DataSource ds = DBConfig.getConnection();
            con = ds.getConnection();
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement(checkSql);
            pst.setInt(1, pdto.getBookingId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                price=rs.getDouble("price");
                System.out.println("booking id is there, continue with payment");
            }
            else{
                throw new RuntimeException("booking id not found");
            }
            pst = con.prepareStatement(insertSQL);
            pst.setInt(1,pdto.getBookingId());
            pst.setDouble(2,price);
            pst.setString(3, pdto.getMethod());
            System.out.println(pdto.getMethod());
            pst.setString(4,"TXN"+System.currentTimeMillis());
            int rows= pst.executeUpdate();
            System.out.println(rows+" rows inserted");

            pst = con.prepareStatement(updateSQL);
            pst.setInt(1, pdto.getBookingId());
            int rows1= pst.executeUpdate();
            con.commit();
            System.out.println(rows1+" rows updated");
            System.out.println("Payment Confirmation successfully");
        }
        catch (SQLException e) {
            try {
                if(con!=null)
                    con.rollback();
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Payment Confirmation failed");
        }
        finally {
            if(con!=null) {
                try {
                    con.setAutoCommit(true);
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void cancelBooking(PaymentDTO pdto){
        Connection con = null;
        String checkSql="select ss.show_time from moviesandshowsmanagement.shows ss join moviesandshowsmanagement.bookings bb on ss.id=bb.show_id1 where bb.id=?";
        String UpdateBooking="update moviesandshowsmanagement.bookings set status = 'CANCELLED' where id = ? ";
        String sql="select ss.show_id,ss.seat_id from moviesandshowsmanagement.show_seats ss inner join moviesandshowsmanagement.booking_items bb on ss.id=bb.show_seat_id where bb.booking_id=?";
        String updateSeats="update moviesandshowsmanagement.show_seats set status = 'active' where show_id=? and seat_id=? ";
        String updateRefund="update moviesandshowsmanagement.payments set status = 'Refund pending' where booking_id = ? ";
        try {
            DataSource ds = DBConfig.getConnection();
            con=ds.getConnection();
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement(checkSql);
            pst.setInt(1, pdto.getBookingId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
              String time=rs.getString("ss.show_time");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime storedTime = LocalDateTime.parse(time, formatter);
                LocalDateTime twoHoursAfter = storedTime.plusHours(2);
                LocalDateTime now = LocalDateTime.now();
            if(!(now.isBefore(twoHoursAfter))) {
               throw new RuntimeException("its already late cannot cancel booking");
            }
            else{
                pst = con.prepareStatement(UpdateBooking);
                pst.setInt(1, pdto.getBookingId());
                pst.executeUpdate();
                pst=con.prepareStatement(sql);
                pst.setInt(1, pdto.getBookingId());
                ResultSet rs1=pst.executeQuery();
                if(rs1.next()) {
                    int seat_id = rs1.getInt("ss.seat_id");
                    int show_id = rs1.getInt("ss.show_id");
                    pst = con.prepareStatement(updateRefund);
                    pst.setInt(1, pdto.getBookingId());
                    pst.executeUpdate();
                    pst = con.prepareStatement(updateSeats);
                    pst.setInt(1, rs1.getInt("ss.show_id"));
                    pst.setInt(2, rs1.getInt("ss.seat_id"));
                    pst.executeUpdate();
                    con.commit();
                    System.out.println("Refund initiated successfully");
                }
            }
            }
        }
        catch (SQLException e) {
            try {
                con.rollback();
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);

        }
        finally {
            if(con!=null) {
                try {
                    con.setAutoCommit(true);
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
