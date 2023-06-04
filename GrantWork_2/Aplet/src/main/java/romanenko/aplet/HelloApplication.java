package romanenko.aplet;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import romanenko.aplet.components.NodeItem;
import romanenko.aplet.structures.BinaryTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HelloApplication extends Application {
    private static final int WINDOW_WIDTH = 1020;
    private static final int WINDOW_HEIGHT = 640;

    private static final int vGap = 60;
    private static final int hGap = 150;


    private static final double NODE_RADIUS = 20.0;
    // Declare the necessary JavaFX controls
    private TextField addElementInput;
    private Pane nodeArea;
    private Button addElementBtn;
    private Button removeElementBtn;

    private Button goInorderBtn;

    private Text displayTraversalText;

    private Stack<NodeItem> nodeStack = new Stack<>();;

    private BinaryTree binaryTree = new BinaryTree();


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false); // Prevent resizing

        // Retrieve the JavaFX controls by their FX IDs
        addElementInput = (TextField) fxmlLoader.getNamespace().get("addElementInput");
        nodeArea = (Pane) fxmlLoader.getNamespace().get("nodeArea");
        addElementBtn = (Button) fxmlLoader.getNamespace().get("addElementBtn");
        removeElementBtn = (Button) fxmlLoader.getNamespace().get("removeElementBtn");
        goInorderBtn = (Button) fxmlLoader.getNamespace().get("goInorderBtn");
        displayTraversalText = (Text) fxmlLoader.getNamespace().get("displayTraversalText");


        goInorderBtn.setOnAction(event -> {
            // Disable the button while the animation is in progress
            goInorderBtn.setDisable(true);
            Paint defaultTextFill = goInorderBtn.getTextFill();
            goInorderBtn.setTextFill(Color.GRAY);
            displayTraversalText.setText(displayTraversalText.getText() + "[inorder]");
            nodeStack.clear(); // Clear the stack before starting the traversal
            fillNodeStackInorder(binaryTree.getRoot()); // Fill the nodeStack with nodes in inorder traversal order
            changeStrokeColorWithDelay(() -> {
                // Enable the button after the animation is completed
                goInorderBtn.setDisable(false);
                goInorderBtn.setTextFill(defaultTextFill); // Restore the original text color
            });
        });
        // Set an event handler for the addElementBtn button
        addElementBtn.setOnAction(event -> {

            String element = addElementInput.getText();
            if (!element.isEmpty()) {
                int value;
                try {
                    value  = Integer.parseInt(element);
                    binaryTree.addNode(value);
                    drawBinaryTree();
                }catch (Exception e) {
                    Text error = new Text("Error: wrong input");
                    error.setFill(Color.RED);
                    error.setLayoutX(40);
                    error.setLayoutY(40);
                    System.out.println(e.getMessage());
                    nodeArea.getChildren().add(error);
                }
            }
        });

        // event for enter support
        addElementInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String element = addElementInput.getText();
                if (!element.isEmpty()) {
                    int value;
                    try {
                        value = Integer.parseInt(element);
                        binaryTree.addNode(value);
                        drawBinaryTree();
                    } catch (Exception e) {
                        Text error = new Text("Error: wrong input");
                        error.setFill(Color.RED);
                        error.setLayoutX(40);
                        error.setLayoutY(40);
                        System.out.println(e.getMessage());
                        nodeArea.getChildren().add(error);
                    }
                }
            }
        });
        removeElementBtn.setOnAction(event -> {
            String element = addElementInput.getText();
            if (!element.isEmpty()) {
                int value = Integer.parseInt(element);
                binaryTree.removeNode(value);
                drawBinaryTree();
            }
        });
        // Set the width and height of the stage
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);

        stage.show();
    }

    private void fillNodeStackInorder(NodeItem node) {
        if (node != null) {
            fillNodeStackInorder(node.getRight()); // Traverse right subtree recursively
            nodeStack.push(node); // Add the current node to the stack
            fillNodeStackInorder(node.getLeft()); // Traverse left subtree recursively
        }
    }

    private void changeStrokeColorWithDelay(Runnable onAnimationCompleted) {
        if (!nodeStack.isEmpty()) {
            NodeItem node = nodeStack.pop();
            Color originalStrokeColor = (Color) node.getStroke(); // Store the original stroke color
            double originalStrokeWidth = node.getStrokeWidth(); // Store the original stroke width

            node.setStroke(Color.RED); // Change the stroke color of the current node
            node.setStrokeWidth(3); // Set the stroke width for the active node
            displayTraversalText.setText(displayTraversalText.getText() + " " + node.getValue()); // Append the node value to the display text

            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(event -> {
                changeStrokeColorWithDelay(onAnimationCompleted); // Process the next node with delay
            });
            pause.play();

            PauseTransition originalColorPause = new PauseTransition(Duration.millis(500));
            originalColorPause.setOnFinished(event -> {
                node.setStroke(originalStrokeColor); // Restore the original stroke color
                node.setStrokeWidth(originalStrokeWidth); // Restore the original stroke width

                if (nodeStack.isEmpty()) {
                    // Invoke the callback function when animation is completed
                    onAnimationCompleted.run();
                }
            });
            originalColorPause.play();
        }
    }


    // Method to draw the binary tree in the nodeArea pane
    private void drawBinaryTree() {
        nodeArea.getChildren().clear(); // Clear the nodeArea before drawing
        double nodeAreaWidth = nodeArea.getWidth();
        double startX = nodeAreaWidth / 2;
        double startY = 50;
        binaryTree.logAllValues();


//
        if (binaryTree.getRoot() != null) {
            parsBranch(binaryTree.getRoot(), nodeArea, null, startX, startY, 0, "center");
        }
//        while (!nodeStack.isEmpty()) {
//
//        }
//        System.out.println(nodeStack.size());
//        if (nodeStack.lastElement().getValue() == 0 ){

//
//        }



    }

    private void parsBranch(NodeItem node, Pane nodeArea, NodeItem parentNodeItem, double startX, double startY, int level, String position) {
        if (node != null) {
//            NodeItem nodeItem = new NodeItem(node.getValue(), (int) NODE_RADIUS);

            double nodeX;
            double nodeY;
//
            if (position.equals("left")) {
                nodeX = Math.max(startX - (hGap / Math.pow(2, level - 1)) - (NODE_RADIUS / Math.pow(2, level - 1)), NODE_RADIUS);
                nodeY = startY + vGap - NODE_RADIUS / level;
            } else if (position.equals("right")) {
                nodeX = Math.max(startX + (hGap / Math.pow(2, level - 1)) + (NODE_RADIUS / Math.pow(2, level - 1)), startX + NODE_RADIUS);
                nodeY = startY + vGap - NODE_RADIUS / level;
            } else {
                nodeX = startX - NODE_RADIUS;
                nodeY = startY - NODE_RADIUS / 2;
            }
//
            node.setLayoutX(nodeX);
            node.setLayoutY(nodeY);
            nodeArea.getChildren().add(node);
            if (parentNodeItem != null){
                Line line = new Line(
                        parentNodeItem.getLayoutX(), parentNodeItem.getLayoutY(),
                        node.getLayoutX(), node.getLayoutY()
                );
                nodeArea.getChildren().add(line);
                line.toBack();
            }
//
//
            parsBranch(node.getLeft(), nodeArea, node, nodeX, nodeY, level + 1, "left");
            parsBranch(node.getRight(), nodeArea, node, nodeX, nodeY, level + 1, "right");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}