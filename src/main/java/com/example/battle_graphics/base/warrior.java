package com.example.battle_graphics.base;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class warrior extends Fighter {

    public warrior(double x, double y) {
        super("Warrior", 150, x, y, 3, true, 0, Color.DARKRED, 150);
        addWeapon(new Pistol());
        addWeapon(new Weapon("Sword Slash", 15, 5.0, 700L));
        addWeapon(new Weapon("Heavy Axe", 30, 3.0, 1500L));
    }

    @Override
    public void createShape() {
        Rectangle r = new Rectangle(60, 80);
        r.setFill(getFighterColor());
        r.setStroke(Color.DARKRED);
        this.fighterShape = r;
        this.fighterShape.setTranslateX(getX());
        this.fighterShape.setTranslateY(getY());
    }
}
