import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MoveGrid extends JPanel
{
    private int width;

    MoveGrid ()
    {
        setLayout(new GridLayout(8, 2));

        // Adds a JPanel into the grid (gridSpot will be it's own separate class in this file)
        JPanel gridSpot;
        for (int x = 0; x < 8; ++x)
            for (int y = 0; y < 2; ++y)
            {
                gridSpot = new JPanel();
                gridSpot.setBorder(BorderFactory.createLineBorder(Color.black));
                add(gridSpot);
            }
    }
    
    // Dynamically resize grid
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setPreferredSize(new Dimension((int) (width * 0.175), getPreferredSize().height));
    }
    
    public void setWidth(int w) { width = w; }
    
}
