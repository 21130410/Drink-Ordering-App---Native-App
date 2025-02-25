package com.example.apptest1.model;

public class PopularModel {
    String name;
    String description;
    String rating;
    String discount;
    String type;
    String img_url;

    public PopularModel() {
    }

    public PopularModel(String description, String discount, String img_url, String name, String rating, String type) {
        this.description = description;
        this.discount = discount;
        this.img_url = img_url;
        this.name = name;
        this.rating = rating;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

