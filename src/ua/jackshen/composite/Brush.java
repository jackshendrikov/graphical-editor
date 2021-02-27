package ua.jackshen.composite;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Brush implements GraphicShape {
    private int strokeWidth;
    private Color strokeColor;
    private Canvas area;

    Brush(Canvas area, int strokeWidth, Color strokeColor) {
        this.area = area;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public void drawShape(GraphicsContext g) {
        area.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    g.beginPath();
                    g.moveTo(event.getX(), event.getY());
                    g.stroke();
                });

        area.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {
                    g.lineTo(event.getX(), event.getY());
                    g.stroke();
                });

        g.setLineWidth(strokeWidth);
        g.setStroke(strokeColor);
    }
}