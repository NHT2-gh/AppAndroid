package com.example.testapp.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long order_id;
    private Integer total_price, total_quantity, status, product_price;
    private String update_at;
    private Long customer_id;
    private List<OrderDetail> order_detail;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public Order(Long order_id, Integer total_price, Integer total_quantity, Integer status, Integer product_price, String update_at, Long customer_id, List<OrderDetail> order_detail) {
        this.order_id = order_id;
        this.total_price = total_price;
        this.total_quantity = total_quantity;
        this.status = status;
        this.product_price = product_price;
        this.update_at = update_at;
        this.customer_id = customer_id;
        this.order_detail = order_detail;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    public Order() {
        super();
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public Integer getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(Integer total_quantity) {
        this.total_quantity = total_quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public List<OrderDetail> getOrderDetailList() {
        return order_detail;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.order_detail = orderDetailList;
    }
}
