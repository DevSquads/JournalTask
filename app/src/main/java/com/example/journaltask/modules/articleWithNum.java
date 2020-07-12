package com.example.journaltask.modules;

public class articleWithNum {
    private String author;
    private String body;
    private String title;
private String id;

    public articleWithNum(String author, String body, String title, String id, int number) {
        this.author = author;
        this.body = body;
        this.title = title;
        this.id = id;
        this.number = number;
    }

    public int getKey() {
        return number;
    }

    public String getId() {
        return id;
    }

    private int number;

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
