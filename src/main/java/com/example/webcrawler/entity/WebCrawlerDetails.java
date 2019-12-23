package com.example.webcrawler.entity;

import javax.persistence.*;

@Entity
@Table(name = "web_crawler_details")
public class WebCrawlerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private Integer baseUrl;
    private String url;
    private Integer imageCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(Integer baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getImageCount() {
        return imageCount;
    }

    public void setImageCount(Integer imageCount) {
        this.imageCount = imageCount;
    }
}
