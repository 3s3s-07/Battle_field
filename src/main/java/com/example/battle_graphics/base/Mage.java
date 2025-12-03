package com.example.battle_graphics.base;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
public class Mage extends Fighter {
    public Mage(double x, double y, int side) {
        // الوراثة: 90 HP، السلاح: FireballStaff، اللون: أرجواني، الجانب: side
        super(90, x, y, new MagicWand(), Color.PURPLE, side);
        setMovementSpeed(4.5);
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
    @Override
    public void specialAbility() { /* ... */ }
}
