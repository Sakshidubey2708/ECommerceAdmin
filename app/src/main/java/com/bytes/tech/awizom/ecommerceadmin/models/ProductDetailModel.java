package com.bytes.tech.awizom.ecommerceadmin.models;

public class ProductDetailModel {
    public long ProductId;
    public String ProductName ;
    public String TypeWeight ;
    public String HighlightsDesign ;
    public double MRPINR ;
    public double TotalDiscountsPer;
    public double MRPDiscountINR ;
    public double AssuredPriceINR ;
    public long BrandId ;
    public long MainCatId ;
    public long SubCatId ;
    public long TypeSubCatId ;
    public String Descriptions ;
    public String Img1 ;

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getTypeWeight() {
        return TypeWeight;
    }

    public void setTypeWeight(String typeWeight) {
        TypeWeight = typeWeight;
    }

    public String getHighlightsDesign() {
        return HighlightsDesign;
    }

    public void setHighlightsDesign(String highlightsDesign) {
        HighlightsDesign = highlightsDesign;
    }

    public double getMRPINR() {
        return MRPINR;
    }

    public void setMRPINR(double MRPINR) {
        this.MRPINR = MRPINR;
    }

    public double getTotalDiscountsPer() {
        return TotalDiscountsPer;
    }

    public void setTotalDiscountsPer(double totalDiscountsPer) {
        TotalDiscountsPer = totalDiscountsPer;
    }

    public double getMRPDiscountINR() {
        return MRPDiscountINR;
    }

    public void setMRPDiscountINR(double MRPDiscountINR) {
        this.MRPDiscountINR = MRPDiscountINR;
    }

    public double getAssuredPriceINR() {
        return AssuredPriceINR;
    }

    public void setAssuredPriceINR(double assuredPriceINR) {
        AssuredPriceINR = assuredPriceINR;
    }

    public long getBrandId() {
        return BrandId;
    }

    public void setBrandId(long brandId) {
        BrandId = brandId;
    }

    public long getMainCatId() {
        return MainCatId;
    }

    public void setMainCatId(long mainCatId) {
        MainCatId = mainCatId;
    }

    public long getSubCatId() {
        return SubCatId;
    }

    public void setSubCatId(long subCatId) {
        SubCatId = subCatId;
    }

    public long getTypeSubCatId() {
        return TypeSubCatId;
    }

    public void setTypeSubCatId(long typeSubCatId) {
        TypeSubCatId = typeSubCatId;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }
}
