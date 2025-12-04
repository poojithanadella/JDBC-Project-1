package com.moviesandshowsmanagement.Dao;

import com.moviesandshowsmanagement.model.SeatDTO;
import com.moviesandshowsmanagement.util.DBConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDAO {
    public void viewSeatAvailability(SeatDTO sdto){
        try {
            DataSource ds = DBConfig.getConnection();
            Connection con = ds.getConnection();
            String sql="SELECT s.id, s.row_label, s.seat_no, ss.status, ss.price FROM show_seats ss JOIN seats s ON ss.seat_id = s.id WHERE ss.show_id = ?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,sdto.getShow_id());
            ResultSet rs=pst.executeQuery();
            if(!rs.next()){
                throw new RuntimeException("show id not found");
            }
            do{

                      System.out.println( rs.getString("row_label")+" | "+ rs.getInt("seat_no")+" | "+rs.getString("status")+" | "+rs.getDouble("price"));
            }while(rs.next());
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }



}
