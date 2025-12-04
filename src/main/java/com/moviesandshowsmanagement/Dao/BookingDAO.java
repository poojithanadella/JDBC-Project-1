package com.moviesandshowsmanagement.Dao;

import com.moviesandshowsmanagement.model.BookingDTO;
import com.moviesandshowsmanagement.model.SeatDTO;
import com.moviesandshowsmanagement.util.DBConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAO {

    public void checkingSeat(SeatDTO sdao, BookingDTO bdto) {
        DataSource ds = DBConfig.getConnection();
        Connection con = null;
        int bookingId = 0;
        double amount = 0.0;

        String checksql = "SELECT status, price FROM show_seats WHERE show_id = ? AND seat_id = ?";
        String updateSql = "UPDATE show_seats SET status = 'booked' WHERE show_id = ? AND seat_id = ?";
        String bookingSql = "INSERT INTO bookings VALUES (0,?,?,?,?,?,NOW())";
        String bookingItemSql = "INSERT INTO booking_items VALUES (0,?,?,?)";

        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement(checksql);
            pst.setInt(1, sdao.getShow_id());
            pst.setInt(2, sdao.getSeat_id());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (!"active".equals(rs.getString("status"))) {
                    throw new RuntimeException("Seat already booked");
                }
                amount = rs.getDouble("price");
            }

            pst = con.prepareStatement(updateSql);
            pst.setInt(1, sdao.getShow_id());
            pst.setInt(2, sdao.getSeat_id());
            pst.executeUpdate();
            pst = con.prepareStatement(bookingSql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, bdto.getUser_name());
            pst.setLong(2, bdto.getUser_phone());
            pst.setInt(3, sdao.getShow_id());
            pst.setDouble(4, amount);
            pst.setString(5, "locked");
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }
            pst = con.prepareStatement(bookingItemSql);
            pst.setInt(1, bookingId);
            pst.setInt(2, sdao.getShow_id());
            pst.setDouble(3, amount);
            pst.executeUpdate();
            con.commit();
            System.out.println("successfully tickets are booked");
            System.out.println(bookingId +" is the booking id");

        }
        catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                throw  new RuntimeException("Rollback failed");
            }
            throw new RuntimeException("booking failed");

        }
        finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    }

