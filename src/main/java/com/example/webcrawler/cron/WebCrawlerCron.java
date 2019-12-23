package com.example.webcrawler.cron;

import com.example.webcrawler.entity.WebCrawler;
import com.example.webcrawler.repository.WebCrawlerRepository;
import com.example.webcrawler.service.WebCrawlerService;
import com.example.webcrawler.threadpool.CustomThreadPoolIntiate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebCrawlerCron {

    @Autowired
    private WebCrawlerRepository webCrawlerRepository;
    @Autowired
    private WebCrawlerService webCrawlerService;
    @Scheduled(cron = "${web.crawler.submit.cron}")
    void webCrawlerCron() {
        try {
            List<WebCrawler> webCrawlerList = webCrawlerRepository.webCrawlers();
            for(WebCrawler webCrawler : webCrawlerList) {
                CustomThreadPoolIntiate.threadPool.submit(new Runnable() {
                    public void run() {
                        try {
                            webCrawler.setStatus(1);
                            webCrawlerRepository.save(webCrawler);
                            System.out.println("Id picked for Crawling " + webCrawler.getId());
                            webCrawlerService.initiateWebCrawling(webCrawler);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
