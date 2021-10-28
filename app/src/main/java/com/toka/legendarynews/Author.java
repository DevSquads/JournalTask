package com.toka.legendarynews;

import java.util.Objects;

public class Author {
    private String name;
    private String id;
    private boolean isAdmin;
    private int noOfArticles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getNoOfArticles() {
        return noOfArticles;
    }

    public void setNoOfArticles(int noOfArticles) {
        this.noOfArticles = noOfArticles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return isAdmin() == author.isAdmin() && getNoOfArticles() == author.getNoOfArticles() && Objects.equals(getName(), author.getName()) && Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), isAdmin(), getNoOfArticles());
    }
}
