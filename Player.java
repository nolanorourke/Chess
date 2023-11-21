// Name: Nicolas Azzi and Nolan O'Rourke

import java.awt.Color;

public class Player
{
    private int playerNumber;
    private String playerName;
    private Color playerColor;
    private King king;
    private Queen queen;
    private Bishop bishop[];
    private Knight knight[];
    private Rook rook[];
    private Pawn pawn[];

    public Player()
    {
        playerNumber = 0;
        playerName = new String("");
        playerColor = Color.MAGENTA;
    }
    public Player(int teamnum, String name)
    {
        playerNumber = teamnum;
        playerName = name;
        if(teamnum == 1)
            playerColor = Color.BLACK;
        else
            playerColor = Color.WHITE;
    }
    public Player(int teamnum, String name, Color c)
    {
        playerNumber = teamnum;
        playerName = name;
        playerColor = c;
    }
    
}