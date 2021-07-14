package flota;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UIManagerClass{

	
	@Autowired
	public UIManagerClass() {
		
		
		//look of buttons
		UIManager.put("Button.background", Color.LIGHT_GRAY);
		UIManager.put("Button.foreground" , Color.BLACK);
		
		
		
		// look of labels
		UIManager.put("Label.foreground", Color.BLACK);
		
		//look of ToolTip
		UIManager.put("ToolTip.background", Color.WHITE);
		
		
	}
	
}
