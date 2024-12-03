package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class UpdateUserDTO {

    @NotBlank(message = "Gmail is mandatory")
    private String gmail;

    @NotBlank(message = "Full name is mandatory")
    private String full_name;

    private Date dob;

    private Boolean status;

    private Long is_deleted;

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

    public void setIs_deleted(Long is_delete) {
        this.is_deleted = is_delete;
    }
}
