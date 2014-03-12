package org.provost.graphics2d.graphics;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.provost.graphics2d.Constants;
import org.provost.graphics2d.Engine;

public class MainWin extends JFrame {

	private static final long serialVersionUID = -6312412897254663113L;
	private static final Logger log = Logger.getLogger(MainWin.class);
	private static final MainWin instance = new MainWin();

	private MainPanel canvas = new MainPanel();
	// TODO future configuration options
	private int width = 640;
	private int height = 480;

	private MainWin() {
		super();
		init();
		log.info("MainWin created");
	}

	public static MainWin getInstance() {
		return instance;
	}

	public MainPanel getCanvas() {
		return canvas;
	}

	private void init() {
		// main window's settings
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle(Constants.APP_TITLE);
		setName(Constants.WIN_NAME);
		getContentPane().setPreferredSize(new Dimension(width, height));
		getContentPane().setLayout(null);

		canvas.setBounds(0, 0, width, height);
		getContentPane().add(canvas);
		canvas.setIgnoreRepaint(true);
		setResizable(false);

		addWindowListener(new java.awt.event.WindowListener() {
			@Override
			public void windowClosed(WindowEvent e) {
				log.debug("Closed.");
			}
			@Override
			public void windowClosing(WindowEvent e) {
				log.debug("Closing.");
				Engine.getInstance().end();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				log.debug("Activated.");
				Engine.getInstance().setPause(false);
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				log.debug("Deactivated.");
				Engine.getInstance().setPause(true);
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				log.debug("Deiconified.");
			}
			@Override
			public void windowIconified(WindowEvent e) {
				log.debug("Iconified.");
			}
			@Override
			// After displayed
			public void windowOpened(WindowEvent e) {
				log.debug("Opened.");
			}
		});
		
		initComponents();
		this.pack();
		log.debug("Initialized");
	}

	private void initComponents() {
	}

}
