package com.example.webcrawler.serviceImpl;

import com.example.webcrawler.dto.WebCrawlerDetailsResponse;
import com.example.webcrawler.entity.WebCrawler;
import com.example.webcrawler.entity.WebCrawlerDetails;
import com.example.webcrawler.repository.WebCrawlerDetailsRepository;
import com.example.webcrawler.repository.WebCrawlerRepository;
import com.example.webcrawler.service.WebCrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class WebCrawlerServiceImpl implements WebCrawlerService {

    //private static  Integer MIN_DEPTH=0;
    private static final int MAX_DEPTH = 10;
    @Autowired
    private WebCrawlerRepository webCrawlerRepository;
    @Autowired
    private WebCrawlerDetailsRepository webCrawlerDetailsRepository;

    @Override
    public void startWebCrawling(String URL, Integer depth, Integer maxDepth, Integer baseUrlID) {

        HashSet<String> links = new HashSet<>();
        if ((!links.contains(URL) && (depth < maxDepth))) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);
                Document document = Jsoup.connect(URL)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
                Elements imagesOnPage = document.select("img");
                String title = document.title();
                int i = 0;
                for (Element images : imagesOnPage) {
                    i++;
                }
                System.out.println("image count " + i);

                Elements linksOnPage = document.select("a[href]");
                WebCrawlerDetails webCrawlerDetails = new WebCrawlerDetails();
                webCrawlerDetails.setBaseUrl(baseUrlID);
                webCrawlerDetails.setImageCount(i);
                webCrawlerDetails.setTitle(title);
                webCrawlerDetails.setUrl(URL);
                webCrawlerDetailsRepository.save(webCrawlerDetails);
                depth++;
                for (Element page : linksOnPage) {

                    startWebCrawling(page.attr("abs:href"), depth, maxDepth, baseUrlID);
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    @Override
    public void initiateWebCrawling(WebCrawler webCrawler) {
        this.startWebCrawling(webCrawler.getUrl(), 0, webCrawler.getDepth(), webCrawler.getId());
        webCrawler.setStatus(2);
        webCrawlerRepository.save(webCrawler);
        System.out.println("Id finished Crawling " + webCrawler.getId());
    }

    @Override
    public void storeWebCrawlerRequest(String url, Integer depth) {
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.setDepth(depth);
        webCrawler.setUrl(url);
        webCrawler.setStatus(0);
        webCrawlerRepository.save(webCrawler);
    }

    @Override
    public String getStatus(String url) {
        List<WebCrawler> webCrawlers = webCrawlerRepository.webCrawlersByURL(url);
        String result="Not Found";
        for(WebCrawler webCrawler : webCrawlers){
            switch (webCrawler.getStatus()){
                case 0:
                    result="Submitted";
                    break;
                case 1:
                    result="In-Process";
                    break;
                case 2:
                    result="Processed";
                    break;
                default:
                    result="failed";
            }
return result;
        }
        return result;
    }

    @Override
    public WebCrawlerDetailsResponse getDetails(String url) {
        List<WebCrawler> webCrawlers = webCrawlerRepository.webCrawlersByURL(url);
        WebCrawlerDetailsResponse response = new WebCrawlerDetailsResponse();
        List<WebCrawlerDetailsResponse.Details> details = new ArrayList<>();
        int totalLink= 0;
        int totalImage=0;
        for(WebCrawler webCrawler : webCrawlers){
            List<WebCrawlerDetails> webCrawlerDetailsList = webCrawlerDetailsRepository.webCrawlerDetailsList(webCrawler.getId());

            for(WebCrawlerDetails webCrawlerDetails : webCrawlerDetailsList){
                WebCrawlerDetailsResponse.Details detail = new WebCrawlerDetailsResponse.Details();
                detail.setImage_count(webCrawlerDetails.getImageCount());
                detail.setPage_link(webCrawlerDetails.getUrl());
                detail.setPage_title(webCrawlerDetails.getTitle());
                details.add(detail);
                totalLink++;
                totalImage=totalImage+detail.getImage_count();
            }
        }
        response.setDetails(details);
        response.setTotal_images(totalImage);
        response.setTotal_links(totalLink);
        return response;
    }
}
