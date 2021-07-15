/*
 * This class creates a IOC container
 * a initialize the application in a thread
 */

package flota;

import java.awt.EventQueue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Start {

	public static void main(String[] args) {

		// Creates a Springframewort container
		// and closes the container when main method is closed
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(FlotaConfig.class);
		context.registerShutdownHook();

		// Application starts
		// in one/first thread
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				

				// initialize first window of GUI
				try {
					//gettin a bean from IOC container
					
					LoginWindow logWindow = context.getBean("loginwindow", LoginWindow.class);
					logWindow.windowBuild();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

}
