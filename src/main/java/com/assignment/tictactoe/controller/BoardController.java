package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;

public class BoardController implements BoardUI {
    private HumanPlayer humanPlayer;
    private AIPlayer aiPlayer;
    private BoardImpl board;

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private Button[][] buttons;

    public BoardController() {
        board = new BoardImpl(this);
        humanPlayer = new HumanPlayer(board);
        aiPlayer = new AIPlayer(board);
    }

    @FXML
    public void initialize() {
        buttons = new Button[][]{
                {btn1, btn2, btn3},
                {btn4, btn5, btn6},
                {btn7, btn8, btn9}
        };
    }

    @FXML
    void btnOnAction(ActionEvent event) {
        Button clickButton = (Button) event.getSource();
        String id = clickButton.getId();

//        String cell = id.substring(3);
        int cell = Integer.parseInt(id.substring(3)) - 1;

        int row = cell / 3;
        int coll = cell % 3;

        humanPlayer.move(row, coll);
        clickButton.setDisable(true);
        updateUI();

        Winner winner = board.checkWinner();
        if (winner != null) {
            notifyWinner(winner);
        } else if (board.isBoardFull()) {
            notifyTie();
        } else {
//            aiPlayer.move(row, coll);
            aiPlayer.findBestMove();
            updateUI();
            board.printBoard();

            winner = board.checkWinner();
            if (winner != null) {
                notifyWinner(winner);
            } else if (board.isBoardFull()) {
                notifyTie();
            }
        }

//        int row = -1;
//        int col = -1;
//
//        int count = 1;


//        L1:
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (count == Integer.parseInt(cell)) {
//                    row = i;
//                    col = j;
//                    break L1;
//                }
//                count++;
//            }
//        }
//        humanPlayer.move(row, col);
//        aiPlayer.move(row, col);
//        board.printBoard();
//        updateUI();
//
//        if (board.checkWinner() != null) {
//            Winner winner = board.checkWinner();
//            notifyWinner(winner);
//        } else if (board.isBoardFull()) {
//            notifyTie();
//        }
    }

    private void notifyTie() {
        System.out.println("Tie");
        showAlert("Tie");
    }

    private void updateUI() {
        for (int i = 0; i < board.getPieces().length; i++) {
            for (int j = 0; j < board.getPieces()[i].length; j++) {
                update(j, i, board.getPieces()[i][j]);
            }
        }
    }

    @Override
    public void update(int col, int row, Piece piece) {
        Button buttonUpdate = buttons[row][col];
        Button[][] buttons = {{btn1, btn2, btn3}, {btn4, btn5, btn6}, {btn7, btn8, btn9}};

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (piece == Piece.X) {
                    buttonUpdate.setText("X");
                    buttonUpdate.setDisable(true);
//                    buttons[row][col].setText("X");
                } else if (piece == Piece.O) {
                    buttonUpdate.setText("O");
                    buttonUpdate.setDisable(true);
//                    buttons[row][col].setText("O");
                } else {
                    buttonUpdate.setText(" ");
                    buttonUpdate.setDisable(false);
//                    buttons[row][col].setText(" ");
                }
            }
        }
    }

    @Override
    public void notifyWinner(Winner winner) {
        System.out.println(winner.getWinningPiece() + " is Winner");
        showAlert(winner.getWinningPiece() + " is Winner");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setOnCloseRequest((DialogEvent event) -> {
            board.initializeBoard();
            updateUI();
        });
        alert.showAndWait();
    }
}
