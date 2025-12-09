package com.example.battle_graphics.fx;

import com.example.battle_graphics.base.Fighter;
import com.example.battle_graphics.base.Projectile;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class InputHandler {

    private final Set<KeyCode> activeKeys = new HashSet<>();
    private final Fighter player1;
    private final Fighter player2;
    private GameManger gameController;

    public InputHandler(Fighter p1, Fighter p2, GameManger controller) {
        this.player1 = p1;
        this.player2 = p2;
        this.gameController = controller;
    }

    public void handleKeyPressed(KeyEvent event) {
        activeKeys.add(event.getCode());

        // Player 1 shoot (F) and switch weapon (Q)
        if (event.getCode() == KeyCode.F) shoot(player1);
        else if (event.getCode() == KeyCode.Q) player1.switchWeapon();

            // Player 2 shoot (L) and switch weapon (E)
        else if (event.getCode() == KeyCode.L) shoot(player2);
        else if (event.getCode() == KeyCode.E) player2.switchWeapon();
    }

    public void handleKeyReleased(KeyEvent event) {
        activeKeys.remove(event.getCode());
    }

    private void shoot(Fighter player) {
        if (gameController == null) return;
        Projectile p = player.shoot();
        if (p != null) {
            gameController.addProjectile(p);
        }
    }

    public void handleMovement() {
        if (gameController == null) return;

        double arenaWidth = gameController.getArenawidth();
        double arenaHeight = gameController.getArenaheight();

        // Player 1 movement (WASD)
        if (activeKeys.contains(KeyCode.W)) player1.move("UP", 0, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.S)) player1.move("DOWN", 0, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.A)) player1.move("LEFT", 0, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.D)) player1.move("RIGHT", 0, arenaWidth, 0, arenaHeight);

        // Player 2 movement (Arrow Keys)
        if (activeKeys.contains(KeyCode.UP)) player2.move("UP", 0, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.DOWN)) player2.move("DOWN", 0, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.LEFT)) player2.move("LEFT", 0, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.RIGHT)) player2.move("RIGHT", 0, arenaWidth, 0, arenaHeight);
    }

    public void setGameController(GameManger gm) {
        this.gameController = gm;
    }
}
