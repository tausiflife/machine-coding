package org.lld.notifyme.models;

public class User {
    private String emailId;
    private String phoneNumber;

    public User(String emailId, String phoneNumber) {
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
