package com.example.testapp.model;

public class Products {
    private String id, name;
    private int price, img;


    public Products() {
    }

    public Products(String id, String name, int price, int img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
