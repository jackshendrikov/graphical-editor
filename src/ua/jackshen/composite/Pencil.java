package ua.jackshen.composite;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Pencil implements GraphicShape {
    private int strokeWidth;
    private Color fillColor;
    private Canvas area;

    Pencil(Canvas area, int strokeWidth, Color fillColor) {
        this.area = area;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
    }

    public void drawShape(GraphicsContext g) {
        EventHandler<MouseEvent> mouseHandlerIII = mouseEvent2 -> {
            g.setFill(fillColor);
            g.fillOval(mouseEvent2.getX() - strokeWidth / 2, mouseEvent2.getY() - strokeWidth / 2, strokeWidth, strokeWidth);
        };

        area.setOnMouseDragged(mouseHandlerIII);
    }
}