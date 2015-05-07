/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuonline;

import javax.swing.JButton;

/**
 *
 * @author Sheyla
 */
public class GameButton extends JButton {
    private int row, col;
    private int selected;
    
    public GameButton()
    {
        this.selected = 0;
    }
    public GameButton( int row, int col )
    {
        this.row = row;
        this.col = col;
        this.selected = 0;
    }
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
    public int getSelected()
    {
        return selected;
    }
    public void setSelected( int selected )
    {
        this.selected = selected;
    }

}
