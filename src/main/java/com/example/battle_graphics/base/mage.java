package com.example.battle_graphics.base;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class mage extends Fighter {

    public mage(double x, double y) {
        super("Mage", 90, x, y, 4.5, true, 0, Color.PURPLE, 90);
        addWeapon(new MagicWand());
        addWeapon(new Weapon("Magic Bolt", 10, 8.0, 450L));
        addWeapon(new Weapon("Fireball", 25, 5.0, 1200L));
    }

    @Override
    public void createShape() {
        Circle circle = new Circle(25);
        circle.setFill(getFighterColor());
        circle.setStroke(Color.BLACK);
        this.fighterShape = circle;
        this.fighterShape.setTranslateX(getX());
        this.fighterShape.setTranslateY(getY());
    }
}
