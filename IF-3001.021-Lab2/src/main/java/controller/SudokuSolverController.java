package controller;

import domain.SudokuSolver;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;

public class SudokuSolverController {

    @FXML
    private TextArea txtAreaSolution;
    @FXML
    private TableView<List<String>> tableViewSudoku;
    @FXML
    private ComboBox<String> comboBoxSudoku;
    @FXML
    private TextArea txtAreaBoard;

    private SudokuSolver sudokuSolver;

    private final int[][] board1 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    private final int[][] board2 = {
            {3, 0, 2, 0, 0, 6, 5, 9, 0},
            {0, 6, 0, 0, 0, 0, 2, 3, 4},
            {0, 4, 0, 0, 0, 2, 0, 1, 6},
            {0, 5, 9, 7, 0, 0, 3, 6, 0},
            {4, 7, 0, 0, 8, 9, 1, 2, 5},
            {0, 0, 6, 2, 0, 0, 7, 0, 0},
            {5, 0, 7, 1, 0, 4, 6, 0, 3},
            {1, 0, 0, 0, 6, 8, 9, 0, 2},
            {6, 0, 0, 0, 9, 0, 4, 5, 0}
    };

    private final int[][] board3 = {
            {0, 0, 3, 0, 2, 0, 6, 0, 0},
            {9, 0, 0, 3, 0, 5, 0, 0, 1},
            {0, 0, 1, 8, 0, 6, 4, 0, 0},
            {0, 0, 8, 1, 0, 2, 9, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 6, 7, 0, 8, 2, 0, 0},
            {0, 0, 2, 6, 0, 9, 5, 0, 0},
            {8, 0, 0, 2, 0, 3, 0, 0, 9},
            {0, 0, 5, 0, 1, 0, 3, 0, 0}
    };

    @FXML
    public void initialize() {
        sudokuSolver = new SudokuSolver(board1);

        comboBoxSudoku.setItems(FXCollections.observableArrayList("Board1", "Board2", "Board3"));//Opciones en comboBox
        comboBoxSudoku.getSelectionModel().selectFirst();//Aparezca primera opción del comboBox
        loadBoard();//Cargar el Board correspondiente

        tableViewSudoku.getColumns().clear();
        for (int i = 0; i < 9; i++) {
            final int colIndex = i;
            TableColumn<List<String>, String> column = new TableColumn<>("Col" + (i + 1));//Crear cada columna
            column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(colIndex)));
            tableViewSudoku.getColumns().add(column);
            tableViewSudoku.setItems(getData());
        }
    }

    //Cargar correctamente los datos al textArea y tableView
    private void loadBoard() {
        // Limpiar TableView y TextAreas
        tableViewSudoku.getItems().clear();
        txtAreaBoard.clear();
        txtAreaSolution.clear();

        //Mostrar el tablero seleccionado
        String selectedBoard = comboBoxSudoku.getSelectionModel().getSelectedItem();
        int[][] selectedBoardArray = null;
        if ("Board1".equals(selectedBoard)) {
            selectedBoardArray = board1;
        } else if ("Board2".equals(selectedBoard)) {
            selectedBoardArray = board2;
        } else if ("Board3".equals(selectedBoard)) {
            selectedBoardArray = board3;
        }
        if (selectedBoardArray != null) {//Verifico que el tablero sea válido
            sudokuSolver.setBoard(selectedBoardArray);//Actualizo el tableView
            updateTableView(); // Método para cargar los datos en el TableView
            updateUI();//Actualizo interfaz
        }
    }

    private void updateTableView() {
        tableViewSudoku.setItems(getData());
    }

    @FXML
    private void SudokuChanged() {
        loadBoard();
    }

    public ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        String sudokuu = sudokuSolver.getSudokuSolution();
        sudokuu = sudokuu.replaceAll("\n", "");
        String[] a = sudokuu.split(" ");
        int count = 0;
        List<String> info = new ArrayList<>();
        for (String s : a) {
            info.add(s);
            if (count++ == 8) {
                data.add(info);
                info = new ArrayList<>();
                count = 0;
            }
        }
        return data;
    }

    //Actualizar textArea
    private void updateUI() {
        this.txtAreaBoard.setText(sudokuSolver.getOriginalBoard());
        this.txtAreaSolution.setText(sudokuSolver.getSudokuSolution());
    }
}