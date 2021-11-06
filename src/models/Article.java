package models;

public class Article {
	private int id;
	private String title,description;
	private Journalist author;
	private boolean approved;
	public Article(String title, String description, String authorName, int id, Journalist author){
		this.setTitle(title);
		this.setDescription(description);
		this.setApproved(false);
		this.setId(id);
		this.setAuthor(author);
		
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
	
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Journalist getAuthor() {
		return author;
	}
	public void setAuthor(Journalist author) {
		this.author = author;
	}
}
