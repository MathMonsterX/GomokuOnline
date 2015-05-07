
package gomokuonline;

import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class GameController {
    private GameModel model;
    private GameView view;
    private JFrame gameViewFrame;
    
    public void setModel(GameModel model){
        this.model=model;
    }
    
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
    
    public void updateBoard(int row, int column){
        view.updateBoard(row, column);
    }
    
    public void endGame(int row, int column, String status){
        view.endGame(row, column, status);
    }
    
    public void makeMove(int row, int col){
        model.makeMove(row, col);
    }
    
    public void makeMoveToAI(int row, int col){
        model.AIGamePlay(row, col, 'o');
    }
    public void setEndMoveEnabled(boolean enabled){
        this.view.setbtnEndMOveEnabled(enabled);
    }
     public char getPlayerChar(){
        return this.model.getPlayerChar();
    }
}

