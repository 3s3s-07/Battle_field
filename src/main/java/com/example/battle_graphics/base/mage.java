package com.example.battle_graphics.base;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
public class Mage extends fighter {

    public Mage(double x, double y) {
        // الوراثة: 90 HP، السلاح: FireballStaff، اللون: أرجواني
        super(90, x, y, new FireballStaff(), Color.PURPLE);
        setSpeed(4.5);
        createShape(); // إنشاء الشكل الخاص فوراً
    }

    // تطبيق إنشاء الشكل: دائرة (Circle)
    @Override
    public void createShape() {
        Circle circle = new Circle(25);
        circle.setFill(getFighterColor());
        circle.setStroke(Color.BLACK);
        this.fighterShape = circle;
        // تعيين الموقع الأولي لشكل JavaFX
        this.fighterShape.setTranslateX(xPosition);
        this.fighterShape.setTranslateY(yPosition);
    }
}
