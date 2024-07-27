package com.android.product.seller;

public class ProductDetails {
    private String productName;
    private String category;
    private String price;
    private String quantity;
    private String description;
    private String pic;

    public ProductDetails(String productName, String category, String price, String quantity, String description, String pic) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.pic = pic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
