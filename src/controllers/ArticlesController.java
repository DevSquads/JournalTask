package controllers;

import java.util.Vector;

import models.Article;

public class ArticlesController {
	
	public ArticlesController() {}
	Vector<Article> articles;
	
	public void addArticle (String name, String description, String authorName) {
		Article newArticle =  new Article(name, authorName, authorName);
		articles.add(newArticle);
	}
	
	public void deleteArticleByTitle (String title) {
		for(int i=0 ; i < articles.size() ; i++)
			if(articles.elementAt(i).getTitle() == title) 
			{
				articles.remove(i);
				break;
			}			
	}
	
}
