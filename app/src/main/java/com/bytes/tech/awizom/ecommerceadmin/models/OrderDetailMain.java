package com.bytes.tech.awizom.ecommerceadmin.models;

public class OrderDetailMain {

    public long OrderDetailId ;
    public long OrderId ;
    public long ProductId ;
    public double UnitPrice ;
    public long Quantity ;
    public double TotalAmount ;
    public double Discount ;
    public double DiscountAmount ;
    public double SGST ;
    public double CGST ;
    public double TotalTaxAmount ;
    public double TaxableAmount ;

    public long getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        OrderDetailId = orderDetailId;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public long getQuantity() {
        return Quantity;
    }

    public void setQuantity(long quantity) {
        Quantity = quantity;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        DiscountAmount = discountAmount;
    }

    public double getSGST() {
        return SGST;
    }

    public void setSGST(double SGST) {
        this.SGST = SGST;
    }

    public double getCGST() {
        return CGST;
    }

    public void setCGST(double CGST) {
        this.CGST = CGST;
    }

    public double getTotalTaxAmount() {
        return TotalTaxAmount;
    }

    public void setTotalTaxAmount(double totalTaxAmount) {
        TotalTaxAmount = totalTaxAmount;
    }

    public double getTaxableAmount() {
        return TaxableAmount;
    }

    public void setTaxableAmount(double taxableAmount) {
        TaxableAmount = taxableAmount;
    }
}
