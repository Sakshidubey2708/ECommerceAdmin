package com.bytes.tech.awizom.ecommerceadmin.models;

public class CartModel {

    public long CartId ;

    public String CreatedBy;


    public long ProductId ;
    public String Img1 ;
    public String ProductName;
    public String Descriptions ;

    public double MRPINR;

    public long BrandId ;
    public String BrandName ;

    public String UserId ;

    public long OrderId ;
    public long OrderDetailId ;
    public String OrdersNo ;

    public String getOrderNo() {
        return OrdersNo;
    }

    public void setOrderNo(String orderNo) {
        OrdersNo = orderNo;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public long getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        OrderDetailId = orderDetailId;
    }

    public long getCartId() {
        return CartId;
    }

    public void setCartId(long cartId) {
        CartId = cartId;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }

    public double getMRPINR() {
        return MRPINR;
    }

    public void setMRPINR(double MRPINR) {
        this.MRPINR = MRPINR;
    }

    public long getBrandId() {
        return BrandId;
    }

    public void setBrandId(long brandId) {
        BrandId = brandId;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }
}
