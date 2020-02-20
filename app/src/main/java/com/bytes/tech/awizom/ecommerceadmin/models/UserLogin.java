package com.bytes.tech.awizom.ecommerceadmin.models;

public class UserLogin {

    public String UserName ;
    public String Password ;
    public boolean RememberMe ;
    public  String UserID;
    public String SubscriberId ;
    public String FirmName;
    public String Category;
    public int Id ;
    public String LogInDate;
    public String LogInType ;

    public String Business;
    public int ContactNo ;
    public String logedInUserName ;

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

    public String getSubscriberId() {
        return SubscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        SubscriberId = subscriberId;
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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getBusiness() {
        return Business;
    }

    public void setBusiness(String business) {
        Business = business;
    }

    public int getContactNo() {
        return ContactNo;
    }

    public void setContactNo(int contactNo) {
        ContactNo = contactNo;
    }

    public String getLogedInUserName() {
        return logedInUserName;
    }

    public void setLogedInUserName(String logedInUserName) {
        this.logedInUserName = logedInUserName;
    }
}
