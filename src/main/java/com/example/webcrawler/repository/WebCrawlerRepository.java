package com.example.webcrawler.repository;

import com.example.webcrawler.entity.WebCrawler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WebCrawlerRepository extends JpaRepository<WebCrawler,Integer> {
    @Query("Select wc from WebCrawler wc where wc.status=0")
    List<WebCrawler> webCrawlers();
    @Query("Select wc from WebCrawler wc where wc.url = :url")
    List<WebCrawler> webCrawlersByURL(@Param("url")String url);
}
