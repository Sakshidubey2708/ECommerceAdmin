package com.bytes.tech.awizom.ecommerceadmin.models;


public class OrderMainModel {

    public long OrderId ;
    public String OrderNo ;
    public String UserId ;
    public String DeliveryAddress ;
    public String Status;
    public double TotalAmount ;
    public double DeliveryCharge;
    public double AnyOtherCharge;


    public String PlaceOrder ;
    public String OrderAccept ;
    public String UnderProccess ;
    public String OrderDispatch ;


    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDeliveryAddress() {
        return DeliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        DeliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public double getDeliveryCharge() {
        return DeliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        DeliveryCharge = deliveryCharge;
    }

    public double getAnyOtherCharge() {
        return AnyOtherCharge;
    }

    public void setAnyOtherCharge(double anyOtherCharge) {
        AnyOtherCharge = anyOtherCharge;
    }

    public String getPlaceOrder() {
        return PlaceOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        PlaceOrder = placeOrder;
    }

    public String getOrderAccept() {
        return OrderAccept;
    }

    public void setOrderAccept(String orderAccept) {
        OrderAccept = orderAccept;
    }

    public String getUnderProccess() {
        return UnderProccess;
    }

    public void setUnderProccess(String underProccess) {
        UnderProccess = underProccess;
    }

    public String getOrderDispatch() {
        return OrderDispatch;
    }

    public void setOrderDispatch(String orderDispatch) {
        OrderDispatch = orderDispatch;
    }
}
