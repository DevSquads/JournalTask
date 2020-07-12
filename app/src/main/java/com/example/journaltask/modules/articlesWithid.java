package com.example.journaltask.modules;

public class articlesWithid {
    private String author;
    private String body;
    private String title;

    public String getKey() {
        return key;
    }

    public articlesWithid(String author, String body, String title, String key) {
        this.author = author;
        this.body = body;
        this.title = title;
        this.key = key;
    }

    private String key;

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }


}
