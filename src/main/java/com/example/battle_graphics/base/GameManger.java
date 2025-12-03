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
        gamePane.getChildren().addAll(player1.getSprite(), player2.getSprite());
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
        handleMovement();
        handleShooting();
        updateProjectiles();
        checkCollisions();
        checkWinner();
    }
    private void handleMovement() {
        if (input.isWPressed()) player1.moveUp();
        if (input.isSPressed()) player1.moveDown();
        if (input.isAPressed()) player1.moveLeft();
        if (input.isDPressed()) player1.moveRight();
        if (input.isUpPressed()) player2.moveUp();
        if (input.isDownPressed()) player2.moveDown();
        if (input.isLeftPressed()) player2.moveLeft();
        if (input.isRightPressed()) player2.moveRight();
    }
    private void handleShooting() {
        if (input.isFPressed() && TimerUtil.canShoot(player1.lastShotTime, player1.getWeapon().getCooldown())) {
            Projectile p = player1.getWeapon().createProjectile(player1);
            projectiles.add(p);
            gamePane.getChildren().add(p.getShape());
            player1.lastShotTime = System.currentTimeMillis();
        }
        if (input.isLPressed() && TimerUtil.canShoot(player2.lastShotTime, player2.getWeapon().getCooldown())) {
            Projectile p = player2.getWeapon().createProjectile(player2);
            projectiles.add(p);
            gamePane.getChildren().add(p.getShape());
            player2.lastShotTime = System.currentTimeMillis();
        }
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
                if (p.getShape().getBoundsInParent().intersects(player2.getSprite().getBoundsInParent())) {
                    player2.takeDamage(p.getDamage());
                    p.deactivate();
                }
            } else {
                if (p.getShape().getBoundsInParent().intersects(player1.getSprite().getBoundsInParent())) {
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
