package com.bytes.tech.awizom.ecommerceadmin.models;

public class UserLogin {

    public String UserName ;
    public String Password ;
    public boolean RememberMe ;
 //   public  String UserID;
  //  public String SubscriberId ;
    public String FirmName;
    public String Category;

    public int Id ;
    public String UserId ;
    public String LogInDate;
    public String LogInType ;
    public String SubsciberID ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getLogInDate() {
        return LogInDate;
    }

    public void setLogInDate(String logInDate) {
        LogInDate = logInDate;
    }

    public String getLogInType() {
        return LogInType;
    }

    public void setLogInType(String logInType) {
        LogInType = logInType;
    }

    public String getSubsciberID() {
        return SubsciberID;
    }

    public void setSubsciberID(String subsciberID) {
        SubsciberID = subsciberID;
    }

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



    public String getFirmName() {
        return FirmName;
    }

    public void setFirmName(String firmName) {
        FirmName = firmName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
