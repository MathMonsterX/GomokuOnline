
package gomokuonline;

import javax.swing.JFrame;

/**
 * This class is the controller in the MVC design. It controls game play.
 * @author clarissapendleton
 */
public class GameController {
    private GameModel model;
    private GameView view;
    private JFrame gameViewFrame;
    
    /**
     * This creates the game view.
     */
    public void createView(){
        this.view = new GameView();
        gameViewFrame = new JFrame();
        //gameViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameViewFrame.setContentPane(view);
        gameViewFrame.setVisible(true);
        view.setController(this);
        view.setFrame(gameViewFrame);
        view.setupBoard(model.getSize(), model.getSize());
        gameViewFrame.pack();

    }
    /**
     * This sets the GameModel.
     * @param model the GameModel
     */
    public void setModel(GameModel model){
        this.model=model;
    }
    /**
     * This updates the board, specified by the row and column of the button.
     * @param row the row of the button 
     * @param column the column of the button
     * @param playerChar the player's character
     */
    public void updateBoard(int row, int column, char playerChar){
        view.updateBoard(row, column, playerChar);
    }
    
    /**
     * This end the game.
     * @param row the row of the last move
     * @param column the column of the last move
     * @param status the status of the player (Winner or Loser)
     * @param playerChar the players character
     */
    public void endGame(int row, int column, String status, char playerChar){
        boolean wonGame = true;
        if(status.equals("Loser"))
            wonGame = false;
        this.model.sendGameStats(wonGame);
        view.endGame(row, column, status, playerChar);
    }
    
    /**
     * Checks to see if the opponent is a human or AI and passes on the move 
     * to the correct method based on the opponent.
     * @param row the row of the move
     * @param col the column of the move
     */
    public void makeMove(int row, int col){
        if(this.model.isAIGame()){
            model.AIGamePlay(row, col, 'O');
        }
        else
            model.makeMove(row, col);
    }
    
    /**
     * Enables or disables the End Move button
     * @param enabled whether to enable or disable the button
     */
    public void setEndMoveEnabled(boolean enabled){
        this.view.setbtnEndMOveEnabled(enabled);
    }
    
    /**
     * Returns this player's character ('X' or 'O')
     * @return the player's character
     */
     public char getPlayerChar(){
        return this.model.getPlayerChar();
    }

     /**
      * Sets this player's character to x
      * @param x the player's character ('X' or 'O')
      */
    public void setPlayerChar(char x) {
        this.model.setPlayerChar(x);    
    }
    
    /**
     * This will set gameController and gameModel to null
     */
    public void setNull(){
        model.setNull();
    }
    /**
    * Returns this user's username
    * @return the username
    */
    public String getUsername() {
        return model.getUsername();
    }
}

