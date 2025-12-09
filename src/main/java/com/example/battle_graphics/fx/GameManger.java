package com.example.battle_graphics.fx;
import com.example.battle_graphics.base.Fighter;
import com.example.battle_graphics.base.Projectile;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import java.util.ArrayList;
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
    private  final double arenawidth;
    private  final double arenaheight;
    private boolean gameOver = false;
    private final Runnable onPlayAgain;

    public GameManger(Fighter p1, Fighter p2, Pane pane, InputHandler handler, ProgressBar hp1, ProgressBar hp2, double arenawidth, double arenaheight, Runnable onPlayAgain) {
        this.player1 = p1;
        this.player2 = p2;
        this.gamePane = pane;
        this.input = handler;
        this.hp1 = hp1;
        this.hp2 = hp2;
        this.arenawidth = arenawidth;
        this.arenaheight = arenaheight;
        this.projectiles = new ArrayList<>();
        this.onPlayAgain = onPlayAgain;

        // ensure shapes placed at fighters' coordinates (some fighters set translate inside createShape already)
        if (player1.getFighterShape() != null) {
            player1.getFighterShape().setTranslateX(player1.getX());
            player1.getFighterShape().setTranslateY(player1.getY());
        }
        if (player2.getFighterShape() != null) {
            player2.getFighterShape().setTranslateX(player2.getX());
            player2.getFighterShape().setTranslateY(player2.getY());
        }

        gamePane.getChildren().addAll(player1.getFighterShape(), player2.getFighterShape());
        startLoop();
        if (this.input != null) {
            this.input.setGameController(this);
        }
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
        if (gameOver) return;

        input.handleMovement();
        updateProjectiles();
        checkCollisions();
        updateHealthBars();
        checkWinner();

    }
    public void addProjectile(Projectile p) {
        if (p == null || p.getShape() == null) return;
        projectiles.add(p);
        gamePane.getChildren().add(p.getShape());
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
            if (!p.isActive()) continue;
            Fighter owner = p.getOwner();
            if (owner == player1) {
                if (p.getShape().getBoundsInParent().intersects(player2.getFighterShape().getBoundsInParent())) {
                    player2.decreaseHealth(p.getDamage());
                    p.deactivate();
                }
            } else if (owner == player2) {
                if (p.getShape().getBoundsInParent().intersects(player1.getFighterShape().getBoundsInParent())) {
                    player1.decreaseHealth(p.getDamage());
                    p.deactivate();
                }
            } else {
                // owner unknown — check both targets (defensive)
                if (p.getShape().getBoundsInParent().intersects(player1.getFighterShape().getBoundsInParent())) {
                    player1.decreaseHealth(p.getDamage());
                    p.deactivate();
                } else if (p.getShape().getBoundsInParent().intersects(player2.getFighterShape().getBoundsInParent())) {
                    player2.decreaseHealth(p.getDamage());
                    p.deactivate();
                }
            }
        }
    }
    private void updateHealthBars() {
        hp1.setProgress((double)player1.getHealth() / player1.getMaxHp());
        hp2.setProgress((double)player2.getHealth() / player2.getMaxHp());
    }
    private void checkWinner() {
        if (!player1.isAlive()) {
            showWinner("Player 2 Wins!");
        } else if (!player2.isAlive()) {
            showWinner("Player 1 Wins!");
        }

    }

    private void showWinner(String text) {
        gameOver = true;
        if (gameLoop != null) gameLoop.stop();
        // Build an overlay on the JavaFX thread
        Platform.runLater(() -> {
            StackPane overlay = new StackPane();
            overlay.setPrefSize(arenawidth, arenaheight);

            Rectangle bg = new Rectangle(arenawidth, arenaheight, Color.rgb(0, 0, 0, 0.65));
            bg.setArcWidth(8);
            bg.setArcHeight(8);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(0.5);

            Label winnerLabel = new Label(text);
            winnerLabel.setTextFill(Color.WHITE);
            winnerLabel.setFont(Font.font(36));
            winnerLabel.setStyle("-fx-font-weight: bold;");

            Button playBtn = new Button("Play Again");
            Button exitBtn = new Button("Exit");
            playBtn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
            exitBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");

            VBox box = new VBox(15, winnerLabel, playBtn, exitBtn);
            box.setAlignment(Pos.CENTER);
            box.setStyle("-fx-padding: 20; -fx-background-color: rgba(30,30,30,0.8); -fx-background-radius: 8;");
            box.setEffect(new DropShadow(10, Color.color(0,0,0,0.6)));

            overlay.getChildren().addAll(bg, box);
            overlay.setMouseTransparent(false);

            // fade in animation for a nicer effect
            overlay.setOpacity(0);
            FadeTransition fade = new FadeTransition(Duration.millis(350), overlay);
            fade.setFromValue(0);
            fade.setToValue(1.0);
            fade.play();

            // Add overlay
            gamePane.getChildren().add(overlay);
            overlay.toFront();

            // disable input
            if (input != null) {
                input.setGameController(null);
            }

            // Play Again behavior: cleanup and call callback
            playBtn.setOnAction(e -> {
                cleanupAfterGame();
                if (onPlayAgain != null) {
                    onPlayAgain.run();
                }
            });

            exitBtn.setOnAction(e -> {
                cleanupAfterGame();
                Platform.exit();
            });
        });
    }

    private void cleanupAfterGame() {
        // Stop loop if not already stopped
        gameOver = true;
        if (gameLoop != null) {
            gameLoop.stop();
        }
        // remove and deactivate any projectiles
        for (Projectile p : new ArrayList<>(projectiles)) {
            p.deactivate();
            if (p.getShape() != null) gamePane.getChildren().remove(p.getShape());
        }
        projectiles.clear();

        // detach input to avoid NPEs while not in game
        if (input != null) {
            input.setGameController(null);
            // also clear active key state if you add a method to InputHandler later
        }
    }
}