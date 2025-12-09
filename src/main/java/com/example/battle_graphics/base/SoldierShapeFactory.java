package com.example.battle_graphics.base;

import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 * Small helper that returns a single Shape (SVGPath) that looks like a simple soldier.
 * SVGPath is a Shape so it plugs directly into Fighter.fighterShape without other changes.
 *
 * You can tweak the SVG path, color, stroke, or scale here to change the soldier look globally.
 */
public final class SoldierShapeFactory {
    private SoldierShapeFactory() {}

    /**
     * Create a soldier silhouette positioned at (x, y).
     * The returned Shape already has translateX/Y set to the given x/y so callers don't need to set them.
     *
     * @param color fill color for the soldier
     * @param x     translateX (starting X)
     * @param y     translateY (starting Y)
     * @return a Shape (SVGPath) representing a soldier
     */
    public static Shape createSoldier(Color color, double x, double y) {
        SVGPath s = new SVGPath();

        // Simple humanoid silhouette. Adjust this path for a different look.
        // This path contains head, torso and legs in a single SVG path so it's a single Shape.
        s.setContent(
                "M10 2 " +                 // head start
                        "C12 2 14 4 14 6 " +
                        "C14 8 12 10 10 10 " +
                        "C8 10 6 8 6 6 " +
                        "C6 4 8 2 10 2 Z " +      // head closed
                        "M3 12 L17 12 L17 22 L13 22 L13 30 L11 30 L11 22 L7 22 L7 30 L5 30 L5 22 L3 22 Z" // torso+arms+legs block
        );

        s.setFill(color);
        s.setStroke(Color.BLACK);
        s.setTranslateX(x);
        s.setTranslateY(y);

        return s;
    }
}