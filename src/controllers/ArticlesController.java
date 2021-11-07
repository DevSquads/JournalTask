package controllers;

import java.util.HashMap;
import java.util.Vector;

import models.Article;
import models.Journalist;
import models.Role;
import models.RoleName;

public class ArticlesController {
	
	public ArticlesController() {}
	
	Vector<Article> approvedArticles;
	Vector<Article> pendingArticles;
	
	public void addArticle (String name, String description, String authorName, int id, Journalist author) {
		Article newArticle =  new Article(name, authorName, authorName, id, author);
		approvedArticles.add(newArticle);
	}
	
	public void deleteArticleById (int id) {
		for(int i=0 ; i < approvedArticles.size() ; i++)
			if(approvedArticles.elementAt(i).getId() == id) 
			{
				approvedArticles.remove(i);
				break;
			}			
	}
	
	
	public void approveArticle(int articleId) {
			for(int i=0;i<pendingArticles.size(); i++)
			{	
				Article currentPendingArticle = pendingArticles.elementAt(i);
				if(currentPendingArticle.getId() == articleId)
				{
					pendingArticles.remove(i);
					approvedArticles.add(currentPendingArticle);
			
				}
			}
	}
	
	public void deleteArticle(int articleId) {
		approvedArticles.removeIf(article -> (article.getId() == articleId));
	}
	
	public void showAllArticles() {
		// key : authorId - value : number of published articles
		HashMap<Integer, Integer> authorsArticles =  getArticlesFrequencyMap();
		

	}
	
	private HashMap<Integer, Integer> getArticlesFrequencyMap () {
		HashMap<Integer, Integer> authorsArticles =  new HashMap<Integer, Integer>();
			
		for(int i=0; i<approvedArticles.size(); i++) {
			int authorId = approvedArticles.elementAt(i).getAuthor().getId();
			int newValue = 0;
			 if(authorsArticles.get(authorId) == null)
				 newValue = 1;
			 else
				 newValue = authorsArticles.get(authorId) + 1;
			
			authorsArticles.put(approvedArticles.elementAt(i).getAuthor().getId(), newValue);
		}
		return authorsArticles;
	}
	
	
	
}
