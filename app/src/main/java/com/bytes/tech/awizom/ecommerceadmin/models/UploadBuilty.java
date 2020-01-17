package com.bytes.tech.awizom.ecommerceadmin.models;

import java.util.Date;

public class UploadBuilty {
    public long UploadBuiltyID ;

    public String Image ;

    public long ProductId ;

    public long CreatedBy ;
    public Date CreatedDate ;

    public long getUploadBuiltyID() {
        return UploadBuiltyID;
    }

    public void setUploadBuiltyID(long uploadBuiltyID) {
        UploadBuiltyID = uploadBuiltyID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public long getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(long createdBy) {
        CreatedBy = createdBy;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }
}
