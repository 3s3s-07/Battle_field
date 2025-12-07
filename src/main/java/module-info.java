module com.example.battle_graphics {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    // VERY IMPORTANT:
    opens com.example.battle_graphics.fx to javafx.graphics;
    exports com.example.battle_graphics.fx;
}
