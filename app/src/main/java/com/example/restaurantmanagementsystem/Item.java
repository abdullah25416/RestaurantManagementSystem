package com.example.restaurantmanagementsystem;

public class Item {
    protected String title;
    protected String price;
    protected String description;
    protected String quantity;

    public Item(String title, String price, String description, String quantity) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}





