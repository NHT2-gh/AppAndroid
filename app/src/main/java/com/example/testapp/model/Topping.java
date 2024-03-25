package com.example.testapp.model;

public class Topping {
    private String id, created_by, update_by;
    private String toppingName, toppingImg;
    private Integer status, toppingPrice;
//    private LocalDateTime created_at, update_at;

    public Topping() {
        super();
    }

    public Topping(String id, String created_by, String update_by, String toppingName, Integer toppingPrice, Integer status, String toppingImg) {
        this.id = id;
        this.created_by = created_by;
        this.update_by = update_by;
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
        this.toppingImg = toppingImg;
        this.status = status;
//        this.created_at = created_at;
//        this.update_at = update_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getToppingImg() {
        return toppingImg;
    }

    public Integer getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(Integer toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    public void setToppingImg(String toppingImg) {
        this.toppingImg = toppingImg;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isChecked() {
        return true;
    }


//    public LocalDateTime getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(LocalDateTime created_at) {
//        this.created_at = created_at;
//    }
//
//    public LocalDateTime getUpdate_at() {
//        return update_at;
//    }
//
//    public void setUpdate_at(LocalDateTime update_at) {
//        this.update_at = update_at;
//    }
}
