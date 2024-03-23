package controller;


import domain.KnightTourProblem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class KnightTourController {

    @FXML
    private TableView<int[]> tableView;

    @FXML
    private TextArea textArea;

    @FXML
    public void initialize() {
        KnightTourProblem knightTour = new KnightTourProblem();
        knightTour.resolverTour();

        // Obtener la solución del recorrido del caballo como una cadena
        String tourSolution = obtenerSolucionComoCadena(knightTour);
        textArea.setText(tourSolution);

        // Llenar el TableView con la solución del recorrido del caballo
        llenarTableView(knightTour.getTablero());
    }

    private String obtenerSolucionComoCadena(KnightTourProblem knightTour) {
        StringBuilder sb = new StringBuilder();
        int[][] tablero = knightTour.getTablero();
        for (int[] fila : tablero) {
            for (int casilla : fila) {
                sb.append(casilla).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void llenarTableView(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            final int rowIndex = i;
            TableColumn<int[], Integer> columna = new TableColumn<>("Fila " + (i + 1));
            columna.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue()[rowIndex]).asObject());
            tableView.getColumns().add(columna);
        }

        for (int[] fila : tablero) {
            tableView.getItems().add(fila);
        }
    }
}
