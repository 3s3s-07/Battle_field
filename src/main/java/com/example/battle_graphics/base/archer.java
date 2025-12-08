package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class archer extends Fighter {
    public archer(double x, double y) {
        super("Archer", 110, x, y, 4.0, new cannon(), true, 0, Color.FORESTGREEN);
    }
    @Override
    public void createShape() {
        fighterShape = new Polygon(0, 50, 25, 0,50, 50);
        fighterShape.setFill(getFighterColor());
    }
    public void setPosition(double x, double y) {
        if (fighterShape != null) {
            fighterShape.setTranslateX(x);
            fighterShape.setTranslateY(y);
        }
    }
}