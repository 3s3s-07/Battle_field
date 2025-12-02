package com.example.battle_graphics.base;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
public class Mage extends fighter {

    public Mage(double x, double y) {
        super("Mage", 150, x, y, 3, new Sword(), true, 0, Color.DARKRED);
        createShape();
    }

    @Override
    public void createShape() {
        Circle circle = new Circle(25);
        circle.setFill(getFighterColor());
        circle.setStroke(Color.BLACK);
        this.fighterShape = circle;

        this.fighterShape.setTranslateX(xPosition);
        this.fighterShape.setTranslateY(yPosition);
    }
}
