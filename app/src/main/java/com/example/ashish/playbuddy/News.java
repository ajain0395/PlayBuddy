package com.example.ashish.playbuddy;

import java.util.Date;

public class News {


    private String newsId;
    private String newsTitle;
    private String newsDescription;
    private Date newsDate;
   // public String sportsId;

    public News()
    {

    }

    public News(String newsTitle, String newsDescription,Date newsDate) {
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsDate=newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public String getTitle() {
        return newsTitle;
    }

    public Date getDate() {
        return newsDate;
    }


    public String getDescription() {
        return newsDescription;
    }

    public void setTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public void setDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNews_id() {
        return newsId;
    }

    public void setNews_id(String newsId) {
        this.newsId = newsId;
    }
}
