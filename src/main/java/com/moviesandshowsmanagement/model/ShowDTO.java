package com.moviesandshowsmanagement.model;

import java.sql.Timestamp;

public class ShowDTO {
        private Integer id;
        private Integer movie_id;
        private Integer auditorium_id;
        private String show_time;
        private String end_time;
        private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getAuditorium_id() {
        return auditorium_id;
    }

    public void setAuditorium_id(Integer auditorium_id) {
        this.auditorium_id = auditorium_id;
    }

    public String getShow_time() {
        return show_time;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
