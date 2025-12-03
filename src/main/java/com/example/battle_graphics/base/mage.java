package com.example.battle_graphics.base;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
class mage extends Fighter {

    public mage(double x, double y) {
        super("Mage", 90, x, y, 4.5, new MagicWand(), true, 0, Color.PURPLE);
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
