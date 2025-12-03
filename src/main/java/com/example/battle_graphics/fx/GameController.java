package com.example.battle_graphics.fx;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import com.example.battle_graphics.base.Fighter;
import com.example.battle_graphics.base.Projectile;
public class GameController {
    private final double arenaWidth;
    private final double arenaHeight;
    private final Pane gameArena;

    private final Fighter player1;
    private final Fighter player2;
    private final InputHandler inputHandler;

    private final List<Projectile> activeProjectiles = new ArrayList<>();
    private final AnimationTimer gameLoop;

    private final ProgressBar healthBar1;
    private final ProgressBar healthBar2;

    public GameController(double width, double height, Fighter p1, Fighter p2) {
        this.arenaWidth = width;
        this.arenaHeight = height;
        this.player1 = p1;
        this.player2 = p2;
        this.gameArena = new Pane();
        this.gameArena.setPrefSize(width, height);
        this.inputHandler = new InputHandler(p1, p2, this);

        // إعداد واجهة المستخدم (UI Setup)
        this.healthBar1 = createHealthBar(p1, 10, 10);
        this.healthBar2 = createHealthBar(p2, width - 210, 10); // 200 عرض شريط الصحة

        setupArena();

        // إعداد حلقة اللعبة
        this.gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame(now);
            }
        };
    }

    // إعداد مكونات JavaFX في الساحة
    private void setupArena() {
        // إضافة خط المنتصف
        javafx.scene.shape.Line midline = new javafx.scene.shape.Line(arenaWidth / 2, 0, arenaWidth / 2, arenaHeight);
        midline.setStroke(Color.GRAY);
        midline.setStrokeWidth(2);

        gameArena.getChildren().addAll(midline, player1.getFighterShape(), player2.getFighterShape(), healthBar1, healthBar2);
    }

    // دالة حلقة اللعبة (Game Loop)
    private void updateGame(long now) {
        // 1. معالجة الإدخال والحركة
        inputHandler.handleMovement();

        // 2. تحديث المقذوفات والتصادمات
        checkCollisionsAndUpdateProjectiles();

        // 3. تحديث أشرطة الصحة
        updateHealthBars();

        // 4. التحقق من نهاية اللعبة
        if (player1.getHealth() <= 0 || player2.getHealth() <= 0) {
            gameLoop.stop();
            displayWinner();
        }
    }

    private void checkCollisionsAndUpdateProjectiles() {
        List<Projectile> projectilesToRemove = new ArrayList<>();

        for (Projectile p : activeProjectiles) {
            p.updatePosition();

            Fighter target = (p.getDirection() == 1) ? player2 : player1;

            // تحقق من التصادم مع الهدف
            if (p.checkCollision(target.getFighterShape())) {
                target.decreaseHealth(p.getDamage());
                projectilesToRemove.add(p);
            }
            // تحقق من تجاوز حدود الشاشة
            else if (p.getXPosition() < 0 || p.getXPosition() > arenaWidth) {
                projectilesToRemove.add(p);
            }
        }

        // إزالة المقذوفات غير النشطة
        for (Projectile p : projectilesToRemove) {
            activeProjectiles.remove(p);
            gameArena.getChildren().remove(p.getShape());
        }
    }

    private void updateHealthBars() {
        healthBar1.setProgress(player1.getHealth() / 120.0); // 120.0 Max Health
        healthBar2.setProgress(player2.getHealth() / 120.0);
    }

    private ProgressBar createHealthBar(Fighter f, double x, double y) {
        ProgressBar bar = new ProgressBar(1.0); // يبدأ بـ 100%
        bar.setPrefSize(200, 20);
        bar.setTranslateX(x);
        bar.setTranslateY(y);
        bar.setStyle("-fx-accent: red;");
        return bar;
    }

    private void displayWinner() {
        // ... منطق عرض رسالة الفائز على الشاشة
    }

    public void addProjectile(Projectile p) {
        activeProjectiles.add(p);
        gameArena.getChildren().add(p.getShape());
    }

    public void startGameLoop() {
        gameLoop.start();
    }

    // Getters للربط مع App و InputHandler
    public Pane getGameArena() { return gameArena; }
    public InputHandler getInputHandler() { return inputHandler; }
    public double getArenaWidth() { return arenaWidth; }
    public double getArenaHeight() { return arenaHeight; }
}

