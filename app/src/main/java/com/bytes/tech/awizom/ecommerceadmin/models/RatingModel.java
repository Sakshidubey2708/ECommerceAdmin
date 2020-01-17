package com.bytes.tech.awizom.ecommerceadmin.models;

public class RatingModel {

    public long ProductRaitingId;
    public long ProductId ;
    public float Raiting ;
    public boolean IsRaiting ;

    public long getProductRaitingId() {
        return ProductRaitingId;
    }

    public void setProductRaitingId(long productRaitingId) {
        ProductRaitingId = productRaitingId;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public float getRaiting() {
        return Raiting;
    }

    public void setRaiting(float raiting) {
        Raiting = raiting;
    }

    public boolean isRaiting() {
        return IsRaiting;
    }

    public void setRaiting(boolean raiting) {
        IsRaiting = raiting;
    }
}
