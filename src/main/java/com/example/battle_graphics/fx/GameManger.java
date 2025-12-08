package com.example.battle_graphics.fx;
import com.example.battle_graphics.base.Fighter;
import com.example.battle_graphics.base.Projectile;
import javafx.animation.AnimationTimer;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
public class GameManger {

    private Fighter player1;
    private Fighter player2;
    private Pane gamePane;
    private List<Projectile> projectiles = new ArrayList<>();
    private InputHandler input;
    private AnimationTimer gameLoop;
    private final ProgressBar hp1;
    private final ProgressBar hp2;
    private  final double arenawidth;
    private  final double arenaheight;
    public GameManger(double arenawidth,double arenaheight ,Fighter p1 ,Fighter p2 ) {
        this.player1 = p1;
        this.player2 = p2;
        this.gamePane =new Pane();
        this.input =new InputHandler(p1,p2,this);
        this.hp1 = createHealthBar(p1,10,10) ;
        this.hp2 = createHealthBar(p2,arenawidth-210,10);
        this.arenawidth = arenawidth;
        this.arenaheight = arenaheight;
        this.gamePane.setPrefSize(arenawidth, arenaheight);

        this.gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
            }
        };
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public InputHandler getInput() {
        return input;
    }

    public double getArenawidth() {
        return arenawidth;
    }

    public double getArenaheight() {
        return arenaheight;
    }

    public void startGameLoop() {
        gameLoop.start();
    }
    private void update(long now) {
        // 1. معالجة الإدخال والحركة
        input.handleMovement();

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
    public void addProjectile(Projectile p) {
        projectiles.add(p);
        gamePane.getChildren().add(p.getShape());}

    private void checkCollisionsAndUpdateProjectiles() {
        List<Projectile> projectilesToRemove = new ArrayList<>();

        for (Projectile p : projectiles) {
            p.updatePosition();

            Fighter target = (p.getDirectionRight() == 1) ? player2 : player1;

            // تحقق من التصادم مع الهدف
            if (p.checkCollision(target.getFighterShape())) {
                target.decreaseHealth(p.getDamage());
                projectilesToRemove.add(p);
            }
            // تحقق من تجاوز حدود الشاشة
            else if (p.getX() < 0 || p.getX() > arenawidth) {
                projectilesToRemove.add(p);
            }
        }

        // إزالة المقذوفات غير النشطة
        for (Projectile p : projectilesToRemove) {
            projectiles.remove(p);
            gamePane.getChildren().remove(p.getShape());
        }
    }
    private void updateHealthBars() {
        hp1.setProgress((double)player1.getHealth() / 120.0);
        hp2.setProgress((double)player2.getHealth() / 120.0);
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
    private ProgressBar createHealthBar(Fighter f, double x, double y) {
        ProgressBar bar = new ProgressBar(1.0); // يبدأ بـ 100%
        bar.setPrefSize(200, 20);
        bar.setTranslateX(x);
        bar.setTranslateY(y);
        bar.setStyle("-fx-accent: red;");
        return bar;
    }
    private void displayWinner() {
        String winnerName;

        // تحديد الفائز
        if (player1.getHealth() <= 0 && player2.getHealth() > 0) {
            // يمكن أن يكون اللاعب 1 أي من الفئات (Warrior, Mage, Archer)
            winnerName = "Player 2 (" + player2.getClass().getSimpleName() + ") WINS!";
        } else if (player2.getHealth() <= 0 && player1.getHealth() > 0) {
            winnerName = "Player 1 (" + player1.getClass().getSimpleName() + ") WINS!";
        } else {
            // حالة التعادل (كلاهما وصل للصفر في نفس الإطار)
            winnerName = "DRAW!";
        }

        // إنشاء Label لعرض الرسالة
        javafx.scene.control.Label winnerLabel = new javafx.scene.control.Label(winnerName);
        winnerLabel.setStyle("-fx-font-size: 48px; -fx-text-fill: gold; -fx-font-weight: bold; -fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 20px;");

        // وضع الـ Label في منتصف الـ Pane
        winnerLabel.setPrefSize(arenawidth, arenaheight);
        winnerLabel.setAlignment(javafx.geometry.Pos.CENTER);

        // إضافة الـ Label إلى الساحة
        gamePane.getChildren().add(winnerLabel);

        // يمكنك هنا أيضًا عرض زر للبدء مرة أخرى
    }
    public void setupArena() {
        javafx.scene.shape.Line midline = new javafx.scene.shape.Line(
                arenawidth / 2, 0, arenawidth / 2, arenaheight
        );
        midline.setStroke(Color.DARKRED);
        midline.setStrokeWidth(2);

        gamePane.getChildren().addAll(midline,
                player1.getFighterShape(),
                player2.getFighterShape(),
                hp1, hp2
        );
    }

}