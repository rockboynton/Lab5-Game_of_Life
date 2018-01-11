package boyntonrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Pane gamePane;
    @FXML
    private Button randomize;
    @FXML
    private Button iterate;
    @FXML
    private Label numAliveCells;
    @FXML
    private Label numDeadCells;

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
}
