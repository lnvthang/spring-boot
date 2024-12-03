package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class CreateUserDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Gmail is mandatory")
    private String gmail;

    @NotBlank(message = "Full name is mandatory")
    private String full_name;

    private Date dob;

    private Boolean status;

    private Long is_deleted;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Long is_deleted) {
        this.is_deleted = is_deleted;
    }
}
