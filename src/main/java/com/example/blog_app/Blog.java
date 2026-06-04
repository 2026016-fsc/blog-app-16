package com.example.blog_app;

import java.time.LocalDateTime;

public class Blog {
    private int id;
    private String title;
    private LocalDateTime postTime;
    private String article;

    public Blog(int id, String title, LocalDateTime postTime, String article) {
        this.id = id;
        this.title = title;
        this.postTime = postTime;
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}