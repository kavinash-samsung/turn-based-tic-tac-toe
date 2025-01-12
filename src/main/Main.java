import java.util.Scanner;

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.GameState;
import game.Move;
import game.Player;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("tictactoe");
        int count = 1;
        Player computer = new Player("O"), opponent = new Player("X");
        while (!ruleEngine.gameState(board).isOver()) {
            System.out.println("turn count "+count++);
            System.out.println("Make your move");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row, col), opponent);
            gameEngine.move(board, opponent, oppMove);
            GameState gameState = ruleEngine.gameState(board);
            if(gameState.isOver()){
                break;
            }
            Move computerMove = aiEngine.suggestMove(computer, board);
            gameEngine.move(board, computer, computerMove);
        }
        GameState gameState = ruleEngine.gameState(board);
        System.out.println("Game finished ");
        System.out.println(gameState.isOver()+" "+gameState.getWinner());
        if(gameState.getWinner().equals(computer.symbol())){
            System.out.println("Computer wins the game");
        }else if(gameState.getWinner().equals(opponent.symbol())){
            System.out.println("Player wins the game");
        }else{
            System.out.println("Game Draw");
        }
        scanner.close();

    }
}