package com.example.demo.dto.user;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class UpdateUserDTO {
    @NotBlank(message = "Email is mandatory")
    private String gmail;

    @NotBlank(message = "Full name is mandatory")
    private String fullname;
    private Date dob;
    private Boolean status;
    private Long is_delete;

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public Long getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Long is_delete) {
        this.is_delete = is_delete;
    }
}
