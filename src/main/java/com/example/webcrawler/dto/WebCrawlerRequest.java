package com.example.webcrawler.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WebCrawlerRequest {
    @NotEmpty(message = "url cannot be blank")
    private String url;
    @NotNull(message = "depth can not be null")
    private Integer depth;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }
}
