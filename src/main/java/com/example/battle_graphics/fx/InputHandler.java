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
    // قائمة المقذوفات: يجب أن يتم تمريرها من GameController
    private final GameController gameController;

    public InputHandler(Fighter p1, Fighter p2, GameController controller) {
        this.player1 = p1;
        this.player2 = p2;
        this.gameController = controller;
    }

    public void handleKeyPressed(KeyEvent event) {
        activeKeys.add(event.getCode());

        // معالجة الإطلاق (Shooting)
        if (event.getCode() == KeyCode.F) {
            Projectile p = player1.shoot();
            if (p != null) gameController.addProjectile(p);
        } else if (event.getCode() == KeyCode.L) {
            Projectile p = player2.shoot();
            if (p != null) gameController.addProjectile(p);
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        activeKeys.remove(event.getCode());
    }

    // تُستدعى في حلقة اللعبة لتطبيق الحركة المستمرة
    public void handleMovement() {
        double arenaWidth = gameController.getArenaWidth();
        double arenaHeight = gameController.getArenaHeight();
        double halfLineX = arenaWidth / 2;

        // حدود اللاعب 1 (اليسار)
        double p1MaxX = halfLineX;
        // حدود اللاعب 2 (اليمين)
        double p2MinX = halfLineX;

        // --- حركة اللاعب 1 (W, A, S, D) ---
        if (activeKeys.contains(KeyCode.W)) player1.move("UP", 0, p1MaxX, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.S)) player1.move("DOWN", 0, p1MaxX, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.A)) player1.move("LEFT", 0, p1MaxX, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.D)) player1.move("RIGHT", 0, p1MaxX, 0, arenaHeight);

        // --- حركة اللاعب 2 (Up, Down, Left, Right) ---
        if (activeKeys.contains(KeyCode.UP)) player2.move("UP", p2MinX, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.DOWN)) player2.move("DOWN", p2MinX, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.LEFT)) player2.move("LEFT", p2MinX, arenaWidth, 0, arenaHeight);
        if (activeKeys.contains(KeyCode.RIGHT)) player2.move("RIGHT", p2MinX, arenaWidth, 0, arenaHeight);
    }
}