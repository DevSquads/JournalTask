package journal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

public class User {

	private String username;   // username to login to the app
	private String password;
	private int nArticles;  // number of articles published by this author
	private String name;  // Author name to be publish he article with
	
	public User(String username, String password, int nArticles, String name) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setnArticles(nArticles);
		this.setName(name);
	}
	
	public User() {
	}
	 //setters and getters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getnArticles() {
		return nArticles;
	}

	public void setnArticles(int nArticles) {
		this.nArticles = nArticles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// check if the user exists
	public boolean checkUsernamePassword(String username, String password, Driver d) {    
		List<User> uList = d.GetAllUsers();     // get all users in the database
		for(int i=0; i< uList.size(); i++) {
			if(uList.get(i).getUsername().equals(username) && uList.get(i).getPassword().equals(password)){
				// check if the user is 'samir' or a 'normal journalist'
				if(uList.get(i).getUsername().equals("admin"))
				{
					// admin dashboard
					JOptionPane.showMessageDialog(null,"Logged in Successfully");  
					SamirDashboard s = new SamirDashboard(uList.get(i),d);
					s.setVisible(true);
					
					return true;
				}
				else {
					// normal journalist dashboard
					JOptionPane.showMessageDialog(null,"Logged in Successfully");  
					JournalistsDashboard j = new JournalistsDashboard(uList.get(i),d);
					j.setVisible(true);
					return true;
				}		
			}
			
		}
		JOptionPane.showMessageDialog(null,"Username or password is incorrect! Try Again","Alert",JOptionPane.WARNING_MESSAGE);
			return false;
	}
	
}
