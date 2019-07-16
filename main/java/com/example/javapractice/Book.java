package com.example.javapractice;

import java.io.Serializable;


public class Book implements Serializable {

    private static final long serialVersionUID = 7701355780902549134L;

    String title;
    String author;
    String review;
    String price;

    public Book(String title, String author, String review, String price){
        this.title = title;
        this.author = author;
        this.review = review;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getPrice() {
        return price;
    }
}
