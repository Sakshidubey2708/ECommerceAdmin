package com.bytes.tech.awizom.ecommerceadmin.models;

public class ProductModel {

    public long ProductId ;
    public String ProductName ;
    public long UnitId ;
    public String TypeWeight ;
    public String HighlightsDesign ;
    public float MRPINR ;
    public float TotalDiscountsPer ;
    public float MRPDiscountINR ;
    public float AssuredPriceINR ;
    public long BrandId ;
    public long MainCatId ;
    public long SubCatId ;
    public long TypeSubCatId ;
    public String Descriptions ;
    public String ProImg1 ;
    public String ProImg2 ;
    public String ProImg3 ;
    public String ProImg4 ;
    public float Raiting ;
    public Boolean IsRaiting;

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

    public long getUnitId() {
        return UnitId;
    }

    public void setUnitId(long unitId) {
        UnitId = unitId;
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

    public float getMRPINR() {
        return MRPINR;
    }

    public void setMRPINR(float MRPINR) {
        this.MRPINR = MRPINR;
    }

    public float getTotalDiscountsPer() {
        return TotalDiscountsPer;
    }

    public void setTotalDiscountsPer(float totalDiscountsPer) {
        TotalDiscountsPer = totalDiscountsPer;
    }

    public float getMRPDiscountINR() {
        return MRPDiscountINR;
    }

    public void setMRPDiscountINR(float MRPDiscountINR) {
        this.MRPDiscountINR = MRPDiscountINR;
    }

    public float getAssuredPriceINR() {
        return AssuredPriceINR;
    }

    public void setAssuredPriceINR(float assuredPriceINR) {
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

    public String getProImg1() {
        return ProImg1;
    }

    public void setProImg1(String proImg1) {
        ProImg1 = proImg1;
    }

    public String getProImg2() {
        return ProImg2;
    }

    public void setProImg2(String proImg2) {
        ProImg2 = proImg2;
    }

    public String getProImg3() {
        return ProImg3;
    }

    public void setProImg3(String proImg3) {
        ProImg3 = proImg3;
    }

    public String getProImg4() {
        return ProImg4;
    }

    public void setProImg4(String proImg4) {
        ProImg4 = proImg4;
    }

    public float getRaiting() {
        return Raiting;
    }

    public void setRaiting(Boolean raiting) {
        IsRaiting = raiting;
    }

    public void setRaiting(float raiting) {
        Raiting = raiting;
    }
}
