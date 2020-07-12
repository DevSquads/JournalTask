package com.example.journaltask.modules;

public class articles {
    private String author,body,title;

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public articles(String author, String body, String title) {
        this.author = author;
        this.body = body;
        this.title = title;
    }
}
