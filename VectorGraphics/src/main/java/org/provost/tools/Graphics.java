package org.provost.tools;

import java.awt.BufferCapabilities;
import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.ImageCapabilities;

public abstract class Graphics {
	
	private static GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

	public static GraphicsDevice[] getSystemInformation() {
		return graphicsEnvironment.getScreenDevices();
	}

	public static String printAllDevicesInformation(String delimiter, boolean showModes) {
		StringBuilder ret = new StringBuilder();
		String delimiterLocal = (delimiter == null ? "" : delimiter);
		GraphicsDevice[] gds = getSystemInformation();
		for(int i = 0; i < gds.length; i++) {
			GraphicsDevice gd = gds[i];
			ret.append(printDeviceInformation(gd, delimiter));

			// buffer information
			ret.append(delimiterLocal);
			GraphicsConfiguration[] gcs = gd.getConfigurations();
			for(int k = 0; k < gcs.length; k++) {
				GraphicsConfiguration gc = gcs[k];
				ret.append(printBufferInformation(gc, delimiter));
				if((k + 1) < gcs.length) {
					ret.append(delimiterLocal);
				}
			}

			if((i + 1) < gds.length || showModes) {
				ret.append(delimiterLocal);
			}
			
			if(showModes) {
				ret.append("Display modes");
				ret.append(delimiterLocal);
				DisplayMode[] modes = gd.getDisplayModes();
				for(int j = 0; j < modes.length; j++) {
					DisplayMode mode = modes[j];
					ret.append(printDisplayModeInformation(mode, delimiter));
					if((i + 1) == gds.length && (j + 1) == modes.length) {
						// do nothing
					} else {
						ret.append(delimiterLocal);
					}
				}
			}
		}
		return ret.toString();
	}

	private static String printBufferInformation(GraphicsConfiguration gc, String delimiter) {
		StringBuilder ret = new StringBuilder();
		String delimiterLocal = (delimiter == null ? "" : delimiter);
        ret.append("Graphics configuration: ");
        ret.append(gc.toString());
        ret.append(delimiterLocal);
        BufferCapabilities bufferCapabilities = gc.getBufferCapabilities();
        ret.append("Buffers configuration");
        ret.append(delimiterLocal);
        ret.append("Multi buffer available: ");
        ret.append(bufferCapabilities.isMultiBufferAvailable());
        ret.append(delimiterLocal);
        ret.append("Page flipping supported: ");
        ret.append(bufferCapabilities.isPageFlipping());
        ret.append(delimiterLocal);
        ret.append("Full screen required: ");
        ret.append(bufferCapabilities.isFullScreenRequired());
        ret.append(delimiterLocal);
        // moznosti predniho bufferu
        ImageCapabilities frontBuffer = bufferCapabilities.getFrontBufferCapabilities();
        // moznosti zadniho bufferu
        ImageCapabilities backBuffer = bufferCapabilities.getBackBufferCapabilities();
        ret.append("Front buffer accelerated: ");
        ret.append(frontBuffer.isAccelerated());
        ret.append(delimiterLocal);
        ret.append("Front buffer volatile: ");
        ret.append(frontBuffer.isTrueVolatile());
        ret.append(delimiterLocal);
        ret.append("Back buffer accelerated: ");
        ret.append(backBuffer.isAccelerated());
        ret.append(delimiterLocal);
        ret.append("Back buffer volatile: ");
        ret.append(backBuffer.isTrueVolatile());
        return ret.toString();
	}

	public static String printDisplayModeInformation(DisplayMode mode, String delimiter) {
		StringBuilder ret = new StringBuilder();
		String delimiterLocal = (delimiter == null ? "" : delimiter);
		ret.append("Width: ");
		ret.append(mode.getWidth());
		ret.append(delimiterLocal);
		ret.append("Height: ");
		ret.append(mode.getHeight());
		ret.append(delimiterLocal);
		ret.append("Bit depth: ");
		ret.append(mode.getBitDepth());
		ret.append(delimiterLocal);
		ret.append("Refresh rate: ");
		ret.append(mode.getRefreshRate());
		return ret.toString();
	}

	public static String printDeviceInformation(GraphicsDevice gd, String delimiter) {
		StringBuilder ret = new StringBuilder();
		String delimiterLocal = (delimiter == null ? "" : delimiter);
		ret.append("Graphic device ID: ");
		ret.append(gd.getIDstring());
		ret.append(delimiterLocal);
		ret.append("Memory: ");
		ret.append(gd.getAvailableAcceleratedMemory());
		ret.append(delimiterLocal);
		ret.append("Graphics change supported: ");
		ret.append(gd.isDisplayChangeSupported());
		ret.append(delimiterLocal);
		ret.append("Full screen supported: ");
		ret.append(gd.isFullScreenSupported());
		ret.append(delimiterLocal);
		ret.append("Graphic device type: ");
		ret.append(gd.getType());
		return ret.toString();
	}

}
