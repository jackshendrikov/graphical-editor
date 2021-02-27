package ua.jackshen.composite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Ornament implements GraphicShape {

    public void drawShape(GraphicsContext g) {
        double width = g.getCanvas().getWidth();
        double height = g.getCanvas().getHeight();

        Random random = new Random(System.currentTimeMillis());

        g.setFill(Color.rgb(random.nextInt(255), random.nextInt(255),
                random.nextInt(255), 0.9));
        g.translate(width / 2, height / 2);

        for (int i = 0; i < 60; i++) {
            g.rotate(6.0);
            g.setFill(Color.rgb(random.nextInt(255), random.nextInt(255),
                    random.nextInt(255), 0.9));
            g.fillOval(10, 60, 30, 30);
            g.strokeOval(60, 60, 30, 30);
            g.setFill(Color.rgb(random.nextInt(255), random.nextInt(255),
                    random.nextInt(255), 0.9));
            g.fillRoundRect(110, 60, 30, 30, 10, 10);
            g.setFill(Color.rgb(random.nextInt(255), random.nextInt(255),
                    random.nextInt(255), 0.9));
            g.fillPolygon(
                    new double[] { 105, 117, 159, 123, 133, 105, 77, 87,51, 93 },
                    new double[] { 150, 186, 186, 204, 246, 222, 246,204, 186, 186 }, 10);

        }

        g.translate(-width/2, -height/2);
    }
}
