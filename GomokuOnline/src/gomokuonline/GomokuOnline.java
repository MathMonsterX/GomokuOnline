package gomokuonline;

import javax.swing.JFrame;
import java.awt.Container;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    AdminModel model;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GomokuOnline game = new GomokuOnline();
        game.startGame();
        
    }
    
    public void startGame(){
        try {
            model = new AdminModel(InetAddress.getLocalHost().getHostAddress(), 8080);//"10.0.0.7", 8080);
        } catch (UnknownHostException ex) {
            Logger.getLogger(GomokuOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.setGomoku(this);
        logInFrame = new JFrame();
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LogInView view = new LogInView();
        LogInController controller = new LogInController();
        controller.setView(view);
        controller.setModel(model);
        view.setFrame(logInFrame);
        view.setController(controller);
        logInFrame.setContentPane(view);
        logInFrame.pack();
        logInFrame.setVisible(true);    
    }
    
    public JFrame openRegister(Container content){
        registerFrame = new JFrame();
        registerFrame.setContentPane(content);
        registerFrame.pack();
        registerFrame.setVisible(true); 
        return registerFrame;
    }
    public JFrame openMainMenu(Container content){
        mainMenuFrame = new JFrame( );
        mainMenuFrame.setContentPane(content);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
        return mainMenuFrame;
    }
    public JFrame openOnlineMenu(Container content){
        onlineMenuFrame = new JFrame( );
        onlineMenuFrame.setContentPane(content);
        onlineMenuFrame.pack();
        onlineMenuFrame.setVisible(true);
        return onlineMenuFrame;
    }
    public JFrame openGame(Container content){
        gameFrame = new JFrame( );
        gameFrame.setContentPane(content);
        gameFrame.pack();
        gameFrame.setVisible(true); 
        return gameFrame;
    }
    public JFrame openStats(Container content){
        statsFrame = new JFrame( );
        statsFrame.setContentPane(content);
        statsFrame.pack();
        statsFrame.setVisible(true);
        return statsFrame;
    }
    public JFrame openChooseAI(Container content){
        chooseAIFrame = new JFrame( );
        chooseAIFrame.setContentPane(content);
        chooseAIFrame.pack();
        chooseAIFrame.setVisible(true); 
        return chooseAIFrame;
    }
    public void setInvisible(Container content){
        content.setVisible(false);
    }
    
}
