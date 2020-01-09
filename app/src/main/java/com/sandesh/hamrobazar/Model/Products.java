package com.sandesh.hamrobazar.Model;

public class Products {
    private String productName;
    private String productPrice;
    private String productType;
    private String image;

    public Products(String productName, String productPrice, String productType, String image) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
