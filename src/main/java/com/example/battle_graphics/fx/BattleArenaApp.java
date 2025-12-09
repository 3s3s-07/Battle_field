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

public class BattleArenaApp extends Application {

    private final double WIDTH = 900;
    private final double HEIGHT = 600;

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle("Battle Arena – Character Select");

        // Load first screen (selection)
        primaryStage.setScene(createSelectionScene());
        primaryStage.show();
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