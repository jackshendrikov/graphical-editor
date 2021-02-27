package ua.jackshen.composite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements GraphicShape {
    private int posX, posY, circleRadius, strokeWidth;
    private Color fillColor, strokeColor;

    Circle(int posX, int posY, int circleRadius, int strokeWidth, Color fillColor, Color strokeColor) {
        this.posX = posX;
        this.posY = posY;
        this.circleRadius = circleRadius;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public void drawShape(GraphicsContext g) {
        g.setLineWidth(strokeWidth);
        g.setStroke(strokeColor);
        g.setFill(fillColor);
        g.fillOval(posX, posY, circleRadius, circleRadius);
        g.strokeOval(posX, posY, circleRadius, circleRadius);
    }
}
