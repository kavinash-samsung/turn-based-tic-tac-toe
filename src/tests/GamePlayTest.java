package tests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.GameState;
import game.Move;
import game.Player;

public class GamePlayTest {

    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;
    @Before
    public void setup(){
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }
    @Test 
    public void checkForRowWin(){
        Board board = gameEngine.start("tictactoe");
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.gameState(board).isOver());
        Assert.assertEquals(ruleEngine.gameState(board).getWinner(), "X");
    }

    @Test 
    public void checkForColWin(){
        Board board = gameEngine.start("tictactoe");
        int[][] moves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.gameState(board).isOver());
        Assert.assertEquals(ruleEngine.gameState(board).getWinner(), "X");
    }
    
    @Test 
    public void checkForDiagWin(){
        Board board = gameEngine.start("tictactoe");
        int[][] moves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.gameState(board).isOver());
        Assert.assertEquals(ruleEngine.gameState(board).getWinner(), "X");
    }
    
    @Test 
    public void checkForRevDiagWin(){
        Board board = gameEngine.start("tictactoe");
        int[][] moves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.gameState(board).isOver());
        Assert.assertEquals(ruleEngine.gameState(board).getWinner(), "X");
    }

    @Test 
    public void checkForComputerWin(){
        Board board = gameEngine.start("tictactoe");
        int[][] moves = new int[][]{{2, 2}, {1, 1}, {2, 0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.gameState(board).isOver());
        Assert.assertEquals(ruleEngine.gameState(board).getWinner(), "O");
    }

    // @Test 
    // public void checkForDrawWin(){
    //     Board board = gameEngine.start("tictactoe");
    //     int[][] moves = new int[][]{{, 0}, {1, 1}, {2, 0}};
    //     playGame(board, moves);
    //     Assert.assertTrue(ruleEngine.gameState(board).isOver());
    //     Assert.assertEquals(ruleEngine.gameState(board).getWinner(), "O");
    // }

    private void playGame(Board board, int[][] moves){
        int row, col;
        int next = 0;
        Player computer = new Player("O"), opponent = new Player("X");
        while (!ruleEngine.gameState(board).isOver()) {
            System.out.println("Make your move");
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move oppMove = new Move(new Cell(row, col), opponent);
            gameEngine.move(board, opponent, oppMove);
            GameState gameState = ruleEngine.gameState(board);
            if(gameState.isOver()){
                break;
            }
            Move computerMove = aiEngine.suggestMove(computer, board);
            gameEngine.move(board, computer, computerMove);
        }
    }
}
