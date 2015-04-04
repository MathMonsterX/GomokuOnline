package gomokuonline;

import javax.swing.JFrame;
import java.awt.Container;

/**
 *
 * @author clarissapendleton
 */
public class GomokuOnline {
    JFrame logInFrame;
    JFrame registerFrame;
    JFrame mainMenuFrame;
    JFrame onlineMenuFrame;
    JFrame gameFrame;
    JFrame statsFrame;
    JFrame chooseAIFrame;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GomokuOnline game = new GomokuOnline();
        game.startGame();
        
    }
    
    public void startGame(){
        logInFrame = new JFrame( );
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LogInView view = new LogInView();
        logInFrame.setContentPane(view);
        logInFrame.pack();
        logInFrame.setVisible(true);    
    }
    
    public void openRegister(Container content){
        registerFrame = new JFrame( );
        registerFrame.setContentPane(content);
        registerFrame.pack();
    }
    public void openMainMenu(Container content){
        mainMenuFrame = new JFrame( );
        mainMenuFrame.setContentPane(content);
        mainMenuFrame.pack();
    }
    public void openOnlineMenu(Container content){
        onlineMenuFrame = new JFrame( );
        onlineMenuFrame.setContentPane(content);
        onlineMenuFrame.pack();
    }
    public void openGame(Container content){
        gameFrame = new JFrame( );
        gameFrame.setContentPane(content);
        gameFrame.pack();
    }
    public void openStats(Container content){
        statsFrame = new JFrame( );
        statsFrame.setContentPane(content);
        statsFrame.pack();
    }
    public void openChooseAI(Container content){
        chooseAIFrame = new JFrame( );
        chooseAIFrame.setContentPane(content);
        chooseAIFrame.pack();
    }
    
}
