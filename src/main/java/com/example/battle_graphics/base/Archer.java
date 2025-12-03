package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class Archer extends Fighter {
    public Archer(double x, double y, int side) {
        // الوراثة: 100 HP، السلاح: LongBow، اللون: أخضر، الجانب: side
        super(100, x, y, new cannon(), Color.GREEN, side);
        setMovementSpeed(5.0);
        createShape();
    }
    @Override
    public void createShape() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                0.0, 60.0, 40.0, 60.0, 20.0, 0.0
        });
        triangle.setFill(getFighterColor());
        triangle.setStroke(Color.BLACK);
        this.fighterShape = triangle;
        this.fighterShape.setTranslateX(xPosition);
        this.fighterShape.setTranslateY(yPosition);
    }
    @Override
    public void specialAbility() { /* ... */ }
    }

