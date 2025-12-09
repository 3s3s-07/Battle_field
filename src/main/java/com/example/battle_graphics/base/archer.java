package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class archer extends Fighter {
    public archer(double x, double y) {
        super("Archer", 110, x, y, 4.0, new cannon(), true, 0, Color.FORESTGREEN,110);
    }
    @Override
    public void createShape() {
        Polygon poly = new Polygon(0, 50, 25, 0,50, 50);
        poly.setFill(getFighterColor());
        this.fighterShape = poly;
        this.fighterShape.setTranslateX(xPosition);
        this.fighterShape.setTranslateY(yPosition);
    }

    }
