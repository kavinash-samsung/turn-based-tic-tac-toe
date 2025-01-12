package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

public class RuleEngine {
    public GameState gameState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard)board;

            boolean rowComplete = false;
            String firstCharacter ="-";
            board1.print();
            try{
                for(int i=0;i<3;i++){
                    firstCharacter = board1.getCell(i,0);
                    rowComplete = firstCharacter != null;
                    for(int j=0;j<3;j++){
                        if(firstCharacter != null && (board1.getCell(i, j) == null || !board1.getCell(i,j).equals(firstCharacter))){
                            rowComplete = false;
                            break;
                        }
                    }
                    if(rowComplete){
                        break;
                    }
                }
            } catch(NullPointerException n){
                System.out.println(firstCharacter);
                System.out.println(rowComplete);
                throw new NullPointerException();
            }
            if(rowComplete){
                System.out.println("Result by row complete");
                return new GameState(true, firstCharacter);
            }

            boolean colComplete = false;
            for(int i=0;i<3;i++){
                firstCharacter = board1.getCell(0,i);
                colComplete = firstCharacter != null;;
                for(int j=0;j<3;j++){
                    if(firstCharacter != null && (board1.getCell(j, i)== null || !board1.getCell(j,i).equals(firstCharacter))){
                        colComplete = false;
                        break;
                    }
                }
                if(colComplete){
                    break;
                }
                
            }
            if(colComplete == true){
                System.out.println("Result by col complete");
                return new GameState(true, firstCharacter);
            }
            
            firstCharacter = board1.getCell(0,0);
            boolean diagComplete = firstCharacter != null;;
            for(int i=0;i<3;i++){
                if(firstCharacter != null && (board1.getCell(i,i) == null || !board1.getCell(i,i).equals(firstCharacter))){
                    diagComplete = false;
                    break;
                }
            }
            if(diagComplete == true){
                System.out.println("Result by diag complete");
                return new GameState(true, firstCharacter);
            }
            firstCharacter = board1.getCell(0,2);
            boolean revDiagComplete = firstCharacter != null;;
            for(int i=0;i<3;i++){
                if(firstCharacter != null && (board1.getCell(i, 2-i) == null || !board1.getCell(i,2-i).equals(firstCharacter))){
                    revDiagComplete = false;
                    break;
                }
            }
            if(revDiagComplete){
                System.out.println("result by rev diag complete");
                return new GameState(true, firstCharacter);
            }
            int cellRemaining = 9;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.getCell(i,j) != null){
                        cellRemaining--;
                    }
                }
            }
            if(cellRemaining>0){
                return new GameState(false, "-");
            }else{
                return new GameState(true, "-");
            }

        }
        return new GameState(false, "-");
    }
    
}
