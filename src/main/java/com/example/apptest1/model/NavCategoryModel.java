package com.example.apptest1.model;

public class NavCategoryModel {
    String name;
    String discription;
    String discount;
    String img_url;
    String type;

    public NavCategoryModel() {
    }

    public NavCategoryModel(String discount, String discription, String img_url, String name, String type) {
        this.discount = discount;
        this.discription = discription;
        this.img_url = img_url;
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NavCategoryModel(String discount, String discription, String img_url, String name) {
        this.discount = discount;
        this.discription = discription;
        this.img_url = img_url;
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
