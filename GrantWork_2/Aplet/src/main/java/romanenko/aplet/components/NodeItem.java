package romanenko.aplet.components;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class NodeItem extends Pane {
    private final int radius;
//    private final int number;
    private Circle circle;
    private int value;
    private NodeItem left;
    private NodeItem right;
    private int level;

    public NodeItem(int number, int radius) {
        this.value = number;
        this.radius = radius;
        initialize();
    }

    public NodeItem(int number) {
        this.value = number;
        this.radius = 20;
        initialize();
    }
    public void setStroke(Paint paint) {
        circle.setStroke(paint);
    }
    public Paint getStroke() {
        return circle.getStroke();
    }

    public void setStrokeWidth(double width) {
        circle.setStrokeWidth(width);
    }
    public double getStrokeWidth() {
        return circle.getStrokeWidth();
    }

    public void setFill(Paint paint) {
        circle.setFill(paint);
    }
    public Paint getFill() {
        return circle.getFill();
    }
    private void initialize() {
        circle = new Circle(radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.GRAY);

        Text text = new Text(String.valueOf(value));
        Bounds textBounds = text.getBoundsInLocal();
        text.setFont(Font.font(radius * 0.7)); // Adjust font size based on radius
        text.setFill(Color.GRAY);
        text.setX(radius - circle.getLayoutBounds().getWidth() / 2 - textBounds.getWidth() / 2);
        text.setY(radius - circle.getLayoutBounds().getHeight() / 2 + textBounds.getHeight() / 4);

        getChildren().addAll(circle, text);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(NodeItem left) {
        this.left = left;
    }

    public NodeItem getLeft() {
        return left;
    }

    public void setRight(NodeItem right) {
        this.right = right;
    }

    public NodeItem getRight() {
        return right;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}