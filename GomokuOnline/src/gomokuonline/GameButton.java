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
    private boolean selected;
    
    public GameButton()
    {
        this.selected = false;
    }
    public GameButton( int row, int col )
    {
        this.row = row;
        this.col = col;
        this.selected = false;
    }
    
    public GameButton(int row, int col, String text){
        super(text);
        this.row = row;
        this.col = col; 
        this.selected = false;
        
    
    }
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
    
    /**
     * Returns the status of the selected condition of the button.
     * @return true if selected, false if not
     */
    @Override
    public boolean isSelected()
    {
        return selected;
    }
    
    
    /**
     * Sets the state of the selected condition of the button
     * @param selected status of button
     */
    @Override
    public void setSelected( boolean selected )
    {
        this.selected = selected;
    }

}
