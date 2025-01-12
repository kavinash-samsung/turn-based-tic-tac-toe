package api;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class AIEngine {
    private RuleEngine ruleEngine;
    public AIEngine(){
        this.ruleEngine = new RuleEngine();
    }
    public Move suggestMove(Player computer, Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard)board;
            if(startingMoves(board1, 4)){
                return getBasicMove(board1, computer);
            }else {
                return getSmartMove(board1, computer);
            }
        }
        throw new IllegalStateException("Board given does not supported");
    }
    public Move getBasicMove(TicTacToeBoard board, Player player){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i, j)==null){
                    return new Move(new Cell(i, j), player);
                }
            }
        }
        return null;
    }
    public Move getSmartMove(TicTacToeBoard board, Player player){

        // victorious moves
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i, j)==null){
                    Move move = new Move(new Cell(i, j), player);
                    board.move(move);
                    if(ruleEngine.gameState(board).isOver()){
                        return move;
                    }
                }
            }
        }
        
        // Defensive Move
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i, j)==null){
                    Move move = new Move(new Cell(i, j), new Player(player.symbol().equals("X")?"O":"X"));
                    board.move(move);
                    if(ruleEngine.gameState(board).isOver()){
                        return move;
                    }
                }
            }
        }
        return null;
    }
    public boolean startingMoves(TicTacToeBoard board, int threshold){
        int count = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i, j) != null){
                    count++;
                }
            }
        }
        return count < threshold;
    }
}
