package journal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {
	
	private Connection conn;
	public static int idCounter = 200;
	
		//create connection to database
		public void createConnection() throws Exception{ 					
			//connect to journal database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/journal", "root", "root");
		}
		
		//get all users in users table
		public List<User> GetAllUsers(){            
			
			List<User> UsersList = new ArrayList<User>();
			Statement st;
			ResultSet result;
			User tempU;
			
			try {
				st = conn.createStatement();
				result = st.executeQuery("select * from users");
				
				while(result.next())
				{
					tempU = convertToUser(result);
					UsersList.add(tempU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return UsersList;
		}
		
		//get all articles in users table
		public List<Article> GetAllArticles(){           
			
			List<Article> ArticlesList = new ArrayList<Article>();
			Statement st;
			ResultSet result;
			Article tempL;
			
			try {
				st = conn.createStatement();
				result = st.executeQuery("select * from articles");
				
				while(result.next())
				{
					tempL = convertToArticle(result);
					ArticlesList.add(tempL);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return ArticlesList;
		}
		
		public void updatePassword(User u)
		{
			try {
				
				String sql = "update users set password = ? where username = ?";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, u.getPassword());
				st.setString(2, u.getUsername());
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//helper function to convert each row in the user table into a user
		public User convertToUser(ResultSet r)            
			{
				User u = new User();
				try {	
					u.setUsername(r.getString("username"));
					u.setPassword(r.getString("password"));
					u.setnArticles(r.getInt("number_of_articles"));
					u.setName(r.getString("name"));
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return u;
		}
		
		//helper function to convert each row in the article table into an article
		public Article convertToArticle(ResultSet r)             
		{
			Article a = new Article();
			try {	
				a.setTitle(r.getString("title"));
				a.setDescription(r.getString("description"));
				a.setAuthorName(r.getString("author_name"));
				a.setAuthorUsername(r.getString("author_username"));
				a.setStatus(r.getString("status"));
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return a;
	}
		
		//adding a new article to the database
		public void addArticle(Article a, User u)
		{
			try {
				// insert the article in the articles table
				String sql = "insert into articles values(?,?,?,?,?,?)";   
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, String.valueOf(idCounter));
				st.setString(2, a.getTitle());
				st.setString(3, a.getDescription());
				st.setString(4, a.getAuthorName());
				st.setString(5, a.getAuthorUsername());
				st.setString(6, a.getStatus());
				st.executeUpdate();
				idCounter ++;
				// get the user who wrote the article
				String sql2 = ("select * from users where username = ?");    
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setString(1, a.getAuthorUsername());
				ResultSet result = st2.executeQuery();
				result.next();
				User user = convertToUser(result);
				// increment the number of articles published by the user
				String sql3 = "update users set number_of_articles = ? where username = ?";    
				PreparedStatement st3 = conn.prepareStatement(sql3);
				st3.setString(1, String.valueOf(user.getnArticles()+1));
				st3.setString(2, a.getAuthorUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//adding a new user to the database
		public void addUser(User u) {
			String sql = "insert into users values(?,?,?,?)";
			PreparedStatement st;
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, u.getUsername());
				st.setString(2, u.getPassword());
				st.setString(3, String.valueOf(u.getnArticles()));
				st.setString(4, u.getName());
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//deleting an article from the database
		public void deleteArticle(String title)
		{
			String sql ="delete from articles where title = ?";
			PreparedStatement st;
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, title);
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//updating the status of an article in the database from pending to approved
		public void updateArticleStatus(String title)
		{
			try {
				
				String sql = "update articles set status = 'approved' where title = ?";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, title);
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}