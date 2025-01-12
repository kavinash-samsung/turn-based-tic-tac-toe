package api;

import boards.TicTacToeBoard;
import game.Board;

import game.Move;
import game.Player;

public class GameEngine {

    public Board start(String type){
        if(type.equals("tictactoe"))
        return new TicTacToeBoard();
        throw new IllegalArgumentException("game "+type+" is not supported");
    }
    public void move(Board board, Player player, Move move){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard)board;
            board1.move(move);
        }
    }

}
