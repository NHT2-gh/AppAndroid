package com.example.testapp.model;

public class ProductSaleRequest {
    private String product_id;
    private String product_name;
    private long total_quantity;
    private long total_sold;
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public long getTotal_sale() {
        return total_sold;
    }
    public void setTotal_sale(int total_sold) {
        this.total_sold = total_sold;
    }
    public long getTotal_quantity() {
        return total_quantity;
    }
    public void setTotal_quantity(long total_quantity) {
        this.total_quantity = total_quantity;
    }
    public long getTotal_sold() {
        return total_sold;
    }
    public void setTotal_sold(long total_sold) {
        this.total_sold = total_sold;
    }
    public ProductSaleRequest(String product_id, String product_name, long total_quantity, long total_sold) {
        super();
        this.product_id = product_id;
        this.product_name = product_name;
        this.total_quantity = total_quantity;
        this.total_sold = total_sold;
    }
    public ProductSaleRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

}
