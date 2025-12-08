package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class archer extends Fighter {
    public archer(double x, double y,int facingright) {
        super("Archer", 100, x, y, new cannon(),4.0 , Color.PURPLE, facingright);
    createShape();
    }
    @Override
    public void createShape() {
        fighterShape = new Polygon(0, 50, 25, 0,50, 50);
        fighterShape.setFill(getFighterColor());
        fighterShape.setStroke(Color.BLACK);
        this.fighterShape.setTranslateX(xPosition);
        this.fighterShape.setTranslateY(yPosition);
    }
}