package com.example.battle_graphics.fx;
import com.example.battle_graphics.base.Fighter;
import com.example.battle_graphics.base.archer;
import com.example.battle_graphics.base.mage;
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
        primaryStage.setTitle("Battle Arena Game");

        // عرض Scene 1: شاشة اختيار الشخصيات
        primaryStage.setScene(createSelectionScene());
        primaryStage.show();
    }

    private Scene createSelectionScene() {
        // خيارات المقاتلين
        String[] fighters = {"Warrior", "Mage", "Archer"};

        ComboBox<String> p1Selector = new ComboBox<>();
        p1Selector.getItems().addAll(fighters);
        p1Selector.setValue("none"); // اختيار افتراضي

        ComboBox<String> p2Selector = new ComboBox<>();
        p2Selector.getItems().addAll(fighters);
        p2Selector.setValue("none"); // اختيار افتراضي

        Label p1Label = new Label("Player 1 (W,A,S,D, F):");
        Label p2Label = new Label("Player 2 (Arrows, L):");

        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(e -> {
            Fighter p1 = createFighter(p1Selector.getValue(), 50, HEIGHT / 2, 1); // موقع اللاعب 1 على اليسار، الاتجاه 1
            Fighter p2 = createFighter(p2Selector.getValue(), WIDTH - 90, HEIGHT / 2, -1); // موقع اللاعب 2 على اليمين، الاتجاه -1
            primaryStage.setScene(createGameScene(p1, p2));
        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.getChildren().addAll(
                new Label("--- Select Your Fighters ---"),
                new HBox(10, p1Label, p1Selector),
                new HBox(10, p2Label, p2Selector),
                startGameButton
        );

        return new Scene(layout, WIDTH, HEIGHT);
    }

    // دالة مساعدة لإنشاء المقاتل بناءً على الاختيار
    private Fighter createFighter(String type, double x, double y, boolean side) {
        switch (type) {
            case "Warrior": return new warrior(x, y);
            case "Mage": return new mage(x,y);
            case "Archer": return new archer(x, y);
            default: return new warrior(x, y); // افتراضي
        }
    }

    // دالة لإنشاء مشهد اللعب
    private Scene createGameScene(Fighter p1, Fighter p2) {
        GameManger Manger = new GameManger(p1,p2,WIDTH, HEIGHT);

        Scene gameScene = new Scene(Manger.getGamePane(), WIDTH, HEIGHT);

        // ربط InputHandler بالمشهد
        gameScene.setOnKeyPressed(Manger.getInput()::handleKeyPressed);
        gameScene.setOnKeyReleased(Manger.getInput()::handleKeyReleased);

        // بدء حلقة اللعبة
        Manger.startGameLoop();

        return gameScene;
    }
}