package boards;
import java.util.Arrays;

import game.Board;
import game.Cell;
import game.Move;

public class TicTacToeBoard extends Board{
    String cells[][];

    public TicTacToeBoard(){
        cells = new String[3][3];
    }
    private void setCell(Cell cell, String symbol){
        cells[cell.getRow()][cell.getCol()] = symbol;
    }


    public String getSymbol(int x, int y){
        return cells[x][y];
    }
    public void print(){
        for(int i=0;i<3;i++){
            System.out.println(Arrays.toString(cells[i]));
        }
    }

    @Override
    public void move(Move move){
        this.setCell(move.getCell(), move.getPlayer().symbol());
    }

    @Override
    public String toString(){
        String answer = "";
        for(int i=0;i<3;i++){
            String temp = "";
            for(int j=0;j<3;j++){
                temp += cells[i][j] +" ";
            }
            answer = temp +"\n";
        }
        return answer;
    }
}
