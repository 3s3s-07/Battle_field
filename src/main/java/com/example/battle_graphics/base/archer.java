package com.example.battle_graphics.base;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class archer extends Fighter {

    public archer(double x, double y) {
        super("Archer", 110, x, y, 4.0, true, 0, Color.FORESTGREEN, 110);
        addWeapon(new cannon());
        addWeapon(new Weapon("Arrow", 12, 7.0, 500L));
        addWeapon(new Weapon("Fire Arrow", 20, 6.0, 900L));
        addWeapon(new Weapon("Rapid Shot", 7, 10.0, 250L));
    }

    @Override
    public void createShape() {
        Polygon poly = new Polygon(0, 50, 25, 0, 50, 50);
        poly.setFill(getFighterColor());
        this.fighterShape = poly;
        this.fighterShape.setTranslateX(getX());
        this.fighterShape.setTranslateY(getY());
    }
}
