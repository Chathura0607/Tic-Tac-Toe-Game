package com.assignment.tictactoe.service;

public class BoardImpl {
    private Piece[][] pieces;
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
    }
}
