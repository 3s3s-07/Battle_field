package com.example.battle_graphics.base;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class warrior extends Fighter {

    public warrior(double x, double y) {
        super("Warrior", 150, x, y, 3, true, 0, Color.DARKRED, 150);
        addWeapon(new Pistol());
        addWeapon(new Weapon("Sword Slash", 15, 5.0, 700L));
        addWeapon(new Weapon("Heavy Axe", 30, 3.0, 1500L));
    }

    @Override
    public void createShape() {
        Shape s = SoldierShapeFactory.createSoldier(getFighterColor(), xPosition, yPosition);
        this.fighterShape = s;
        fighterShape.setScaleX(3.5);
        fighterShape.setScaleY(2.5);
    }
}
