package com.example.testapp.model;

public class Product {
    private String id, product_name, image;
    private int price;


    public Product() {
        super();
    }

    public Product(String id, String product_name, String image, int price) {
        this.id = id;
        this.product_name = product_name;
        this.image = image;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
