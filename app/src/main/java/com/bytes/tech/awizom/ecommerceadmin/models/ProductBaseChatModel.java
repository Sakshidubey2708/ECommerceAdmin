package com.bytes.tech.awizom.ecommerceadmin.models;

public class ProductBaseChatModel {

    public int ProductBaseChatId ;
    public int AddUserId;
    public int RetailerID;
    public int ProductId ;
    public String CreateOn;
    public String ChatContain;

    public int getProductBaseChatId() {
        return ProductBaseChatId;
    }

    public void setProductBaseChatId(int productBaseChatId) {
        ProductBaseChatId = productBaseChatId;
    }

    public int getAddUserId() {
        return AddUserId;
    }

    public void setAddUserId(int addUserId) {
        AddUserId = addUserId;
    }

    public int getRetailerID() {
        return RetailerID;
    }

    public void setRetailerID(int retailerID) {
        RetailerID = retailerID;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getCreateOn() {
        return CreateOn;
    }

    public void setCreateOn(String createOn) {
        CreateOn = createOn;
    }

    public String getChatContain() {
        return ChatContain;
    }

    public void setChatContain(String chatContain) {
        ChatContain = chatContain;
    }

    public String getMsgBy() {
        return MsgBy;
    }

    public void setMsgBy(String msgBy) {
        MsgBy = msgBy;
    }

    public String MsgBy ;
}
