// Name: Nicolas Azzi and Nolan O'Rourke

import java.awt.Color;
import Chess_Pieces.*;

public class Player
{
    private int playerNumber;
    private boolean castled; //switching the rook and king, can only do it once
    private String playerName;
    private Color playerColor;
    private King king;
    private Queen queen[];
    private Bishop bishop[];
    private Knight knight[];
    private Rook rook[];
    private Pawn pawn[];
    private int piecesTaken;
    private int piecesLost;

    public Player()
    {
        playerNumber = 0;
        playerName = new String("");
        playerColor = Color.MAGENTA;
        castled = false;


        piecesLost = 0;
        piecesTaken = 0;

    }
    public Player(int teamnum, String name)
    {
        playerNumber = teamnum;
        playerName = name;
        if(teamnum == 1)
            playerColor = Color.BLACK;
        else
            playerColor = Color.WHITE;

        castled = false;
    }
    public Player(int teamnum, String name, Color c)
    {
        playerNumber = teamnum;
        playerName = name;
        playerColor = c;
        castled = false;
    }
    public void takeTurn()
    {

    }

    public int getPlayerNumber(){return playerNumber;}
    public int getPiecesTaken() {return piecesTaken;}
    public int getPiecesLost() {return piecesLost; }
    public void setPiecesTaken(int t) {piecesTaken = t;}
    public void setPiecesLost(int l) {piecesLost = l;}
    public void IncrementTaken(){piecesTaken++;}
    public void IncrementLost(){piecesLost++;}
    
}