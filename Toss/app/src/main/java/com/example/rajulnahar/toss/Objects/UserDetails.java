package com.example.rajulnahar.toss.Objects;

/**
 * Created by Rajul Nahar on 06-02-2017.
 */

public class UserDetails {
    private int userId;
    private String userGuid;
    private String emailId;
    private String loginType;
    private boolean isUserActive;

    public int getUserId() {
        return userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getLoginType() {
        return loginType;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public void setUserActive(boolean userActive) {
        isUserActive = userActive;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isUserActive() {
        return isUserActive;
    }
}
