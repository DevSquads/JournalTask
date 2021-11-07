package models;

import java.util.Vector;
import models.Role;

public class Journalist {
	private int id;
	private String name;
	private Vector<Role> permissions;
	private Vector<Article> articles;
	
	Journalist(String name){
		this.setName(name);
	}
	
	public Vector<Role> getPermissions() {
		return permissions;
	}
	
	public void addPermission(Role newPermission) {
		this.permissions.add(newPermission);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Vector<Article> getArticles() {
		return articles;
	}

	public void addArticle(Article newArticle) {
		this.articles.add(newArticle);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
