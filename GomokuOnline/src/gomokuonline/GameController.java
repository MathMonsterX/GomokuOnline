
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
    
    public void updateBoard(int row, int column, char playerChar){
        view.updateBoard(row, column, playerChar);
    }
    
    public void endGame(int row, int column, String status, char playerChar){
        view.endGame(row, column, status, playerChar);
    }
    
    public void makeMove(int row, int col){
        if(this.model.isAIGame()){
            model.AIGamePlay(row, col, 'O');
        }
        else
            model.makeMove(row, col);
    }
    
    public void makeMoveToAI(int row, int col){
        model.AIGamePlay(row, col, 'O');
    }
    public void setEndMoveEnabled(boolean enabled){
        this.view.setbtnEndMOveEnabled(enabled);
    }
     public char getPlayerChar(){
        return this.model.getPlayerChar();
    }

    public void setPlayerChar(char x) {
        this.model.setPlayerChar(x);    
    }
}

