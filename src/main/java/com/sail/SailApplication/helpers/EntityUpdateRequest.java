package com.sail.SailApplication.helpers;

public class EntityUpdateRequest {
    private Long id;
    private String newMail;
    private String newPassword;

    public EntityUpdateRequest(Long id, String newMail,String newPassword) {
        this.id = id;
        this.newMail = newMail;
        this.newPassword = newPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewMail() {
        return newMail;
    }

    public void setNewMail(String newMail) {
        this.newMail = newMail;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
