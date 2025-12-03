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

    public GameEngine(Fighter p1, Fighter p2, Pane pane, InputHandler handler) {
        this.player1 = p1;
        this.player2 = p2;
        this.gamePane = pane;
        this.input = handler;

        this.projectiles = new ArrayList<>();

        // Add player sprites to pane
        gamePane.getChildren().addAll(player1.getSprite(), player2.getSprite());

        startLoop();   // start the game loop
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

    /** Called every frame */
    private void update() {
        handleMovement();
        handleShooting();
        updateProjectiles();
        checkCollisions();
        checkWinner();
    }

    /** Moves both players according to key presses */
    private void handleMovement() {

        // Player 1 movement (WASD)
        if (input.isWPressed()) player1.moveUp();
        if (input.isSPressed()) player1.moveDown();
        if (input.isAPressed()) player1.moveLeft();
        if (input.isDPressed()) player1.moveRight();

        // Player 2 movement (Arrow Keys)
        if (input.isUpPressed()) player2.moveUp();
        if (input.isDownPressed()) player2.moveDown();
        if (input.isLeftPressed()) player2.moveLeft();
        if (input.isRightPressed()) player2.moveRight();
    }
}
