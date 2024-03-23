package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ucr.lab.HelloApplication;

import java.io.IOException;

public class HelloController {
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text messageText;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void home(ActionEvent event) {
        this.messageText.setText("Laboratory 2");
        this.bp.setCenter(ap);
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void knightTourOnAction(ActionEvent actionEvent) {
        loadPage("knightTour.fxml");
    }

    @FXML
    public void sudokuSolverOnAction(ActionEvent actionEvent) {
        loadPage("sudokuSolver.fxml");
    }

    @FXML
    public void sudokuSolverOnMousePressed(Event event) {
        this.messageText.setText("Loading Sudoku Test");
        this.bp.setCenter(ap);
    }

    @FXML
    public void nQueenOnMousePressed(Event event) {
        this.messageText.setText("Loading N Queen Test");
        this.bp.setCenter(ap);
    }

    @FXML
    public void ratInMazeOnAction(ActionEvent actionEvent) {
        loadPage("ratInMaze.fxml");
    }
    @FXML
    public void nQueenOnAction(ActionEvent actionEvent) {
        loadPage("nQueenProblem.fxml");
    }

    @FXML
    public void knapsackOnAction(ActionEvent actionEvent) {
        loadPage("knapsack.fxml");
    }

    @FXML
    public void ratInMazeOnMousePressed(Event event) {
        this.messageText.setText("Loading Rat in Maze Test");
        this.bp.setCenter(ap);
    }

    @FXML
    public void knightTourOnMousePressed(Event event) {
        this.messageText.setText("Loading Knight Tour Test");
        this.bp.setCenter(ap);
    }

    @FXML
    public void knapsackOnMousePressed(Event event) {
        this.messageText.setText("Loading Knapsack Test");
        this.bp.setCenter(ap);
    }
}