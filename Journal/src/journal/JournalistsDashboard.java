package journal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.Color;

public class JournalistsDashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public JournalistsDashboard(User u, Driver d) {       
		
		//Retrieving articles' and journalists' data from the database
		List<Article> aList = d.GetAllArticles();
		List<User> uList = d.GetAllUsers();
		
		//Adding GUI Components
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane Menu = new JTabbedPane(JTabbedPane.TOP);
		Menu.setBounds(0, 10, 610, 509);
		Menu.setBackground(new Color(70, 130, 180));
		contentPane.add(Menu);		
		JPanel ChangePassword = new JPanel();
		ChangePassword.setBackground(new Color(70, 130, 180));
		ChangePassword.setBounds(115, 181, 332, 160);
		JPanel Homepage = new JPanel();
		Homepage.setBackground(new Color(70, 130, 180));
		Homepage.setBounds(75, 10, 219, 140);
		Homepage.setLayout(null);
		Menu.add("HomePage",Homepage);
		
		JLabel homepageLabel = new JLabel("WELCOME " + u.getName().toUpperCase() + "!");
		homepageLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		homepageLabel.setBounds(200, 20, 240, 40);
		Homepage.add(homepageLabel);
		JButton createArticleBtn = new JButton("Create Article");
		createArticleBtn.setBackground(new Color(220, 220, 220));
		createArticleBtn.setForeground(new Color(255, 255, 255));
		createArticleBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		createArticleBtn.setBounds(210, 419, 211, 40);
		Homepage.add(createArticleBtn);
		
		JPanel viewArticlesPanel = new JPanel();
		viewArticlesPanel.setBackground(new Color(70, 130, 180));
		viewArticlesPanel.setBounds(0, 71, 605, 338);
		Homepage.add(viewArticlesPanel);
		viewArticlesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JTextField newPassword = new JTextField();
		newPassword.setBounds(40, 171, 271, 42);
		newPassword.setForeground(new Color(255, 255, 255));
		newPassword.setBackground(new Color(192, 192, 192));
		newPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ChangePassword.setLayout(null);
		JButton SaveBtn = new JButton("Save Changes");
		SaveBtn.setBounds(208, 285, 201, 61);
		SaveBtn.setBackground(new Color(192, 192, 192));
		SaveBtn.setForeground(new Color(255, 255, 255));
		SaveBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		ChangePassword.add(SaveBtn);
		
		JLabel label1 = new JLabel("Current Password");
		label1.setBounds(40, 46, 158, 19);
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		ChangePassword.add(label1);
		JTextField currentPassword = new JTextField();
		currentPassword.setBounds(40, 70, 271, 42);
		currentPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		currentPassword.setForeground(new Color(255, 255, 255));
		currentPassword.setBackground(new Color(192, 192, 192));
		currentPassword.setText(u.getPassword());
		ChangePassword.add(currentPassword);
		currentPassword.setColumns(10);
		JLabel label2 = new JLabel("New Password");
		label2.setBounds(40, 148, 132, 19);
		label2.setForeground(new Color(255, 255, 255));
		label2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		ChangePassword.add(label2);
		newPassword.setColumns(10);
		ChangePassword.add(newPassword);		
		Menu.add("Change Password",ChangePassword);

		createArticleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewArticle a= new CreateNewArticle(u, d);
				a.setVisible(true);
				dispose();
			}
		});
		
		//View all approved articles to the journalist starting with their articles first
		for(int j=0;j<aList.size();j++)
			{			
				if(aList.get(j).getAuthorUsername().equals(u.getUsername())&& aList.get(j).getStatus().equals("approved"))
				{
					JTextArea area = new JTextArea();
					area.setText("Title: " + aList.get(j).getTitle() + "\n" + aList.get(j).getDescription() + "\n" + "Written By: " + aList.get(j).getAuthorName());
					area.setEditable(false);
					viewArticlesPanel.add(area);
				}
			}
		for(int j=0;j<aList.size();j++)
			{					
				if((!(aList.get(j).getAuthorUsername().equals(u.getUsername()))&& aList.get(j).getStatus().equals("approved")))
				{
					JTextArea area = new JTextArea();
					area.setText("Title: " + aList.get(j).getTitle() + "\n" + aList.get(j).getDescription() + "\n" + "Written By: " + aList.get(j).getAuthorName());
					area.setEditable(false);
					viewArticlesPanel.add(area);
				}
			}
			 // save new password to the database
			SaveBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newPass = newPassword.getText();
					u.setPassword(newPass);
					JOptionPane.showMessageDialog(null,"Password changed successfully"); 
					d.updatePassword(u);
					newPassword.setText("");
					currentPassword.setText(newPass);
				}
			});
			

	}
}
