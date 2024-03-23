package controller;

import domain.Item;
import domain.Knapsack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class KnapsackController {
    @FXML
    private ComboBox<Double> comBox;
    @FXML
    private TableView<Item> tableView_Solution;
    @FXML
    private TableView<Item> tableView_List;
    @FXML
    private TableColumn<Item, String> list_name;
    @FXML
    private TableColumn<Item, Double> list_Value;
    @FXML
    private TableColumn<Item, Double> list_Weigth;
    @FXML
    private TableColumn<Item, String> name_solution;
    @FXML
    private TableColumn<Item, Double> value_solution;
    @FXML
    private TableColumn<Item, Double> weight_solution;
    @FXML
    private TableColumn<Item, Double> valueWeight_solution;

    private ObservableList<Item> itemList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        itemList.addAll(
                new Item("SmartTV", 1000, 12),
                new Item("Smart TV 65 pulg", 800, 10),
                new Item("Smarte TV 32 pulg", 600, 7),
                new Item("PS5", 600, 1.3),
                new Item("LIBRO JAVA", 25, 0.7),
                new Item("LIBRO ANSI C", 40, 1.2),
                new Item("LIBRO C++", 45, 0.9),
                new Item("LIBRO PYTHON", 60, 1.3),
                new Item("Samsung", 800, 0.5),
                new Item("Huawei", 700, 0.4),
                new Item("iPhone 8", 1000, 0.3),
                new Item("iPone 10", 800, 0.3),
                new Item("iPone 13", 700, 0.3),
                new Item("Xbox", 600, 1.5),
                new Item("LapTop HP", 500, 3),
                new Item("MacBook Air", 1300, 0.7),
                new Item("Drone", 200, 4.5),
                new Item("Impresora 3D", 1200, 5),
                new Item("MacBook Air", 1300, 0.7),
                new Item("Gafas  Realidad virtual", 1700, 0.4),
                new Item("Proyector", 300, 1.2)
        );

        tableView_List.setItems(itemList);

        list_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        list_Value.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
        list_Weigth.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());

        comBox.getItems().addAll(14.5, 24.5, 36.5, 45.5, 50.2);

        name_solution.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        value_solution.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
        weight_solution.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());

        comBox.setOnAction(event -> {
            Double selectedWeight = comBox.getValue();
            if (selectedWeight != null) {
                updateSolution(selectedWeight);
            }
        });
    }

    private void updateSolution(Double capacity) {
        // Llamar al método solve de la clase Knapsack con la capacidad seleccionada
        Knapsack knapsack = new Knapsack(itemList.toArray(new Item[0]), capacity);
        Item[] solutionItems = knapsack.solve();

        // Limpiar los ítems anteriores en la tabla de solución
        tableView_Solution.getItems().clear();

        // Agregar nuevos ítems de solución a la tabla de solución
        tableView_Solution.getItems().addAll(solutionItems);
    }
}
