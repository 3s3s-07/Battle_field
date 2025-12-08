package com.example.battle_graphics.fx;
import com.example.battle_graphics.base.Fighter;
import com.example.battle_graphics.base.warrior;
import com.example.battle_graphics.base.archer;
import com.example.battle_graphics.base.mage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class BattleArenaApp extends Application {

    private final double WIDTH = 800;
    private final double HEIGHT = 600;

    private Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle("Battle Arena");


        primaryStage.setScene(createSelectionScene());
        primaryStage.show();
    }


    private Scene createSelectionScene() {
        String[] fighters = {"Warrior", "Mage", "Archer"};
        ComboBox<String> p1Select = new ComboBox<>();
        p1Select.getItems().addAll(fighters);
        p1Select.setValue("none");

        ComboBox<String> p2Select = new ComboBox<>();
        p2Select.getItems().addAll(fighters);
        p2Select.setValue("none");

        Button startButton = new Button("Yala bena");

        startButton.setOnAction(e -> {
            Fighter p1 = createFighter(p1Select.getValue(), 50, HEIGHT / 2,1);
            Fighter p2 = createFighter(p2Select.getValue(), WIDTH - 150, HEIGHT / 2,-1);

            primaryStage.setScene(createGameScene(p1, p2));
        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));

        layout.getChildren().addAll(
                new Label("Select Fighters"),
                new HBox(10, new Label("Player 1:"), p1Select),
                new HBox(10, new Label("Player 2:"), p2Select),
                startButton
        );

        return new Scene(layout, WIDTH, HEIGHT);
    }


    private Fighter createFighter(String type, double x, double y,int facingRight) {
        switch (type) {
            case "Warrior": return new warrior(x, y, facingRight);
            case "Mage": return new mage(x, y,facingRight);
            case "Archer": return new archer(x, y,facingRight);
            default: return new warrior(x, y,facingRight);
        }
    }


    private Scene createGameScene(Fighter p1, Fighter p2) {
        GameManger gameController = new GameManger(WIDTH, HEIGHT, p1, p2);
        gameController.setupArena();
        Scene gameScene = new Scene(gameController.getGamePane(), WIDTH, HEIGHT);

        // ربط InputHandler بالمشهد
        gameScene.setOnKeyPressed(gameController.getInput()::handleKeyPressed);
        gameScene.setOnKeyReleased(gameController.getInput()::handleKeyReleased);

        // بدء حلقة اللعبة
        gameController.startGameLoop();

        return gameScene;
    }
}