package com.example.webcrawler.repository;

import com.example.webcrawler.entity.WebCrawlerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WebCrawlerDetailsRepository extends JpaRepository<WebCrawlerDetails, Integer> {
    @Query("Select wcb from WebCrawlerDetails wcb where wcb.baseUrl= :baseUrl")
    public List<WebCrawlerDetails> webCrawlerDetailsList(@Param("baseUrl") Integer baseUrl);
}
