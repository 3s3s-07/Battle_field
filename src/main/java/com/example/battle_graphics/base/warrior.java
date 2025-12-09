package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class warrior extends Fighter {

    public warrior(double x,double y){
        super("Warrior", 150, x, y, 3, new Pistol(), true, 0, Color.DARKRED,150);
    }
    @Override
    public void createShape() {
        Rectangle r = new Rectangle(60, 80);
        r.setFill(getFighterColor());
        r.setStroke(Color.DARKRED);
        this.fighterShape = r;
        this.fighterShape.setTranslateX(xPosition);
        this.fighterShape.setTranslateY(yPosition);
    }


    }
