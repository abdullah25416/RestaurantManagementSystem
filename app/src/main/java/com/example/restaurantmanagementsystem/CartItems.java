package com.example.restaurantmanagementsystem;

public class CartItems {
    protected String Cname;
    protected String Cprice;
    protected String Cquantity;

    public CartItems(String cname, String cprice, String cquantity) {
        Cname = cname;
        Cprice = cprice;
        Cquantity = cquantity;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCprice() {
        return Cprice;
    }

    public void setCprice(String cprice) {
        Cprice = cprice;
    }

    public String getCquantity() {
        return Cquantity;
    }

    public void setCquantity(String cquantity) {
        Cquantity = cquantity;
    }
}

