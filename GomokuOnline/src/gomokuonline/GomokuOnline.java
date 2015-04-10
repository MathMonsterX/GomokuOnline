package gomokuonline;

import javax.swing.JFrame;
import java.awt.Container;

/**
 * This is the main class that starts the game
 * @author clarissapendleton
 */
public class GomokuOnline {
    private static final String LOCAL_HOST = "127.0.0.1";
    private JFrame logInFrame;
    private AdminModel model;
    /**
     * This creates a new instance of GomokuOnline
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GomokuOnline game = new GomokuOnline();
        game.startGame();
        
    }
    /**
     * This instantiates new instances of AmdinModel, JFrame, LogInView, and LogInController.
     * It contains the setters for each object.
     */
    public void startGame(){
        model = new AdminModel();
        
        logInFrame = new JFrame();
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LogInView view = new LogInView();
        LogInController controller = new LogInController();
        model.setLogInController(controller);
        controller.setView(view);
        controller.setModel(model);
        view.setFrame(logInFrame);
        view.setController(controller);
        logInFrame.setContentPane(view);
        logInFrame.pack();
        logInFrame.setVisible(true);    
    }


   
    
}
