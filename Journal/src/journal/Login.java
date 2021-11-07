package journal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameField;
	private JPasswordField PasswordField;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Login() {
		
		//adding GUI components
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setForeground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UsernameField = new JTextField();
		UsernameField.setForeground(new Color(255, 255, 255));
		UsernameField.setFont(new Font("Segoe UI", Font.BOLD, 16));
		UsernameField.setBackground(new Color(192, 192, 192));
		UsernameField.setBounds(175, 84, 320, 46);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.setForeground(new Color(255, 255, 255));
		LoginBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LoginBtn.setBackground(new Color(192, 192, 192));
		LoginBtn.setBounds(175, 349, 320, 50);
		contentPane.add(LoginBtn);
		
		PasswordField = new JPasswordField();
		PasswordField.setForeground(new Color(255, 255, 255));
		PasswordField.setBackground(new Color(192, 192, 192));
		PasswordField.setFont(new Font("Segoe UI", Font.BOLD, 16));
		PasswordField.setBounds(175, 225, 320, 53);
		contentPane.add(PasswordField);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(175, 58, 132, 22);
		contentPane.add(lblNewLabel);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblPassword.setBounds(175, 198, 132, 22);
		contentPane.add(lblPassword);
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Driver driver = new Driver();
				User u = new User();
				try {
					driver.createConnection();
					boolean check = u.checkUsernamePassword(UsernameField.getText(), PasswordField.getText(),driver);
					if(check)
						dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}
}
