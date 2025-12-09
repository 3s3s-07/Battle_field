package com.example.battle_graphics.base;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class mage extends Fighter {

    public mage(double x, double y) {
        super("Mage", 90, x, y, 4.5, true, 0, Color.PURPLE, 90);
        addWeapon(new MagicWand());
        addWeapon(new Weapon("Magic Bolt", 10, 8.0, 450L));
        addWeapon(new Weapon("Fireball", 25, 5.0, 1200L));
    }

    @Override
    public void createShape() {
        Shape s = SoldierShapeFactory.createSoldier(getFighterColor(), xPosition, yPosition);
        this.fighterShape = s;
    }
}
