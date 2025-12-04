package com.example.battle_graphics.base;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class GameManger {

    private Fighter player1;
    private Fighter player2;
    private Pane gamePane;
    private List<Projectile> projectiles;
    private InputHandler input;
    private AnimationTimer gameLoop;
    private final ProgressBar hp1;
    private final ProgressBar hp2;
    public GameManger(Fighter p1, Fighter p2, Pane pane, InputHandler handler, ProgressBar hp1, ProgressBar hp2) {
        this.player1 = p1;
        this.player2 = p2;
        this.gamePane = pane;
        this.input = handler;
        this.hp1 = hp1;
        this.hp2 = hp2;
        this.projectiles = new ArrayList<>();
        gamePane.getChildren().addAll(player1.getFighterShape(), player2.getFighterShape());
        startLoop();
    }
    private void startLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        gameLoop.start();
    }
    private void update() {
        input.handleMovement();
        updateProjectiles();
        checkCollisions();
        updateHealthBars();
        checkWinner();

    }
    private void updateProjectiles() {
        List<Projectile> toRemove = new ArrayList<>();
        for (Projectile p : projectiles) {
            p.update();
            if (!p.isActive() || p.isOutOfBounds()) {
                toRemove.add(p);
            }
        }
        for (Projectile p : toRemove) {
            gamePane.getChildren().remove(p.getShape());
            projectiles.remove(p);
        }
    }
    private void checkCollisions() {
        for (Projectile p : projectiles) {
            if (p.getOwner() == player1) {
                if (p.getShape().getBoundsInParent().intersects(player2.getFighterShape().getBoundsInParent())) {
                    player2.decreaseHealth(p.getDamage());
                    p.deactivate();
                }
            } else {
                if (p.getShape().getBoundsInParent().intersects(player1.getFighterShape().getBoundsInParent())) {
                    player1.decreaseHealth(p.getDamage());
                    p.deactivate();
                }
            }
        }
    }
    private void updateHealthBars() {
        hp1.setProgress((double)player1.getHealth() / 120.0);
        hp2.setProgress((double)player2.getHealth() / 120.0); // using 120 as top for simplicity
    }
    private void checkWinner() {
        if (!player1.isAlive()) {
            System.out.println("Player 2 Wins!");
            gameLoop.stop();
        }
        if (!player2.isAlive()) {
            System.out.println("Player 1 Wins!");
            gameLoop.stop();
        }
}
}
