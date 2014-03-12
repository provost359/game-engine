package org.provost.profile;

public class FPSProfiler {

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

	public long calculateFps() {
		return numFrames / (sumTime / 1000L);
	}

	public long getNumFrames() {
		return this.numFrames;
	}

}
