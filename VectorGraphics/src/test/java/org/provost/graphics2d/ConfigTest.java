package org.provost.graphics2d;

import org.apache.log4j.Logger;

public class ConfigTest {

	public static void main(String[] args) throws Exception {
		Logger.getLogger(ConfigTest.class).debug("Working directory: " + System.getProperty("user.dir"));
		Configuration conf = new Configuration();
		Logger.getLogger(ConfigTest.class).debug("Width: " + conf.getProperty(Props.Width.getName()));
	}

}
