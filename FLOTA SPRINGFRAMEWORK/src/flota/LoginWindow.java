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
import java.awt.event.ActionEvent;

@Component("loginwindow")
public class LoginWindow implements WindowGUI {

	private JFrame frame;
	public JTextField userField;
	public JPasswordField userPassword;
	private JLabel passwordLabel;
	private JLabel infoLoginLabel;

	@Autowired
	LoginAction login;

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
		userField.setToolTipText("podaj login");
		userField.setBounds(223, 94, 229, 28);
		frame.getContentPane().add(userField);
		userField.setColumns(10);

		userPassword = new JPasswordField();
		userPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		userPassword.setToolTipText("podaj has\u0142o");
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

		infoLoginLabel = new JLabel("Podaj login i has\u0142o aby po\u0142\u0105czy\u0107 si\u0119 z baza danych\r\n");
		infoLoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoLoginLabel.setBounds(10, 11, 403, 28);
		frame.getContentPane().add(infoLoginLabel);

		JButton loginButton = new JButton("Zaloguj");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// login.checkLogin();
				login.compare();
				// login.loginConfirm();

			}
		});
		loginButton.setBounds(341, 225, 111, 35);
		frame.getContentPane().add(loginButton);
		frame.setVisible(true);

	}
}
