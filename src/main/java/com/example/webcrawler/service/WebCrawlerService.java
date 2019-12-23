package com.example.webcrawler.service;

import com.example.webcrawler.dto.WebCrawlerDetailsResponse;
import com.example.webcrawler.entity.WebCrawler;

import java.util.List;

public interface WebCrawlerService {
    public void startWebCrawling(String url, Integer depth,Integer maxDepth,Integer baseUrlId);
    public void initiateWebCrawling(WebCrawler webCrawler);
    public void storeWebCrawlerRequest(String url, Integer depth);
    public String getStatus(String url);
    public WebCrawlerDetailsResponse getDetails(String url);
}
