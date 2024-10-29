package com.assignment.tictactoe.service;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BoardImpl implements Board {
    private Piece[][] pieces;
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        initializeBoard();
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public void initializeBoard() {
        pieces = new Piece[3][3];
        for (Piece[] piece : pieces) {
            Arrays.fill(piece, Piece.EMPTY);
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        if (row < 0 || row >= pieces.length || col < 0 || col >= pieces[0].length) {
            return false;
        } if (pieces[row][col] != Piece.EMPTY) {
            return false;
        }
        return true;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        if (isLegalMove(row, col)) {
            pieces[row][col] = piece;
        }
    }

    @Override
    public Winner checkWinner() {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][0] != Piece.EMPTY) {
                return new Winner(pieces[i][0], i, 0, i, 1, i, 2);
            }
            if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[0][i] != Piece.EMPTY) {
                return new Winner(pieces[0][i], 0, i, 1, i, 2, i);
            }
        }

        if (pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2] && pieces[0][0] != Piece.EMPTY) {
            return new Winner(pieces[0][0], 0, 0, 1, 1, 2, 2);
        }
        if (pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0] && pieces[0][2] != Piece.EMPTY) {
            return new Winner(pieces[0][2], 0, 2, 1, 1, 2, 0);
        }

        return null;
    }

    @Override
    public void printBoard() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                System.out.print(pieces[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
