package com.example.webcrawler.controller;

import com.example.webcrawler.dto.DetailsRequest;
import com.example.webcrawler.dto.WebCrawlerDetailsResponse;
import com.example.webcrawler.dto.WebCrawlerRequest;
import com.example.webcrawler.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/webcrawler")
public class WebCrawlerController {
    @Autowired
    private WebCrawlerService webCrawlerService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String storeWebCrawlerRequest(@Valid @RequestBody WebCrawlerRequest webCrawlerRequest) {
        webCrawlerService.storeWebCrawlerRequest(webCrawlerRequest.getUrl(), webCrawlerRequest.getDepth());
        return "submitted";
    }

    @RequestMapping(value = "/getDetails",method = RequestMethod.POST)
    public WebCrawlerDetailsResponse getDetails(@RequestBody DetailsRequest detailsRequest){
        return webCrawlerService.getDetails(detailsRequest.getBaseUrl());
    }
    @RequestMapping(value = "/getStatus",method = RequestMethod.POST)
    public String getStatus(@RequestBody DetailsRequest detailsRequest){
        return webCrawlerService.getStatus(detailsRequest.getBaseUrl());
    }
}
