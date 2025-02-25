package com.example.apptest1.model;

import java.io.Serializable;

public class MyCartModel implements Serializable {
    String productName;
    String productPrice;
    String currentDate;
    String currentTime;
    String totalQuantity;
    int totalPrice;
    String documentID;

    public MyCartModel() {
    }

    public MyCartModel(String currentDate, String currentTime, String documentID, String productName, String productPrice, int totalPrice, String totalQuantity) {
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.documentID = documentID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

    public MyCartModel(String currentDate, String currentTime, String productName, String productPrice, int totalPrice, String totalQuantity) {
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }


    public String getCurrentDate() {
        return currentDate;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
