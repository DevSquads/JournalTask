package com.toka.legendarynews;

import java.util.Objects;

public class Article {
    private String title;
    private String description;
    private String id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(getTitle(), article.getTitle()) && Objects.equals(getDescription(), article.getDescription()) && Objects.equals(getId(), article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getId());
    }
}
