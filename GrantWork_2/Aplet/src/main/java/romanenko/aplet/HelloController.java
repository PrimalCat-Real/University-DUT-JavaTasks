package romanenko.aplet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import romanenko.aplet.components.IconButton;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private VBox sidemenu;

    public void initialize() {
        IconButton iconButton = new IconButton("M5.4598 7.41005V11.56H6.6498V6.05005H5.6998L4.0498 7.16005L4.5198 8.00005L5.4598 7.41005Z",
                Color.web("#D9D9D9"), Color.RED);
        sidemenu.getChildren().add(iconButton);
    }
}