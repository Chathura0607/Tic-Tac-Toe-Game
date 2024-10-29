package com.assignment.tictactoe.service;

public interface BoardUI {
    void update(int col, int row, Piece piece);
    void notifyWinner(Winner winner);
}
