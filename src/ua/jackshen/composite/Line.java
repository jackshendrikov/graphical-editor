package ua.jackshen.composite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line implements GraphicShape {
    private int posX, posY, lineLength, strokeWidth;
    private Color color;

    public Line(int posX, int posY, int lineLength, int strokeWidth, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.lineLength = lineLength;
        this.strokeWidth = strokeWidth;
        this.color = color;
    }

    public void drawShape(GraphicsContext g) {
        g.setLineWidth(strokeWidth);
        g.setStroke(color);
        g.strokeLine(posX, posY, lineLength, strokeWidth);
    }
}
