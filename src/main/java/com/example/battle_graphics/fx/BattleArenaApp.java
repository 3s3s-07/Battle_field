package com.example.battle_graphics.fx;

import com.example.battle_graphics.base.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class BattleArenaApp extends Application {

    private final double WIDTH = 900;
    private final double HEIGHT = 600;

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle("Battle Arena");

        // Start with the title / splash scene, then proceed to selection
        primaryStage.setScene(createTitleScene());
        primaryStage.show();
    }

    // ------------------------------------------
    // Title / Splash Screen shown before character select
    // ------------------------------------------
    private Scene createTitleScene() {
        Label title = new Label("BATTLE ARENA");
        title.setFont(Font.font("Arial", 56));
        title.setTextFill(Color.web("#ffffff"));
        title.setStyle("-fx-font-weight: bold; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 6,0,0,2);");

        Label subtitle = new Label("Two-player local combat. Choose fighters and battle!");
        subtitle.setFont(Font.font("Arial", 18));
        subtitle.setTextFill(Color.web("#dddddd"));

        Button startBtn = new Button("Start");
        startBtn.setPrefWidth(180);
        startBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        startBtn.setOnAction(e -> primaryStage.setScene(createSelectionScene()));

        Button howToBtn = new Button("How to Play");
        howToBtn.setPrefWidth(180);
        howToBtn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
        howToBtn.setOnAction(e -> showHowToPlay());

        Button exitBtn = new Button("Exit");
        exitBtn.setPrefWidth(180);
        exitBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");
        exitBtn.setOnAction(e -> primaryStage.close());

        VBox buttonBox = new VBox(12, startBtn, howToBtn, exitBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(18);
        root.setPadding(new Insets(60));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(#2c3e50, #1a252f);");
        root.getChildren().addAll(title, subtitle, buttonBox);

        return new Scene(root, WIDTH, HEIGHT);
    }

    // Simple informational dialog laid out as a Scene replacement (keeps it lightweight)
    private void showHowToPlay() {
        Label header = new Label("How to Play");
        header.setFont(Font.font("Arial", 28));
        header.setTextFill(Color.WHITE);

        Label lines = new Label(
                "Player 1: Move with WASD, Shoot = F, Switch Weapon = Q\n" +
                        "Player 2: Move with Arrow Keys, Shoot = L, Switch Weapon = E\n\n" +
                        "Each fighter has multiple weapons — switch to find the best one.\n" +
                        "First player to reduce the opponent's health to 0 wins."
        );
        lines.setFont(Font.font("Arial", 14));
        lines.setTextFill(Color.web("#e9e9e9"));

        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        back.setOnAction(e -> primaryStage.setScene(createTitleScene()));

        VBox box = new VBox(12, header, lines, back);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(24));
        box.setStyle("-fx-background-color: rgba(20,20,20,0.6); -fx-background-radius: 8;");

        VBox root = new VBox(10, box);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: linear-gradient(#2c3e50, #1a252f);");

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
    }

    // ------------------------------------------
    // 1) Character Selection Screen
    // ------------------------------------------
    private Scene createSelectionScene() {

        ComboBox<String> p1Select = new ComboBox<>();
        p1Select.getItems().addAll("Warrior", "Mage", "Archer");
        p1Select.setValue("Warrior");

        ComboBox<String> p2Select = new ComboBox<>();
        p2Select.getItems().addAll("Warrior", "Mage", "Archer");
        p2Select.setValue("Archer");

        Button startButton = new Button("Start Battle");

        startButton.setOnAction(e -> {
            Fighter p1 = createFighter(p1Select.getValue(), 100, HEIGHT / 2);
            Fighter p2 = createFighter(p2Select.getValue(), WIDTH - 150, HEIGHT / 2);

            // Make player2 face left so its shots travel toward player1
            p2.setFacingRight(false);

            primaryStage.setScene(createGameScene(p1, p2));
        });

        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));

        root.getChildren().addAll(
                new Label("🎮 Select Fighters"),
                new HBox(10, new Label("Player 1:"), p1Select),
                new HBox(10, new Label("Player 2:"), p2Select),
                startButton
        );

        return new Scene(root, WIDTH, HEIGHT);
    }

    // ------------------------------------------
    // 2) Create Fighter from dropdown selection
    // ------------------------------------------
    private Fighter createFighter(String type, double x, double y) {
        switch (type) {
            case "Mage": return new mage(x, y);
            case "Archer": return new archer(x, y);
            default: return new warrior(x, y);
        }
    }

    // ------------------------------------------
    // 3) Create Game Scene with GameManger
    // ------------------------------------------
    private Scene createGameScene(Fighter p1, Fighter p2) {

        // Create fighter shapes
        p1.createShape();
        p2.createShape();
        // Arena Pane
        Pane gamePane = new Pane();
        gamePane.setPrefSize(WIDTH, HEIGHT);
        javafx.scene.shape.Line midline = new javafx.scene.shape.Line(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
        midline.setStroke(Color.GRAY);
        midline.setStrokeWidth(2);
        gamePane.getChildren().addAll(midline);
        // Health bars
        ProgressBar hp1 = new ProgressBar(1.0);
        ProgressBar hp2 = new ProgressBar(1.0);

        hp1.setPrefWidth(200);
        hp2.setPrefWidth(200);

        hp1.setLayoutX(20);
        hp1.setLayoutY(20);

        hp2.setLayoutX(WIDTH - 220);
        hp2.setLayoutY(20);

        gamePane.getChildren().addAll(hp1, hp2);

        // InputHandler created BEFORE GameManger but without controller (will be set)
        InputHandler handler = new InputHandler(p1, p2, null);

        // Create GameManger, pass a callback that will return to the selection scene when "Play Again" is pressed.
        GameManger manager = new GameManger(
                p1, p2,
                gamePane,
                handler,
                hp1,
                hp2,
                WIDTH,
                HEIGHT,
                () -> {
                    // ensure UI update happens on FX thread
                    javafx.application.Platform.runLater(() -> {
                        primaryStage.setScene(createSelectionScene());
                    });
                }
        );
        handler.setGameController(manager);

        // Create scene and attach key handlers to the Scene so key events are delivered reliably.
        Scene scene = new Scene(gamePane, WIDTH, HEIGHT);
        scene.setOnKeyPressed(handler::handleKeyPressed);
        scene.setOnKeyReleased(handler::handleKeyReleased);

        // Make sure the pane can receive focus and request it so keyboard events are received.
        gamePane.setFocusTraversable(true);
        gamePane.requestFocus();

        return scene;
    }
}