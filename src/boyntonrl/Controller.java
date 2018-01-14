/*
 * SE1021 - 021
 * Winter 2017
 * Lab: Lab 5 Conway's Game of Life
 * Name: Rock Boynton
 * Created: 1/13/18
 */

package boyntonrl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the JavaFX application, Lab5
 */
public class Controller implements Initializable{

    @FXML
    private Pane gamePane;
    @FXML
    private Button randomizeButton;
    @FXML
    private Button iterateButton;
    @FXML
    private Label numAliveCells;
    @FXML
    private Label numDeadCells;
    @FXML
    private Button iterate10StepsButton;

    private LifeGrid lifeGrid;

    /**
     * Initializes the Game Pane containing a LifeGrid for Conway's Game of Life
     * @param location URL location
     * @param resources bundle of resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert gamePane != null :"fx:id=\"gamePane\" was not injected: check your FXML file " +
                "'game.fxml'.";
        lifeGrid = new LifeGrid(gamePane);
        updateCellCount();
    }

    @FXML
    private void randomizeButtonClicked(ActionEvent e) {
        lifeGrid.randomize();
        updateCellCount();
    }

    @FXML
    private void iterateButtonClicked(ActionEvent e) {
        lifeGrid.iterate();
        updateCellCount();
    }

    /**
     * Updates the count of alive and dead cells in a LifeGrid (There's got to be a more efficient
     * way to do this?)
     */
    private void updateCellCount() {
        List<List<Cell>> cells = lifeGrid.getCells();
        int aliveCells = 0;
        int deadCells = 0;
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                if (cell.isAlive()) {
                    aliveCells++;
                } else {
                    deadCells++;
                }
            }
        }
        numAliveCells.setText("" + aliveCells);
        numDeadCells.setText("" + deadCells);
    }

    @FXML
    private void mouseClicked(MouseEvent e) {
        // calls the method to get a cell at a specific location dependant on the scale of the
        // specific LifeGrid
        Cell cell = lifeGrid.getCellAtLocation((int) (e.getX()/Cell.SCALE), (int) (e.getY()/Cell.SCALE));
        if (cell.isAlive()) {
            cell.setAlive(false);
        } else {
            cell.setAlive(true);
        }
        cell.updateColors();
    }

    @FXML
    private void iterate10StepsButtonClicked(ActionEvent e) {
        final int KEY_FRAME_DURATION = 100; // duration between each KeyFrame (in milliseconds)
        final int STEPS = 10;
        final Timeline timeline = new Timeline();
        // create a new KeyFrame every 100
        for (int i = 0; i <= KEY_FRAME_DURATION*STEPS; i+=KEY_FRAME_DURATION) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i), event -> {
                iterateButtonClicked(e);
                updateCellCount();
            }));
            updateCellCount();
        }
        timeline.play();
    }
}
