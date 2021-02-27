package ua.jackshen.composite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle implements GraphicShape {

	private int posX, posY, posZ, strokeWidth;
	private Color fillColor, strokeColor;

	Triangle(int posX, int posY, int posZ, int strokeWidth, Color fillColor, Color strokeColor) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.fillColor = fillColor;
		this.strokeColor = strokeColor;
		this.strokeWidth = strokeWidth;
	}

	public void drawShape(GraphicsContext g) {
		g.setLineWidth(strokeWidth);
		g.setStroke(strokeColor);
		g.setFill(fillColor);
		g.fillPolygon(
				new double[]{posX, posY, posZ},
				new double[]{posX, posZ, posY}, 3);
		g.strokePolygon(
				new double[]{posX, posY, posZ},
				new double[]{posX, posZ, posY}, 3);
	}
}
