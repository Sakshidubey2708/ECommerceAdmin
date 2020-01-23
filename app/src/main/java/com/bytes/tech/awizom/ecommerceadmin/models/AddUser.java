package com.bytes.tech.awizom.ecommerceadmin.models;

public class AddUser {

    public long AddUserId ;
    public String FirstName ;
    public String MiddleName ;
    public String LastName;
    public String UserName;
    public String pass ;
    public String Business ;
    public long ContactNo ;
    public String EmailId ;
    public long Pincode ;
    public String Address;
    public long StateID ;
    public long CityID ;
    public String UID ;
    public String SubscriberID ;
    public boolean Active;


    public long getAddUserId() {
        return AddUserId;
    }

    public void setAddUserId(long addUserId) {
        AddUserId = addUserId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBusiness() {
        return Business;
    }

    public void setBusiness(String business) {
        Business = business;
    }

    public long getContactNo() {
        return ContactNo;
    }

    public void setContactNo(long contactNo) {
        ContactNo = contactNo;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public long getPincode() {
        return Pincode;
    }

    public void setPincode(long pincode) {
        Pincode = pincode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public long getStateID() {
        return StateID;
    }

    public void setStateID(long stateID) {
        StateID = stateID;
    }

    public long getCityID() {
        return CityID;
    }

    public void setCityID(long cityID) {
        CityID = cityID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getSubscriberID() {
        return SubscriberID;
    }

    public void setSubscriberID(String subscriberID) {
        SubscriberID = subscriberID;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }
}
