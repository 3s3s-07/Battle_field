package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class warrior extends Fighter {

    public warrior(double x,double y,int facingRight){
            super("Warrior", 150, x, y, new Pistol(),3 , Color.PINK, facingRight);
        }
        @Override
    public void createShape() {
        fighterShape = new Rectangle(60, 80);
        fighterShape.setFill(getFighterColor());
        fighterShape.setStroke(Color.DARKRED);
            this.fighterShape.setTranslateX(xPosition);
            this.fighterShape.setTranslateY(yPosition);
    }

}


