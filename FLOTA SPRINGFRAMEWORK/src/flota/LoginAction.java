/*this class verifies if user name and password has been entered properly
 * 
 */

package flota;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("logAction")
public class LoginAction {

	// declaring and initializing proper valuer fo r login and password
	private String correctloginName = "admin";
	private String correctPassword = "admin";
	
	@Autowired
	private MainGUIWindow mainWindow;

	@Autowired
	private LoginWindow logWin;

	public void compare() throws SQLException {
		/*
		 * transforms Array into String
		 * getPassword() returns an Array do to compare with String it
		 * must be converted 
		 */
		StringBuilder password = new StringBuilder();
		password.append(logWin.userPassword.getPassword());

		String pass = password.toString();

		
		//checks if statement is true
		if ((logWin.userField.getText().compareTo(correctloginName) == 0) & (pass.compareTo(correctPassword) == 0)) {

			JOptionPane.showMessageDialog(null, "has這 prawid這we");
			mainWindow.initialize();
			logWin.frame.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "has這 nieprawid這we");
		}

	}

}
