package boyntonrl;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

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
        Cell cell = lifeGrid.getCellAtLocation((int) (e.getX()/Cell.SCALE), (int) (e.getY()/Cell.SCALE));
        if (cell.isAlive()) {
            cell.setAlive(false);
            cell.updateColors();
        } else {
            cell.setAlive(true);
            cell.updateColors();
        }
    }

    @FXML
    private void iterate10StepsButtonClicked(ActionEvent e) {
        final int FPS = 2;

        //create key frames with a for loop
        Timeline timeline = new Timeline(FPS); // lambda for adding key frames

        timeline.play();

    }
}
