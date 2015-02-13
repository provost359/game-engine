package org.provost.graphics2d.graphics;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.provost.graphics2d.Constants;
import org.provost.graphics2d.Engine;
import org.provost.graphics2d.Props;

public class MainWin {

	private static final Logger log = Logger.getLogger(MainWin.class);
	private static final MainWin instance = new MainWin();

	private JFrame frame = new JFrame();
	private MainPanel canvas = new MainPanel();

	private MainWin() {
		super();
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
		this.frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.frame.setTitle(Constants.APP_TITLE);
		this.frame.setName(Constants.WIN_NAME);
		int width = Integer.parseInt(Engine.getInstance().getConfig().getProperty(Props.Width.getName()));
		int height = Integer.parseInt(Engine.getInstance().getConfig().getProperty(Props.Height.getName()));
		this.frame.getContentPane().setPreferredSize(new Dimension(width, height));
		this.frame.getContentPane().setLayout(null);

		canvas.setBounds(0, 0, width, height);
		this.frame.getContentPane().add(canvas);
		canvas.setIgnoreRepaint(true);
		this.frame.setResizable(false);

		this.frame.addWindowListener(new java.awt.event.WindowListener() {
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
		this.frame.pack();
		log.debug("Initialized");
	}

	private void initComponents() {
	}

	public void end() {
		this.frame.dispose();
	}

	public void show() {
		log.debug("Making MainWin visible");
		init();
		this.frame.setVisible(true);
		this.canvas.createBufferStrategy(3);
	}

	public int getHeight() {
		return this.frame.getPreferredSize().height;
	}

	public int getWidth() {
		return this.frame.getPreferredSize().width;
	}
}
