package org.provost.graphics2d.graphics.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import org.apache.log4j.Logger;
import org.provost.graphics2d.graphics.BasicVector;

public class Triangle implements BasicVector {

	private Coordinates vertex1 = null;
	private Coordinates vertex2 = null;
	private Coordinates vertex3 = null;
	private Color color = null;
	private Polygon triangle = null;
	private int rotateDegrees = 0;

	public Triangle(Coordinates vertex1, Coordinates vertex2,Coordinates vertex3, Color color) {
		super();
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.vertex3 = vertex3;
		this.color = color;
		this.triangle = new Polygon(new int[] { this.vertex1.getX(), this.vertex2.getX(), this.vertex3.getX() }, 
				new int[] { this.vertex1.getY(), this.vertex2.getY(), this.vertex3.getY() }, 3);
		Logger.getLogger(this.getClass()).debug("Triangle, vertex1: " + vertex1 + ", vertex2: " + vertex2 + ", vertex3: " + vertex3 + ", color: " + color);
	}

	public Coordinates getVertex1() {
		return vertex1;
	}

	public void setVertex1(Coordinates vertex1) {
		this.vertex1 = vertex1;
	}

	public Coordinates getVertex2() {
		return vertex2;
	}

	public void setVertex2(Coordinates vertex2) {
		this.vertex2 = vertex2;
	}

	public Coordinates getVertex3() {
		return vertex3;
	}

	public void setVertex3(Coordinates vertex3) {
		this.vertex3 = vertex3;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void paint(Graphics2D g) {
		paint(g, this.color);
	}

	@Override
	public void paint(Graphics2D g, Color color) {
		Logger.getLogger(this.getClass()).debug("Painting coordinates 1: " + vertex1 + "; 2: " + vertex2 + "; 3: " + vertex3);
		Color orig = g.getColor();
		AffineTransform saved = g.getTransform();
		AffineTransform rotationAT = saved;
		
		// calculate and apply rotation
		if(rotateDegrees > 0) {
			rotationAT = new AffineTransform();
			double middleX = vertex1.getX();//50;
			if(vertex1.getX() < vertex2.getX()) {
				middleX = vertex1.getX() + ((vertex2.getX() - vertex1.getX()) / 2D);
			} else if(vertex1.getX() > vertex2.getX()) {
				middleX = vertex2.getX() + ((vertex1.getX() - vertex2.getX()) / 2D);
			}
			double middleY = vertex1.getY();//256;
			if(vertex1.getY() < vertex2.getY()) {
				middleY = vertex1.getY() + ((vertex2.getY() - vertex1.getY()) / 2D);
			} else if(vertex1.getY() > vertex2.getY()) {
				middleY = vertex2.getY() + ((vertex1.getY() - vertex2.getY()) / 2D);
			}
			Logger.getLogger(this.getClass()).debug("Rotation center: " + middleX + ", " + middleY);
			Logger.getLogger(this.getClass()).debug("Rotate by: " + rotateDegrees + " degrees, " + Math.toRadians(rotateDegrees) + " radians");
			rotationAT.rotate(Math.toRadians(rotateDegrees), middleX, middleY);
		}
		g.setColor(color);

		// TODO move to the last position plus new position calculated by speed and vector of the triangle
		Shape transformedTriangle = rotationAT.createTransformedShape(triangle);
		g.draw(transformedTriangle);

		g.setColor(orig);
		g.setTransform(saved);
		Logger.getLogger(this.getClass()).debug("Painting done");
	}

	@Override
	public void move(int speedPixel) {
		// TODO adjust to the new logic, i.e. store (new position / something) depending on transformation requirements
		
	}

	@Override
	public void rotate(int degrees) {
		this.rotateDegrees += degrees;
		long mult = this.rotateDegrees / 360;
		this.rotateDegrees -= (360 * mult);
	}

}
