package com.bytes.tech.awizom.ecommerceadmin.models;

public class UserLogin {

    public String UserName ;
    public String Password ;
    public boolean RememberMe ;
    public  String UserID;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isRememberMe() {
        return RememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        RememberMe = rememberMe;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
