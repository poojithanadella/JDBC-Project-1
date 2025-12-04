package com.moviesandshowsmanagement.Dao;

import com.moviesandshowsmanagement.model.ShowDTO;
import com.moviesandshowsmanagement.exceptions.AlreadyMovieIsRunningException;
import com.moviesandshowsmanagement.util.DBConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDAO {
    public void createShow(ShowDTO shdto) {
        String checkingSql="SELECT * FROM shows WHERE auditorium_id=? AND ((show_time<=? AND end_time>=?) OR (show_time<=? AND end_time>=?)) ";
        String insertSql="insert into moviesandshowsmanagement.shows values(0,?,?,?,?,'scheduled')";
        try {
            DataSource ds = DBConfig.getConnection();
            Connection con=ds.getConnection();
            PreparedStatement pst=con.prepareStatement(checkingSql);
            pst.setInt(1,shdto.getAuditorium_id());
            pst.setString(2, shdto.getShow_time());
            pst.setString(3,shdto.getShow_time());
            pst.setString(4,shdto.getEnd_time());
//            pst.setString(4,shdto.getShow_time());
            pst.setString(5,shdto.getEnd_time());
//            pst.setString(6,shdto.getShow_time());
//            pst.setString(7,shdto.getEnd_time());
             ResultSet rs =pst.executeQuery();
             if(rs.next()){
                 throw new AlreadyMovieIsRunningException("show already running");
             }
             else{
                 pst=con.prepareStatement(insertSql);
                 pst.setInt(1,shdto.getMovie_id());
                 pst.setInt(2,shdto.getAuditorium_id());
                 pst.setString(3,shdto.getShow_time());
                 pst.setString(4,shdto.getEnd_time());
                 int rowsAffected=pst.executeUpdate();
                 System.out.println("rows affected="+rowsAffected);

             }




        }



        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
