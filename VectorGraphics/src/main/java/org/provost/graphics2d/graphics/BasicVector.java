package org.provost.graphics2d.graphics;

import java.awt.Color;
import java.awt.Graphics2D;

public interface BasicVector {

	public void paint(Graphics2D g);
	public void paint(Graphics2D g, Color color);
	public void move(int speedPixel);
	public void rotate(int degrees);

}
