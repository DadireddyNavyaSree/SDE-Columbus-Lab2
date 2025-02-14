package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
    private final int dimension = 10;
    private final int scale = 50; // Scaling factor
    private OceanMap oceanMap;
    private Ship ship;
    private ImageView shipImageView;
    private boolean[][] oceanGrid; // Used to draw the map

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        oceanMap = new OceanMap();
        oceanGrid = oceanMap.getMap();
        ship = new Ship(3, 3); // Placing ship at an initial position

        Pane root = new Pane();
        drawMap(root);
        loadShipImage(root);

        Scene scene = new Scene(root, dimension * scale, dimension * scale);
        primaryStage.setTitle("Ocean Explorer");
        primaryStage.setScene(scene);
        primaryStage.show();

        startSailing(scene);
    }

    private void drawMap(Pane root) {
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                // Drawing the ocean (background)
                Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
                rect.setStroke(Color.BLACK); // Black outline
                rect.setFill(Color.PALETURQUOISE); // Ocean blue color

                if (oceanGrid[x][y]) { // If there's an island
                    rect.setFill(Color.GREEN); // Green color for islands
                }

                root.getChildren().add(rect);
            }
        }
    }

    private void loadShipImage(Pane root) {
        Image shipImage = new Image("images/ship.png", 50, 50, true, true);
        shipImageView = new ImageView(shipImage);
        int[] shipLocation = ship.getShipLocation();
        shipImageView.setX(shipLocation[0] * scale);
        shipImageView.setY(shipLocation[1] * scale);
        root.getChildren().add(shipImageView);
    }

    private void startSailing(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case RIGHT:
                        ship.goEast(oceanMap);
                        break;
                    case LEFT:
                        ship.goWest(oceanMap);
                        break;
                    case UP:
                        ship.goNorth(oceanMap);
                        break;
                    case DOWN:
                        ship.goSouth(oceanMap);
                        break;
                    default:
                        break;
                }

                // Update ship's image position
                int[] shipLocation = ship.getShipLocation();
                shipImageView.setX(shipLocation[0] * scale);
                shipImageView.setY(shipLocation[1] * scale);
            }
        });
    }
}

