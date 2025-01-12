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
            Move suggestion;
            if(startingMoves(board1, 3)){
                suggestion = getBasicMove(board1, computer);
            }else {
                suggestion = getSmartMove(board1, computer);
            }
            if(suggestion != null){
                return suggestion;
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException("Board given does not supported");
    }
    public Move getBasicMove(TicTacToeBoard board, Player player){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i, j)==null){
                    System.out.println("Basic Move");
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
                    TicTacToeBoard copyBoard = board.copy();
                    copyBoard.move(move);
                    if(ruleEngine.gameState(copyBoard).isOver()){
                        System.out.println("Victorious move");
                        return move;
                    }
                }
            }
        }
        
        // Defensive Move
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i, j)==null){
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard copyBoard = board.copy();
                    copyBoard.move(move);
                    
                    if(ruleEngine.gameState(copyBoard).isOver()){
                        System.out.println("Defensive move");
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }
        return getBasicMove(board, player);
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
