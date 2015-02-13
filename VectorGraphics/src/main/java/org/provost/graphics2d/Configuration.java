package org.provost.graphics2d;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {

	private static final Logger log = Logger.getLogger(Configuration.class);

	private Properties properties = null;
	
	public Configuration() {
		this.properties = new Properties();
		load();
	}

	public void save() {
		OutputStream os = null;
		try {
			os = new BufferedOutputStream(new FileOutputStream(Constants.PROP_FILE));
			this.properties.storeToXML(os, "");
		} catch (Exception e) {
			log.error("Problem storing configuration", e);
		} finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					log.error("Problem closing property stream", e);
				}
			}
		}
	}

	private void load() {
		boolean goDefault = false;
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(Constants.PROP_FILE), 4096);
			this.properties.loadFromXML(is);
		} catch (Exception e) {
			log.error("Failed loading configuration", e);
			goDefault = true;
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {}
			}
		}
		if(goDefault) {
			log.debug("Using default configuration values");
			this.properties.putAll(Props.getDefaults());
		}
	}

	public String getProperty(String name) {
		return this.properties.getProperty(name);
	}

}
