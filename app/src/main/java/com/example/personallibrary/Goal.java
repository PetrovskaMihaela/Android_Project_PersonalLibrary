package com.example.personallibrary;

public class Goal {
    String user;
    int books;
    int hours;
    int pages;

    public Goal(String user, int books, int hours, int pages) {
        this.user = user;
        this.books = books;
        this.hours = hours;
        this.pages = pages;
    }
    public Goal(){}

    public String getUser() {
        return user;
    }

    public int getBooks() {
        return books;
    }

    public int getHours() {
        return hours;
    }

    public int getPages() {
        return pages;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setBooks(int books) {
        this.books = books;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
