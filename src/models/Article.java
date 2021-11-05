package models;

public class Article {
	private String title,description,authorName;
	private boolean approved;
	public Article(String title, String description, String authorName){
		this.setTitle(title);
		this.setDescription(description);
		this.setAuthorName(authorName);
		this.setApproved(false);
		
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
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
