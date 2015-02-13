package org.provost.profile;

import org.apache.log4j.Logger;

public class FPSProfiler {

	private static final org.apache.log4j.Logger log = Logger.getLogger(FPSProfiler.class);

	private long sumTime = 0;
	private long numFrames = 0;
	private long startFrame = 0;

	public void startFrame(long time) {
		this.startFrame = time; 
	}

	public void endFrame(long time) {
		this.sumTime += (time - this.startFrame);
		this.numFrames++;
		this.startFrame = 0;
	}

	public long calculateOverallFps() {
		long ret = 0;
		try {
			long sec = (sumTime / 1000L);
			sec = (sec == 0 ? 1 : sec);
			ret = numFrames / sec;
		} catch(Exception x) {
			log.error("Calculation error", x);
		}
		return ret;
	}

	public long getNumFrames() {
		return this.numFrames;
	}

}
