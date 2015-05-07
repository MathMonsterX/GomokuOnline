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
    
    public void setModel(){
        
    }
    
    public void createView(){
        this.view = new GameView();
        gameViewFrame = new JFrame();
        //gameViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameViewFrame.setContentPane(view);
        gameViewFrame.pack();
        gameViewFrame.setVisible(true);
        view.setController(this);
        view.setFrame(gameViewFrame);
    }
    
    public void updateBoard(int row, int column){
        view.updateBoard(row, column);
    }
    
    public void endGame(int row, int column){
        view.endGame(row, column);
    }
    
    public void makeMove(int row, int col){
        model.makeMove(row, col);
    }
}
