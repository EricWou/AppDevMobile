package com.example.billingproject.model;

public class Billing {

    private int client_ID;
    private String client_Name;
    private String product_Name;
    private double prd_Price;
    private int prd_Qty;
    public static final double Fed_Tax = 0.075;
    public static final double Prv_Tax = 0.06;

    public Billing() {
        this.client_ID = 0;
        this.client_Name = "";
        this.product_Name = "";
        this.prd_Price = 0.0;
        this.prd_Qty = 0;
    }

    public Billing(int client_ID, String client_Name, String product_Name, double prd_Price, int prd_Qty) {
        this.client_ID = client_ID;
        this.client_Name = client_Name;
        this.product_Name = product_Name;
        this.prd_Price = prd_Price;
        this.prd_Qty = prd_Qty;
    }

    public int getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(int client_ID) {
        this.client_ID = client_ID;
    }

    public String getClient_Name() {
        return client_Name;
    }

    public void setClient_Name(String client_Name) {
        this.client_Name = client_Name;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public double getPrd_Price() {
        return prd_Price;
    }

    public void setPrd_Price(double prd_Price) {
        this.prd_Price = prd_Price;
    }

    public int getPrd_Qty() {
        return prd_Qty;
    }

    public void setPrd_Qty(int prd_Qty) {
        this.prd_Qty = prd_Qty;
    }

    public double calculateBilling() {
        return (prd_Price*prd_Qty) + (prd_Price*prd_Qty)*Fed_Tax + (prd_Price*prd_Qty)*Prv_Tax;

    }

}
