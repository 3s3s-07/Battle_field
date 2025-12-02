package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class warrior extends fighter {
    public warrior() {}
    public warrior(double x,double y){
            super("Warrior", 150, x, y, 3, new Sword(), true, 0, Color.DARKRED);
        }
        @Override
    public void createShape() {
        fighterShape = new Rectangle(60, 80);
        fighterShape.setFill(getFighterColor());
        fighterShape.setStroke(Color.DARKRED);
    }

    public void setPosition(double x, double y) {
        if (this.fighterShape != null) {
            this.fighterShape.setTranslateX(x);
            this.fighterShape.setTranslateY(y);
        }
    }
}


