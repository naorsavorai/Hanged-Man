package com.example.maman13;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Line stage1, stage3, stage4, stage5, stage6, stage7;
    @FXML
    private Circle stage2;
    @FXML
    private TextField input;
    @FXML
    private Pane board;
    @FXML
    private Button EnterB;


    Game hangedManInstance = new Game();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EnterB.setDisable(true);
        input.textProperty().addListener((obs, oldText, newText) -> {
            EnterB.setDisable(newText.length() != 1 || !newText.matches("[a-zA-Z]") || !hangedManInstance.getIsStarted());
        });

    }


    @FXML
    public void newGame(ActionEvent e) {
        hangedManInstance.startOver();
        renderHangedMan();
        renderCharLabels();

    }


    public void checkChar(ActionEvent e) {
        try {
            if (input.getText().length() != 1 || !input.getText().matches("[a-zA-Z]") || !hangedManInstance.getIsStarted()) {
                throw new Exception("invalid input");
            }
            String parsedInput = input.getText().toUpperCase();
            String parsedTarget = hangedManInstance.getTargetWord().toUpperCase();

            if (parsedTarget.contains(parsedInput)) {
                char c = parsedInput.charAt(0);
                renderCharLabels(c, parsedTarget.indexOf(parsedInput));
                hangedManInstance.updateGuess(c, parsedTarget.indexOf(parsedInput));
                if (hangedManInstance.checkWin()) {
                    endGame(true);
                }
            } else {
                if (hangedManInstance.nextStage()) {
                    renderHangedMan();
                } else {
                    renderHangedMan();
                    endGame(false);
                }
            }

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

    }

    private void endGame(boolean win) {
        board.getChildren().clear();
        Label endLabel = new Label();
        endLabel.setStyle("-fx-font: 30 arial;");
        if (win) {
            endLabel.setText("Congratulations, You Won");
        }
        if (!win) {
            endLabel.setText("You Lost, Better Luck Next Time");
        }
        hangedManInstance.setIsStrated(false);
        board.getChildren().add(endLabel);
    }


    private void renderHangedMan() {
        try {
            switch (hangedManInstance.getStage()) {
                case (0) -> {
                    stage1.setVisible(false);
                    stage2.setVisible(false);
                    stage3.setVisible(false);
                    stage4.setVisible(false);
                    stage5.setVisible(false);
                    stage6.setVisible(false);
                    stage7.setVisible(false);
                }
                case (1) -> stage1.setVisible(true);

                case (2) -> stage2.setVisible(true);

                case (3) -> stage3.setVisible(true);

                case (4) -> stage4.setVisible(true);

                case (5) -> stage5.setVisible(true);

                case (6) -> stage6.setVisible(true);

                case (7) -> stage7.setVisible(true);

                default -> throw new Exception("Stages can only be between 0 to 7");


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void renderCharLabels() {
        board.getChildren().clear();
        GridPane newGrid = new GridPane();
        newGrid.setId("charGrid");
        for (int i = 0; i < hangedManInstance.getTargetWord().length(); i++) {
            newGrid.getColumnConstraints().add(new ColumnConstraints(40));
            Label l = new Label();
            l.setMinWidth(25);
            l.setStyle("-fx-border-style:solid  ;  -fx-border-width:0 0 2 0 ;-fx-border-color: black;");
            newGrid.add(l, i, 0);
        }
        newGrid.setAlignment(Pos.CENTER_LEFT);
        board.getChildren().add(newGrid);

    }

    private void renderCharLabels(char c, int j) {
        for (int i = j; i < hangedManInstance.getTargetWord().length(); i++) {
            if (hangedManInstance.getTargetWord().toUpperCase().charAt(i) == c) {
                if (getNodeFromGridPane((GridPane) board.lookup("#charGrid"), i, 0) != null) {
                    getNodeFromGridPane((GridPane) board.lookup("#charGrid"), i, 0).setText(String.valueOf(c).toUpperCase());
                }
            }
        }
    }

    private Label getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row && node instanceof Label) {
                return (Label) node;
            }
        }
        return null;
    }


}