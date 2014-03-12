package org.provost.graphics2d;

import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.provost.graphics2d.graphics.MainWin;
import org.provost.tools.Graphics;

/**
 * App entry point.
 */
public class Main 
{

	private static final org.apache.log4j.Logger log = Logger.getLogger(Main.class);

	public static void main( String[] args )
	{
		log.info("Graphics device information: " + Graphics.printAllDevicesInformation("\n", true));
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (Exception x) {
			log.error("Problem setting look&feel", x);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				log.debug("Creating MainWin instance");
				MainWin mainWin = MainWin.getInstance();
				log.debug("Making MainWin visible");
				try {
					mainWin.setVisible(true);
					MainWin.getInstance().getCanvas().createBufferStrategy(2);
					Engine eng = Engine.getInstance();
					eng.start();
					
				} catch(Exception x) {
					log.error("Problem making visible", x);
				}
			}
		});
	}
}
