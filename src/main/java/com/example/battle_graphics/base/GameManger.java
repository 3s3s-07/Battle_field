package com.example.battle_graphics.base;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class GameManger {

    private Fighter player1;
    private Fighter player2;
    private Pane gamePane;
    private List<Projectile> projectiles;
    private InputHandler input;
    private AnimationTimer gameLoop;
    public GameManger(Fighter p1, Fighter p2, Pane pane, InputHandler handler) {
        this.player1 = p1;
        this.player2 = p2;
        this.gamePane = pane;
        this.input = handler;
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
                    player2.takeDamage(p.getDamage());
                    p.deactivate();
                }
            } else {
                if (p.getShape().getBoundsInParent().intersects(player1.getFighterShape().getBoundsInParent())) {
                    player1.takeDamage(p.getDamage());
                    p.deactivate();
                }
            }
        }
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
