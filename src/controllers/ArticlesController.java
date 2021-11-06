package controllers;

import java.util.Vector;

import models.Article;
import models.Journalist;

public class ArticlesController {
	
	public ArticlesController() {}
	Vector<Article> articles;
	
	public void addArticle (String name, String description, String authorName, int id, Journalist author) {
		Article newArticle =  new Article(name, authorName, authorName, id, author);
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
	
	
	public void approveArticleById(String articleId) {
		
	}
	
}
