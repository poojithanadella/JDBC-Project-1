package com.moviesandshowsmanagement.Dao;

import com.moviesandshowsmanagement.model.MovieDTO;
import com.moviesandshowsmanagement.exceptions.MovieAlreadyExistExceptions;
import com.moviesandshowsmanagement.util.DBConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAO {
    public void addMovie(MovieDTO mdto) {

        try {
            DataSource ds = DBConfig.getConnection();
            Connection con=ds.getConnection();
            String checkingSql="select * from moviesandshowsmanagement.movies where title=? and language=?";
            PreparedStatement ps=con.prepareStatement(checkingSql);
            ps.setString(1,mdto.getTitle());
            ps.setString(2,mdto.getLanguage());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                throw new MovieAlreadyExistExceptions("movie already exists");
            }
            else {
                String insertSql = "insert into moviesandshowsmanagement.movies values(0,?,?,?,?,?,?)";
                ps = con.prepareStatement(insertSql);
                ps.setString(1, mdto.getTitle());
                ps.setString(2, mdto.getLanguage());
                ps.setInt(3, mdto.getDuration_min());
                ps.setString(4, mdto.getCertification());
                ps.setString(5, mdto.getStatus());
                ps.setString(6, mdto.getCreated_at());
                int rowsAffected = ps.executeUpdate();
                System.out.println(rowsAffected + "row(s) affected: ");

            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
