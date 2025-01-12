package game;

public class Player {
    private String playerSymbol;
    public Player(String playerSymbol){
        this.playerSymbol = playerSymbol;
    }
    public String symbol(){
        return this.playerSymbol;
    }
    public Player flip(){
        if(this.playerSymbol.equals("X")){
            return new Player("O");
        }else{
            return new Player("X");
        }
    }
}
