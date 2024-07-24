package com.example.bookpublishingproject.model;

public class Book {

    private int b_id;
    private String b_author;
    private String b_title;
    private int b_isbn;
    private String b_type;
    private double b_price;
    private int p_id;

    public Book() {
        this.b_id = 0;
        this.b_author = "";
        this.b_title = "";
        this.b_isbn = 0;
        this.b_type = "";
        this.b_price = 0.0;
        this.p_id = 0;
    }
    public Book(int b_id,
                String b_author,
                String b_title,
                int b_isbn,
                String b_type,
                double b_price,
                int p_id) {
        this.b_id = b_id;
        this.b_author = b_author;
        this.b_title = b_title;
        this.b_isbn = b_isbn;
        this.b_type = b_type;
        this.b_price = b_price;
        this.p_id = p_id;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_author() {
        return b_author;
    }

    public void setB_author(String b_author) {
        this.b_author = b_author;
    }

    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public int getB_isbn() {
        return b_isbn;
    }

    public void setB_isbn(int b_isbn) {
        this.b_isbn = b_isbn;
    }

    public String getB_type() {
        return b_type;
    }

    public void setB_type(String b_type) {
        this.b_type = b_type;
    }

    public double getB_price() {
        return b_price;
    }

    public void setB_price(double b_price) {
        this.b_price = b_price;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "b_id=" + b_id +
                ", b_author='" + b_author + '\'' +
                ", b_title='" + b_title + '\'' +
                ", b_isbn=" + b_isbn +
                ", b_type='" + b_type + '\'' +
                ", b_price=" + b_price +
                ", p_id=" + p_id +
                '}';
    }

    public double calculate_Price_Euro() {
        return (b_price*1.45);
    }
}
