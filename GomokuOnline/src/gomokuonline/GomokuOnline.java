package gomokuonline;

import javax.swing.JFrame;
import java.awt.Container;

/**
 *
 * @author clarissapendleton
 */
public class GomokuOnline {
    JFrame logInFrame;
    AdminModel model;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GomokuOnline game = new GomokuOnline();
        game.startGame();
        
    }
    
    public void startGame(){
        model = new AdminModel("10.0.0.7", 8080);
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


   
    
}
