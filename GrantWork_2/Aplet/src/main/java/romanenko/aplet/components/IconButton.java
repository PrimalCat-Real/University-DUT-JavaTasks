package romanenko.aplet.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class IconButton extends Button {
    private ObjectProperty<Color> defaultColor = new SimpleObjectProperty<>(Color.BLACK);
    private ObjectProperty<Color> hoverColor = new SimpleObjectProperty<>(Color.RED);

    public IconButton() {
        setStyle("-fx-background-color: transparent;");
        getStyleClass().add("icon-button");

        hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                setStyle("-fx-background-color: " + toHex(hoverColor.get()) + ";");
            } else {
                setStyle("-fx-background-color: transparent;");
            }
        });
    }

    public IconButton(String svgPath, Color defaultColor, Color hoverColor) {
        this();
        setDefaultColor(defaultColor);
        setHoverColor(hoverColor);
        setGraphic(createIcon(svgPath, defaultColor));
    }

    public Color getDefaultColor() {
        return defaultColor.get();
    }

    public ObjectProperty<Color> defaultColorProperty() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor.set(defaultColor);
//        setGraphic(createIcon(getGraphic(), defaultColor));
    }

    public Color getHoverColor() {
        return hoverColor.get();
    }

    public ObjectProperty<Color> hoverColorProperty() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor.set(hoverColor);
    }

    private SVGPath createIcon(String svgPath, Color color) {
        SVGPath icon = new SVGPath();
        icon.setContent(svgPath);
        icon.setFill(color);
        return icon;
    }

    private String toHex(Color color) {
        String red = Integer.toHexString((int) (color.getRed() * 255));
        String green = Integer.toHexString((int) (color.getGreen() * 255));
        String blue = Integer.toHexString((int) (color.getBlue() * 255));
        return "#" + red + green + blue;
    }
}