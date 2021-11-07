package journal;

import java.util.List;

public class Article {
	
	private String title;   
	private String description;
	private String authorName;
	private String authorUsername;
	private String status;
	
	public Article(String title, String description, String authorName, String authorUsername, String status) {
		super();
		this.setTitle(title);
		this.setDescription(description);
		this.setAuthorName(authorName);
		this.setAuthorUsername(authorUsername);
		this.setStatus(status);
		
	}
	
	public Article() {
	}
	//setters and getters for the private attributes
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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	// delete the article the admin(Samir) requested to delete
	public void  deleteArticle(List<Article> list,String title) {
		for(int i =0;i<list.size();i++)
		{
			if(list.get(i).getTitle().equals(title))
			{
				list.remove(i);
				break;
			}
		}
	}
	
	//update the article's status from pending to approved to be published
	public List<Article> updateArticlesStatus(String title, List<Article> list)
	{
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getTitle().equals(title))
			{
				list.get(i).setStatus("approved");
				return list;
			}
		}
		return list;

	}
}
