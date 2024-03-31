package com.example.testapp.model;

public class OrderDetail {
    private String product_id, product_size, product_name, product_img;
    private Long product_price;
    private Integer product_quantity;

    public OrderDetail() {
        super();
    }

    public OrderDetail(String product_id, String product_size, String product_name, String product_img, Long product_price, Integer product_quantity) {
        this.product_id = product_id;
        this.product_size = product_size;
        this.product_name = product_name;
        this.product_img = product_img;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_size() {
        return product_size;
    }

    public Long getProduct_price() {
        return product_price;
    }

    public Integer getProduct_quantity() {
        return product_quantity;
    }
}
