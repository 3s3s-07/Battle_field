package com.example.battle_graphics.base;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {

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
    }}
