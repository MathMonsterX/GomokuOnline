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
}
