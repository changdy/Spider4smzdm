package com.smzdm.model;

public class Channel {
    private Integer id;

    private String url;

    private String title;

    public Channel(Integer id, String url, String title) {
        this.id = id;
        this.url = url;
        this.title = title;
    }

    public Channel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}