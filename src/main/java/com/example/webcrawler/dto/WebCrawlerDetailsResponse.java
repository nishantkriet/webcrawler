package com.example.webcrawler.dto;


import java.util.ArrayList;
import java.util.List;

public class WebCrawlerDetailsResponse {

    private Integer total_links;
    private Integer total_images;
    List<Details> details = new ArrayList<>();

    public Integer getTotal_links() {
        return total_links;
    }

    public void setTotal_links(Integer total_links) {
        this.total_links = total_links;
    }

    public Integer getTotal_images() {
        return total_images;
    }

    public void setTotal_images(Integer total_images) {
        this.total_images = total_images;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public static class Details{
        private String page_title;
        private String page_link;
        private Integer image_count;

        public String getPage_title() {
            return page_title;
        }

        public void setPage_title(String page_title) {
            this.page_title = page_title;
        }

        public String getPage_link() {
            return page_link;
        }

        public void setPage_link(String page_link) {
            this.page_link = page_link;
        }

        public Integer getImage_count() {
            return image_count;
        }

        public void setImage_count(Integer image_count) {
            this.image_count = image_count;
        }
    }
}
