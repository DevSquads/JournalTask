package com.toka.legendarynews;

import java.util.Objects;

public class Article {
    private Author author;
    private String title;
    private String description;
    private String id;
    private boolean isPublished;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return isPublished() == article.isPublished() && Objects.equals(getAuthor(), article.getAuthor()) && Objects.equals(getTitle(), article.getTitle()) && Objects.equals(getDescription(), article.getDescription()) && Objects.equals(getId(), article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getTitle(), getDescription(), getId(), isPublished());
    }
}
