/*this class verifies the values entered by user
 * and default values
 * 
 * a link with container is established (container is declared in Start.class)
 * 
 * 
 */

package flota;

import java.util.Arrays;

import javax.swing.JOptionPane;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("logAction")
public class LoginAction {

	// declaring and initializing proper valuer fo r login and password
	private String correctloginName = "admin";
	private String correctPassword = "admin";
	// private char[] correctPassword = new char[] {'a','d','m','i','n'};

	@Autowired
	private LoginWindow logWin;

	public void compare() {

		StringBuilder password = new StringBuilder();
		password.append(logWin.userPassword.getPassword());

		String pass = password.toString();

		if ((logWin.userField.getText().compareTo(correctloginName) == 0) & (pass.compareTo(correctPassword) == 0))

			JOptionPane.showMessageDialog(null, "has這 prawid這we");

		else {
			JOptionPane.showMessageDialog(null, "has這 nieprawid這we");
		}

	}

}
