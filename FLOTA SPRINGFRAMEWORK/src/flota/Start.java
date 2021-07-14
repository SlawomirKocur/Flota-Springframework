package flota;

import java.awt.EventQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;


public class Start {
	
	
	public static void main(String[] args) {

		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(FlotaConfig.class);
		context.registerShutdownHook();
		
		
		// Application starts
		// in one/first thread
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				// Creates a Springframewort container
				// and closes the container when main method is closed
				
				

				// initialize first window of GUI
				try {

					LoginWindow logWindow = context.getBean("loginwindow", LoginWindow.class);
					logWindow.windowBuild();
				

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

}
