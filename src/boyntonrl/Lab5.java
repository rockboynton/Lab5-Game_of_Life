/*
 * SE1021 - 021
 * Winter 2017
 * Lab: Lab 5 Conway's Game of Life
 * Name: Rock Boynton
 * Created: 1/13/18
 */

package boyntonrl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This JavaFX application models Conway's Game of Life
 */
public class Lab5 extends Application {

    /**
     * Width of the stage in pixels
     */
    public static final int WIDTH = 690;
    /**
     * Height of the stage in pixels
     */
    public static final int HEIGHT = 530;

    /**
     * Start method of the JavaFX program
     * @param primaryStage the stage of the JavaFX application
     * @throws Exception if anything goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lab5.fxml"));
        primaryStage.setTitle("Lab 5: Game of Life");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    /**'
     * Main method of the JavaFX application
     * @param args args of the system
     */
    public static void main(String[] args) {
        launch(args);
    }
}
