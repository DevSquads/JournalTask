package journal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CreateNewArticle extends JFrame {

	private JPanel contentPane;
	private JTextField articleTitle;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CreateNewArticle(User u, Driver d) {
		
		//Adding GUI Components
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 581);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(57, 23, 68, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblArticle = new JLabel("Article");
		lblArticle.setForeground(new Color(255, 255, 255));
		lblArticle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblArticle.setBounds(57, 116, 68, 22);
		contentPane.add(lblArticle);
		
		articleTitle = new JTextField();
		articleTitle.setForeground(new Color(255, 255, 255));
		articleTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		articleTitle.setBackground(new Color(192, 192, 192));
		articleTitle.setBounds(57, 55, 209, 37);
		contentPane.add(articleTitle);
		articleTitle.setColumns(10);
		
		JTextArea articleDescription = new JTextArea();
		articleDescription.setForeground(new Color(255, 255, 255));
		articleDescription.setBackground(new Color(192, 192, 192));
		articleDescription.setFont(new Font("Segoe UI", Font.BOLD, 14));
		articleDescription.setBounds(57, 148, 570, 234);
		contentPane.add(articleDescription);
		JButton finishBtn = new JButton("Finish");
		finishBtn.setBackground(new Color(192, 192, 192));
		finishBtn.setForeground(new Color(255, 255, 255));
		finishBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		//adding a new article
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Article a = new Article(articleTitle.getText(),articleDescription.getText(),u.getName(), u.getUsername(),"pending");
				d.addArticle(a, u);
				JOptionPane.showMessageDialog(null,"Added Successfully"); 
				SamirDashboard s = new SamirDashboard(u,d);
				s.setVisible(true);
				dispose();
				
			}
		});
		finishBtn.setBounds(263, 427, 174, 37);
		contentPane.add(finishBtn);
	}
}
