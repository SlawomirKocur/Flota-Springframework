/*
 * This class builds first window od application
 * this is login window
 * 
 */

package flota;

import java.awt.Color;

import javax.swing.JFrame;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@Component("loginwindow")
public class LoginWindow implements WindowGUI {

	public JFrame frame;
	public JTextField userField;
	public JPasswordField userPassword;
	private JLabel passwordLabel;
	private JLabel infoLoginLabel;

	@Autowired
	private LoginAction login;
	
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void windowBuild() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBackground(Color.GRAY);
		frame.setForeground(Color.BLACK);
		frame.setBounds(100, 100, 533, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		userField = new JTextField();
		userField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userField.setHorizontalAlignment(SwingConstants.TRAILING);
		userField.setToolTipText("podaj login \"admin\"");
		userField.setBounds(223, 94, 229, 28);
		frame.getContentPane().add(userField);
		userField.setColumns(10);

		userPassword = new JPasswordField();
		userPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		userPassword.setToolTipText("podaj has\u0142o \"admin\"");
		userPassword.setBounds(223, 159, 227, 28);
		frame.getContentPane().add(userPassword);

		JLabel loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginLabel.setBounds(116, 94, 97, 28);
		frame.getContentPane().add(loginLabel);

		passwordLabel = new JLabel("Has\u0142o");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordLabel.setBounds(116, 159, 97, 28);
		frame.getContentPane().add(passwordLabel);

		infoLoginLabel = new JLabel("Podaj login i has\u0142o aby uruchomic aplikacje");
		infoLoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoLoginLabel.setBounds(10, 11, 403, 28);
		frame.getContentPane().add(infoLoginLabel);

		JButton loginButton = new JButton("Zaloguj");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					login.compare();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				
				//temp.insert();
				
			}
		});
		loginButton.setBounds(341, 225, 111, 35);
		frame.getContentPane().add(loginButton);
		
		JLabel lblNewLabel = new JLabel("username: admin      has\u0142o: admin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 222, 260, 38);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);

	}
}
