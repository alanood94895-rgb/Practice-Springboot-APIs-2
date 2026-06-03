package com.example.traineeapp;

public class InventoryBook {
    private int bookId;
    private String title;
    private double price;
    private int stock;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public InventoryBook(int bookId, String title, double price, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.stock = stock;

    }
}
