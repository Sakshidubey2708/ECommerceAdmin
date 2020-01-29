package com.bytes.tech.awizom.ecommerceadmin.models;

public class PricerequestModel {
    public long PriceRequestId ;
    public String RequestUserId ;
    public String RequestDate;
    public long ProductId ;
    public String RequestStatus ;
    public double SalePrice ;
    public double SaleDiscount ;
    public Boolean isRequest;

    public Boolean getRequest() {
        return isRequest;
    }

    public void setRequest(Boolean request) {
        isRequest = request;
    }

    public long getPriceRequestId() {
        return PriceRequestId;
    }

    public void setPriceRequestId(long priceRequestId) {
        PriceRequestId = priceRequestId;
    }

    public String getRequestUserId() {
        return RequestUserId;
    }

    public void setRequestUserId(String requestUserId) {
        RequestUserId = requestUserId;
    }

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        RequestDate = requestDate;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getRequestStatus() {
        return RequestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        RequestStatus = requestStatus;
    }

    public double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(double salePrice) {
        SalePrice = salePrice;
    }

    public double getSaleDiscount() {
        return SaleDiscount;
    }

    public void setSaleDiscount(double saleDiscount) {
        SaleDiscount = saleDiscount;
    }


}
