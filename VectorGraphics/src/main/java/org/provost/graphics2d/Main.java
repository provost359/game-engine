package org.provost.graphics2d;

import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.provost.tools.Graphics;

/**
 * App entry point.
 */
public class Main 
{

	private static final org.apache.log4j.Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args)
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
				Engine eng = null; 
				try {
					eng = Engine.getInstance();
					eng.start();
					
					/*
					synchronized(Thread.currentThread()) {
						Thread.currentThread().wait(5000L);
						throw new NullPointerException("Simulated error");
					}
					*/
				} catch(Exception x) {
					log.error("Problem making visible, closing", x);
					if(eng != null) {
						eng.end();
					}
				}
			}
		});
	}
}
