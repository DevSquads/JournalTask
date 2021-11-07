package journal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Color;

public class SamirDashboard extends JFrame {

	private JPanel contentPane;
	private JTextField currentPassword;
	private JTextField newPassword;
	private JTextField usernameText;
	private JTextField passwordText;
	private JTextField nameText;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SamirDashboard(User u, Driver d) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
		});
		
		//Retrieving articles' and journalists' data from the database
		List<Article> aList = d.GetAllArticles();
		List<User> uList = d.GetAllUsers();
		
		//adding GUI components
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane Menu = new JTabbedPane(JTabbedPane.TOP);
		Menu.setBackground(new Color(70, 130, 180));
		Menu.setBounds(0, 10, 610, 509);
		contentPane.add(Menu);
		
		JPanel ChangePassword = new JPanel();
		ChangePassword.setBackground(new Color(70, 130, 180));
		ChangePassword.setBounds(115, 181, 332, 160);
		ChangePassword.setLayout(null);
		JPanel Homepage = new JPanel();
		Homepage.setBackground(new Color(70, 130, 180));
		Homepage.setBounds(75, 10, 219, 140);
		Homepage.setLayout(null);
		JPanel PendingArticles = new JPanel();
		PendingArticles.setBackground(new Color(70, 130, 180));
		PendingArticles.setBounds(159, 281, 273, 48);
		PendingArticles.setLayout(null);
		Menu.add("HomePage",Homepage);
		
		JLabel homepageLabel = new JLabel("WELCOME " + u.getName().toUpperCase() + "!");
		homepageLabel.setForeground(new Color(255, 255, 255));
		homepageLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		homepageLabel.setBounds(198, 22, 240, 40);
		Homepage.add(homepageLabel);
		
		JButton createArticleBtn = new JButton("Create Article");
		createArticleBtn.setBackground(new Color(192, 192, 192));
		createArticleBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		createArticleBtn.setForeground(new Color(255, 255, 255));
		createArticleBtn.setBounds(217, 410, 192, 40);
		Homepage.add(createArticleBtn);
		
		JPanel viewArticlesPanel = new JPanel();
		viewArticlesPanel.setBackground(new Color(70, 130, 180));
		viewArticlesPanel.setBounds(0, 71, 605, 330);
		Homepage.add(viewArticlesPanel);
		viewArticlesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel pendingPanel = new JPanel();
		pendingPanel.setBackground(new Color(70, 130, 180));
		pendingPanel.setBounds(0, 0, 605, 379);
		PendingArticles.add(pendingPanel);
		JComboBox pendingTitles = new JComboBox();
		pendingTitles.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		pendingTitles.setForeground(new Color(255, 255, 255));
		pendingTitles.setBackground(new Color(192, 192, 192));
		pendingTitles.setBounds(38, 423, 228, 21);
		PendingArticles.add(pendingTitles);
		
		JButton SaveBtn = new JButton("Save Changes");
		SaveBtn.setForeground(new Color(255, 255, 255));
		SaveBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		SaveBtn.setBackground(new Color(192, 192, 192));
		
		SaveBtn.setBounds(222, 284, 192, 62);
		ChangePassword.add(SaveBtn);
		
		JLabel label1 = new JLabel("Current Password");
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label1.setBounds(50, 46, 156, 19);
		ChangePassword.add(label1);
		
		currentPassword = new JTextField();
		currentPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		currentPassword.setBackground(new Color(192, 192, 192));
		currentPassword.setForeground(new Color(255, 255, 255));
		currentPassword.setBounds(50, 75, 271, 42);
		currentPassword.setText(u.getPassword());
		ChangePassword.add(currentPassword);
		currentPassword.setColumns(10);
		
		JLabel label2 = new JLabel("New Password");
		label2.setForeground(new Color(255, 255, 255));
		label2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label2.setBounds(50, 152, 133, 19);
		ChangePassword.add(label2);
		
		newPassword = new JTextField();
		newPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		newPassword.setBackground(new Color(192, 192, 192));
		newPassword.setForeground(new Color(255, 255, 255));
		newPassword.setColumns(10);
		newPassword.setBounds(50, 175, 271, 42);
		ChangePassword.add(newPassword);
		Menu.add("Pending Articles",PendingArticles);
		
		JButton publishBtn = new JButton("Publish Article");
		publishBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		publishBtn.setBackground(new Color(192, 192, 192));
		publishBtn.setForeground(new Color(255, 255, 255));
		JButton deleteBtn = new JButton("Delete Selected Article");
		deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		deleteBtn.setForeground(new Color(255, 255, 255));
		deleteBtn.setBackground(new Color(192, 192, 192));
		publishBtn.setBounds(331, 400, 150, 44);
		PendingArticles.add(publishBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Select article's title to be published");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(38, 389, 245, 24);
		PendingArticles.add(lblNewLabel_1);
		
		JPanel DeleteArticles = new JPanel();
		DeleteArticles.setBackground(new Color(70, 130, 180));
		Menu.addTab("Delete Articles", null, DeleteArticles);
		DeleteArticles.setLayout(null);
		
		JComboBox titlesCombo = new JComboBox();
		titlesCombo.setForeground(new Color(255, 255, 255));
		titlesCombo.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		titlesCombo.setBackground(new Color(192, 192, 192));
		titlesCombo.setMaximumRowCount(10);
		titlesCombo.setBounds(199, 110, 211, 21);
		DeleteArticles.add(titlesCombo);
		

		createArticleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewArticle a= new CreateNewArticle(u, d);
				a.setVisible(true);
				dispose();
			}
		});
		
		//viweing pending articles
		for(int k=0;k<aList.size();k++)
		{
			if(aList.get(k).getStatus().equals("pending"))
			{
				JTextArea area = new JTextArea();
				area.setText("Title: " + aList.get(k).getTitle() + "\n" + aList.get(k).getDescription() + "\n" + "Written By: " + aList.get(k).getAuthorName());
				area.setEditable(false);
				pendingTitles.addItem(aList.get(k).getTitle());
				pendingPanel.add(area);
			}
		}
		
		//sorting list of users toget the most popular ones
		Collections.sort(uList, Comparator.comparingInt(User::getnArticles).reversed());	//sorting users array according to number of articles (Most Popular)
		
		//viewing all articles sorted by most popular
		for(int i=0;i< uList.size();i++)
		{
			for(int j=0;j<aList.size();j++)
			{	
				
				if(aList.get(j).getAuthorUsername().equals(uList.get(i).getUsername())&& aList.get(j).getStatus().equals("approved"))
				{
					JTextArea area = new JTextArea();
					area.setText("Title: " + aList.get(j).getTitle() + "\n" + aList.get(j).getDescription() + "\n" + "Written By: " + aList.get(j).getAuthorName());
					area.setEditable(false);
					viewArticlesPanel.add(area);
				}
			}
		}
		
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
		
		publishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedTitle = pendingTitles.getSelectedItem().toString();
				int index = pendingTitles.getSelectedIndex();
				Article a= new Article();
				List <Article>  list = a.updateArticlesStatus(selectedTitle, aList);
				d.updateArticleStatus(selectedTitle);
				pendingTitles.removeItemAt(index);
				for(int k=0;k<list.size();k++)
				{			
					if(list.get(k).getTitle().equals(selectedTitle))
					{
						JTextArea area = new JTextArea();
						area.setText("Title: " + list.get(k).getTitle() + "\n" + list.get(k).getDescription() + "\n" + "Written By: " + list.get(k).getAuthorName());
						area.setEditable(false);
						pendingTitles.addItem(list.get(k).getTitle());
						viewArticlesPanel.add(area);
						
					}
				}
				
			}
		});
		
		for(int i=0;i<aList.size();i++)
		{
			titlesCombo.addItem(aList.get(i).getTitle());
		}		
		deleteBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String selectedTitle = titlesCombo.getSelectedItem().toString();
			Article a = new Article();
			a.deleteArticle(aList, selectedTitle);
			JOptionPane.showMessageDialog(null,"Article Deleted Successfully"); 
			int index = titlesCombo.getSelectedIndex();
			titlesCombo.removeItemAt(index);
			d.deleteArticle(selectedTitle);
			SamirDashboard s = new SamirDashboard(u,d);
			s.setVisible(true);
			}
		});
		deleteBtn.setBounds(199, 151, 211, 68);
		DeleteArticles.add(deleteBtn);
		JPanel Journalists = new JPanel();
		Journalists.setBackground(new Color(70, 130, 180));
		Menu.addTab("Add Journalists", null, Journalists);
		Journalists.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(69, 50, 103, 22);
		Journalists.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pen Name");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(69, 198, 103, 22);
		Journalists.add(lblNewLabel_1_1);
		
		JButton generatePassword = new JButton("Generate Password");
		generatePassword.setForeground(new Color(255, 255, 255));
		generatePassword.setBackground(new Color(192, 192, 192));
		generatePassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
		generatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int upperbound = 999999;
				Random rand = new Random();
				passwordText.setText(String.valueOf(rand.nextInt(upperbound)));   // generate random password from 0000000 to 99999999
			}
		});
		generatePassword.setBounds(112, 135, 189, 36);
		Journalists.add(generatePassword);
		
		usernameText = new JTextField();
		usernameText.setBackground(new Color(192, 192, 192));
		usernameText.setFont(new Font("Segoe UI", Font.BOLD, 16));
		usernameText.setForeground(new Color(255, 255, 255));
		usernameText.setBounds(69, 75, 189, 31);
		Journalists.add(usernameText);
		usernameText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setForeground(new Color(255, 255, 255));
		passwordText.setBackground(new Color(192, 192, 192));
		passwordText.setFont(new Font("Segoe UI", Font.BOLD, 16));
		passwordText.setEditable(false);
		passwordText.setColumns(10);
		passwordText.setBounds(334, 135, 212, 36);
		Journalists.add(passwordText);
		
		nameText = new JTextField();
		nameText.setBackground(new Color(192, 192, 192));
		nameText.setForeground(new Color(255, 255, 255));
		nameText.setFont(new Font("Segoe UI", Font.BOLD, 16));
		nameText.setBounds(69, 222, 189, 31);
		Journalists.add(nameText);
		nameText.setColumns(10);
		
		JButton addJournalistBtn = new JButton("Add Journalist");
		addJournalistBtn.setBackground(new Color(192, 192, 192));
		addJournalistBtn.setForeground(new Color(255, 255, 255));
		addJournalistBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		addJournalistBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u = new User(usernameText.getText(),passwordText.getText(),0,nameText.getText());
				d.addUser(u);
				JOptionPane.showMessageDialog(null,"New Journalist Added Successfully"); 
			}
		});
		addJournalistBtn.setBounds(205, 297, 212, 46);
		Journalists.add(addJournalistBtn);
		Menu.add("Change Password",ChangePassword);
		
	}
}
