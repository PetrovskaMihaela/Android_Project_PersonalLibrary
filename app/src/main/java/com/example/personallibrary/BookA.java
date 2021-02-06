package com.example.personallibrary;

public class BookA {
    long bookId;
    String title;
    String author;
    String date;
    String finishDate;

    public BookA(String title, String author, String date, String finishDate) {

        this.title = title;
        this.author = author;
        this.date = date;
        this.finishDate = finishDate;
    }
    public BookA(){}

    public BookA( long idb, String title, String author, String date, String finishDate) {

        this.bookId = idb;
        this.title = title;
        this.author = author;
        this.date = date;
        this.finishDate = finishDate;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
