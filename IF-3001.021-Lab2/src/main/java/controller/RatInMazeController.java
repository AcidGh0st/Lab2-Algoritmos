package controller;

import domain.RatInMaze;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.util.Arrays;

public class RatInMazeController {

    @FXML
    private ComboBox<String> mazeComboBox;

    @FXML
    private TextArea initialMazeTextArea;

    @FXML
    private TextArea solutionTextArea;

    @FXML
    private TableView<int[]> tableViewSolution;

    private int[][] selectedMaze;

    // Laberintos disponibles
    private final int[][] maze1 = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
    };

    private final int[][] maze2 = {
            {1, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 1},
            {1, 0, 0, 1, 1, 1, 1, 0},
            {1, 0, 0, 1, 1, 0, 1, 1},
            {1, 0, 0, 1, 1, 0, 0, 1},
    };

    private final int[][] maze3 = {
            {1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 1, 1, 1, 1, 0},
            {1, 0, 0, 1, 1, 0, 1, 1},
            {1, 0, 0, 1, 1, 0, 0, 1},
    };

    @FXML
    public void initialize() {
        ObservableList<String> mazeOptions = FXCollections.observableArrayList("Laberinto 1", "Laberinto 2", "Laberinto 3");
        mazeComboBox.setItems(mazeOptions);
        mazeComboBox.setOnAction(event -> {
            String selectedMazeName = mazeComboBox.getSelectionModel().getSelectedItem();
            switch (selectedMazeName) {
                case "Laberinto 1":
                    selectedMaze = maze1;
                    break;
                case "Laberinto 2":
                    selectedMaze = maze2;
                    break;
                case "Laberinto 3":
                    selectedMaze = maze3;
                    break;
            }
            displayMaze(selectedMaze);
        });
    }

    private void displayMaze(int[][] maze) {
        if (maze == null) {
            return;
        }
        clearTableView();

        setupTableViewColumns(maze[0].length);

        initialMazeTextArea.setText(convertToString(maze));

        RatInMaze rat = new RatInMaze(maze.length);
        boolean solved = rat.solveMaze(maze);
        if (solved) {
            int[][] solution = rat.getSolution();
            solutionTextArea.setText(convertToString(solution));
            tableViewSolution.getItems().addAll(Arrays.asList(solution));
        }

    }

    private void setupTableViewColumns(int numColumns) {
        tableViewSolution.getColumns().clear();
        for (int i = 0; i < numColumns; i++) {
            TableColumn<int[], Integer> column = new TableColumn<>("Column " + (i + 1));
            final int columnIndex = i;
            column.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue()[columnIndex]));
            tableViewSolution.getColumns().add(column);
        }
    }

    private void clearTableView() {
        tableViewSolution.getItems().clear();
        tableViewSolution.getColumns().clear();
    }

    private String convertToString(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : matrix) {
            for (int cell : row) {
                stringBuilder.append(cell).append("  ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
