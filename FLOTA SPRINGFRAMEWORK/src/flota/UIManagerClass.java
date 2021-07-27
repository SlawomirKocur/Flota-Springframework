/*
 * This class sets a look of windows, buttons,and overall look of app
 */

package flota;

import java.awt.Color;
import javax.swing.UIManager;

import org.springframework.stereotype.Component;

@Component
public class UIManagerClass{

	
	
	public UIManagerClass() {
		
		
		//look of buttons
		UIManager.put("Button.background", Color.LIGHT_GRAY);
		UIManager.put("Button.foreground" , Color.BLACK);
		
		
		
		// look of labels
		UIManager.put("Label.foreground", Color.BLACK);
		
		//look of ToolTip
		UIManager.put("ToolTip.background", Color.WHITE);
		
		UIManager.put("OptionPane.background", Color.GRAY);
		UIManager.put("OptionPane.foreground", Color.GRAY);
		UIManager.put("OptionPane.messageForeground", Color.BLACK);
		UIManager.put("Panel.background", Color.GRAY);
		UIManager.put("window", Color.GRAY);
		
		UIManager.put("ScrollPane.background", Color.GRAY);
		UIManager.put("ScrollPane.foreground", Color.BLACK);
		
		UIManager.put("table.background", Color.GRAY);
		UIManager.put("table.foreground", Color.BLACK);
		UIManager.put("table.focusCellBackground", Color.GRAY);
		UIManager.put("table.focusCellForeground", Color.BLACK);
		UIManager.put("table.focusCellHighlightBorder", Color.WHITE);
		
		UIManager.put("ComboBox.buttonBackground", Color.GRAY);
		UIManager.put("ComboBox.background", Color.WHITE);
	}
	
}
