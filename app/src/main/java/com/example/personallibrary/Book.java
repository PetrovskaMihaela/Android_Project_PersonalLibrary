package com.example.personallibrary;



public class Book {
    private String mTitle;
    private String mAuthors;
    private String mPublishedDate;
    private String mDescription;
    private String mCategories;
    private String mThumbnail;
    private String mrRetailPrice;
    private String mBuy;
    private String mPreview;
    private String mPrice;
    private int pageCount;
    private String mUrl;
    public Book(String mTitle, String mAuthors, String mPublishedDate, String mDescription, String mCategories, String mThumbnail,
                String mBuy, String mPerview , String price,int pageCount , String mUrl) {
        this.mTitle = mTitle;
        this.mAuthors = mAuthors;
        this.mPublishedDate = mPublishedDate;
        this.mDescription = mDescription;
        this.mCategories = mCategories;
        this.mThumbnail = mThumbnail;
        this.mBuy = mBuy;
        this.mPreview = mPerview;
        this.mPrice = price;
        this.pageCount = pageCount;
        this.mUrl = mUrl;
    }


    public String getmUrl() {
        return mUrl;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthors() {
        return mAuthors;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getCategories() {
        return mCategories;
    }

    public String getThumbnail() {
        return mThumbnail;
    }


    public String getBuy() {
        return mBuy;
    }

    public String getPerview() {
        return mPreview;
    }

    public String getPrice() {
        return mPrice;
    }




}
/*class Book
{
    // fields
    private String title;
    private String author;
    private String pubDate;
    private String category;
    private double retailPrice;
    private String imageURL;

    // constructor
    public Book()
    {
        // constructor for RecyclerView
    }

    public Book(String title, String author, String pubDate, String category, double retailPrice, String imageURL)
    {
        this.title = title;
        this.author = author;
        this.pubDate = pubDate;
        this.category = category;
        this.retailPrice = retailPrice;
        this.imageURL = imageURL;
    }

    // methods
    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public String getCategory()
    {
        return category;
    }

    public double getRetailPrice()
    {
        return retailPrice;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    @NonNull
    @Override
    public String toString()
    {
        return title + " " + retailPrice;
    }
}*/

