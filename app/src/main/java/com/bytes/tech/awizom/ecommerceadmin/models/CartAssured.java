package com.bytes.tech.awizom.ecommerceadmin.models;

public class CartAssured {

    public long CartAssuredId;
    public long CartId;
    public long ProductId ;
    public String UserId;
    public long Quantity ;
    public double AssuredPriceINR ;


    public long getCartAssuredId() {
        return CartAssuredId;
    }

    public void setCartAssuredId(long cartAssuredId) {
        CartAssuredId = cartAssuredId;
    }

    public long getCartId() {
        return CartId;
    }

    public void setCartId(long cartId) {
        CartId = cartId;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public long getQuantity() {
        return Quantity;
    }

    public void setQuantity(long quantity) {
        Quantity = quantity;
    }

    public double getAssuredPriceINR() {
        return AssuredPriceINR;
    }

    public void setAssuredPriceINR(double assuredPriceINR) {
        AssuredPriceINR = assuredPriceINR;
    }
}
