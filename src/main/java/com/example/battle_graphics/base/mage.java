package com.example.battle_graphics.base;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
public class mage extends Fighter {

    public mage(double x, double y,int facingRight) {
        super("Mage", 90, x, y, new MagicWand(),4.5 , Color.BLUE, facingRight);
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
