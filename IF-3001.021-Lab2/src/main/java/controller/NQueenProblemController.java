package controller;

import domain.NQueenProblem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class NQueenProblemController
{
    @javafx.fxml.FXML
    private TableView tableView;
    @javafx.fxml.FXML
    private TextArea textArea;
    @javafx.fxml.FXML
    private ComboBox comboBox;
    private String nQueenData;
    //la data para el tableView

    @javafx.fxml.FXML
    public void initialize() {
        comboBox.setItems(boardOl);
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("4x4")) {
                nQueenProblemTest(4);
                showTableView(4);
            } else if (newValue.equals("8x8")) {
                nQueenProblemTest(8);
                showTableView(8);
            }
        });
    }

    private void showTableView(int n) {
        tableView.getColumns().clear(); //limpio las columnas
        for (int i = 0; i < n; i++) {
            final int colIndex = i;
            TableColumn<List<String>, String> column = new TableColumn<>("Col" + (i+1));//Cantidad de columnas
            column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(colIndex)));//Valor tipo String
            tableView.getColumns().add(column);//agrego columnas
            tableView.setItems(getData(n));

        }



    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ObservableList<List<String>> getData(int n) {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        nQueenData = nQueenData.replaceAll("\n",""); //para eliminar los saltos de linea
        String a[] = nQueenData.split(" ");
        int count = 0;
        List<String> info = new ArrayList<>();
        for (int i = 0; i < n*n; i++) {
            info.add(a[i]);
            if(count++==n-1){
                data.add(info);
                info = new ArrayList<>();
                count = 0;
            }
        }
        return data;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void nQueenProblemTest(int n) {
        NQueenProblem nQueen = new NQueenProblem();
        String result = nQueen.solveNQueen(n); // Utiliza el parámetro 'n'
        this.textArea.setText(result);
        this.nQueenData = result;
    }


    ObservableList<String>boardOl = FXCollections.observableArrayList("4x4", "8x8");
}


