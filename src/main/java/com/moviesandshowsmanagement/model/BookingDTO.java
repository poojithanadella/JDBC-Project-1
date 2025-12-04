package com.moviesandshowsmanagement.model;

public class BookingDTO {
private String user_name;
private Long user_phone;
private Integer show_id1;
private Double total_amount;
private String status;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Long getUser_phone() {

        return user_phone;
    }

    public void setUser_phone(Long user_phone) {
        this.user_phone = user_phone;
    }

    public Integer getShow_id1() {

        return show_id1;
    }

    public void setShow_id1(Integer show_id1) {
        this.show_id1 = show_id1;
    }

    public Double getTotal_amount() {

        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {

        this.total_amount = total_amount;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
