package com.bytes.tech.awizom.ecommerceadmin.models;

public class StockMain {
    public long ProductId;
    public String ProductName;
    public String Descriptions;
    public String HighlightsDesign;
    public String CreatedBy;
    public long StockQuantity;
    public long StockInQuantity;
    public long StockOutQuantity;
    public int PricingProductId;
    public  int StockMainId;
    public String ProImg1;

    public int getPricingProductId() {
        return PricingProductId;
    }

    public void setPricingProductId(int pricingProductId) {
        PricingProductId = pricingProductId;
    }

    public int getStockMainId() {
        return StockMainId;
    }

    public void setStockMainId(int stockMainId) {
        StockMainId = stockMainId;
    }

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

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }

    public String getHighlightsDesign() {
        return HighlightsDesign;
    }

    public void setHighlightsDesign(String highlightsDesign) {
        HighlightsDesign = highlightsDesign;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public long getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(long stockQuantity) {
        StockQuantity = stockQuantity;
    }

    public long getStockInQuantity() {
        return StockInQuantity;
    }

    public void setStockInQuantity(long stockInQuantity) {
        StockInQuantity = stockInQuantity;
    }

    public long getStockOutQuantity() {
        return StockOutQuantity;
    }

    public void setStockOutQuantity(long stockOutQuantity) {
        StockOutQuantity = stockOutQuantity;
    }

    public String getProImg1() {
        return ProImg1;
    }

    public void setProImg1(String proImg1) {
        ProImg1 = proImg1;
    }
}
