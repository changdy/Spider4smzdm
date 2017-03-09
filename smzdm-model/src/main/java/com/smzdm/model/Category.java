package com.smzdm.model;

public class Category {
    private Integer id;

    private String title;

    private Integer parentId;

    private String urlNicktitle;

    private Integer level;

    public Category(Integer id, String title, Integer parentId, String urlNicktitle, Integer level) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.urlNicktitle = urlNicktitle;
        this.level = level;
    }

    public Category() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrlNicktitle() {
        return urlNicktitle;
    }

    public void setUrlNicktitle(String urlNicktitle) {
        this.urlNicktitle = urlNicktitle == null ? null : urlNicktitle.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}