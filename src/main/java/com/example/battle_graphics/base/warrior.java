package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class warrior extends Fighter {
    public warrior(double x, double y, int side) {
        // الوراثة: 120 HP، السلاح: BasicSword، اللون: أزرق، الجانب: side
        super(120, x, y, new Pistol(), Color.BLUE, side);
        setMovementSpeed(3.5);
        createShape();
    }
    @Override
    public void createShape() {
        Rectangle rect = new Rectangle(40, 60);
        rect.setFill(getFighterColor());
        rect.setStroke(Color.BLACK);
        this.fighterShape = rect;
        this.fighterShape.setTranslateX(xPosition);
        this.fighterShape.setTranslateY(yPosition);
    }
    @Override
    public void specialAbility() { /* ... */ }
}


