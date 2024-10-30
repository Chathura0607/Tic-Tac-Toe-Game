package com.assignment.tictactoe.service;

public class AIPlayer extends Player {
//    private Random random;
    BoardImpl board;

    public AIPlayer(Board board) {
        super(board);
        this.board = (BoardImpl) board;
//        this.random = new Random();
    }

//    @Override
//    public void move(int row, int col) {
//        int randomRow, randomCol;
//        do {
//            randomRow = random.nextInt(3);
//            randomCol = random.nextInt(3);
//        } while (!board.isLegalMove(randomRow, randomCol)); {
//            board.updateMove(randomRow, randomCol, Piece.O);
//        }
//    }

    @Override
    public void move(int row, int col) {
        if (board.isLegalMove(row, col)) {
            board.updateMove(row, col, Piece.O);
        }
    }

    public void findBestMove() {
        int bestMove = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;
        Piece[][] pieces = board.getPieces();

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    pieces[i][j] = Piece.O;
                    int moveValue = minimax(pieces, 0, false);
                    pieces[i][j] = Piece.EMPTY;
                    if (moveValue > bestMove) {
                        bestRow = i;
                        bestCol = j;
                        bestMove = moveValue;
                    }
                }
            }
        } if (bestRow != -1 && bestCol != -1) {
            move(bestRow, bestCol);
        }
    }

    private int minimax(Piece[][] pieces, int depth, boolean isMaximizing) {
        Winner winner = board.checkWinner();
        if (winner != null) {
            if (winner.getWinningPiece() == Piece.O) {
                return 10 - depth;
            } else if (winner.getWinningPiece() == Piece.X) {
                return depth - 10;
            }
        }
        if (board.isBoardFull()) {
            return 0;
        }

        if (isMaximizing) {
            int bestValue = Integer.MIN_VALUE;
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if (pieces[i][j] == Piece.EMPTY) {
                        pieces[i][j] = Piece.O;
                        bestValue = Math.max(bestValue, minimax(pieces, depth + 1, false));
                        pieces[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if (pieces[i][j] == Piece.EMPTY) {
                        pieces[i][j] = Piece.X;
                        bestValue = Math.min(bestValue, minimax(pieces, depth + 1, true));
                        pieces[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestValue;
        }
    }
}
