package com.example.battle_graphics.fx;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

/**
 * Simple heart-shaped health bar. Call setProgress(0..1) to update the fill.
 * Use setDisplaySize(width, height) to scale the heart to the size you want.
 *
 * Implementation notes:
 * - The heart fill is implemented with a filled SVGPath whose clip is a Rectangle.
 * - The control scales from a base (BASE_W x BASE_H) coordinate system.
 */
public class HeartHealthBar extends StackPane {

    private final SVGPath outline;
    private final SVGPath fillShape;
    private final Rectangle clipRect;

    private static final double BASE_W = 60.0;
    private static final double BASE_H = 60.0;

    private double progress = 1.0; // 0..1

    public HeartHealthBar(Color fillColor) {
        // Heart path sized to roughly BASE_W x BASE_H.
        String heartPath =
                // a compact heart path in the 0..60 x 0..60 coordinate box
                "M30 52 L12 36 C4 28 4 18 12 12 C18 7 26 8 30 14 C34 8 42 7 48 12 C56 18 56 28 48 36 L30 52 Z";

        outline = new SVGPath();
        outline.setContent(heartPath);
        outline.setFill(Color.TRANSPARENT);
        outline.setStroke(Color.DARKRED);
        outline.setStrokeWidth(1.8);

        fillShape = new SVGPath();
        fillShape.setContent(heartPath);
        fillShape.setFill(fillColor);
        fillShape.setStroke(Color.TRANSPARENT);

        // clip rectangle; by default we clip left-to-right based on progress
        clipRect = new Rectangle(0, 0, BASE_W * progress, BASE_H);
        fillShape.setClip(clipRect);

        // Keep natural size, scale later via setDisplaySize
        this.getChildren().addAll(fillShape, outline);

        // ensure preferred size reflects base size so layout works predictably
        setPrefSize(BASE_W, BASE_H);
    }

    /**
     * Set progress 0..1. 1.0 = full heart, 0.0 = empty.
     * The clipping is left-to-right; if you'd prefer bottom-to-top change clipRect Y/height logic.
     */
    public void setProgress(double value) {
        this.progress = Math.max(0.0, Math.min(1.0, value));
        double clipW = BASE_W * this.progress;
        clipRect.setWidth(clipW);
        // clip anchored at left (0). If you want the right-to-left fill for a flipped heart, shift translateX accordingly.
    }

    /**
     * Convenience to set displayed size. This scales the control from the base coordinate system.
     */
    public void setDisplaySize(double width, double height) {
        double sx = width / BASE_W;
        double sy = height / BASE_H;
        setScaleX(sx);
        setScaleY(sy);

        // Update pref size so parent layouts (if any) see expected extents
        setPrefSize(width, height);
    }

    /**
     * Return current progress (0..1).
     */
    public double getProgress() {
        return progress;
    }
}