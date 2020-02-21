package com.bytes.tech.awizom.ecommerceadmin.models;

public class SearchModel {
    public int ProductId;
    public String ProductName ;
    public int BrandId ;
    public String BrandName;
    public String ProImg1;

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int brandId) {
        BrandId = brandId;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getProImg1() {
        return ProImg1;
    }

    public void setProImg1(String proImg1) {
        ProImg1 = proImg1;
    }
}
