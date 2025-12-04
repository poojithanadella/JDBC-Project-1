package com.moviesandshowsmanagement.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class DBConfig {
    private static final String dbUrl="jdbc:mysql://localhost:3306/moviesandshowsmanagement";
    private static final String username="root";
    private static final String password="Root@123";
    private static HikariDataSource ds=null;
    static{
        Connection con=null;
        HikariConfig config=new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);//Maximum Number of Connection in the pool..
        config.setMinimumIdle(5);//Minimum number of IDLE Connections..
        config.setConnectionTimeout(3000);//Connection timeout in milliseconds..
        config.setIdleTimeout(60000);//IDLE timeout in milliseconds..
        ds=new HikariDataSource(config);//Connect to db and create conn pool..
    }
    public static DataSource getConnection(){

        return ds;
    }
}
