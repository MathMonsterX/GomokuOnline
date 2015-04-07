package gomokuonline;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class OnlineMenuController {
    JFrame onlineMenuFrame;
    AdminModel model;
    OnlineMenuView view; 
    
    public void createView(){
        view = new OnlineMenuView();
        onlineMenuFrame = new JFrame( );
        onlineMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        onlineMenuFrame.setContentPane(view);
        onlineMenuFrame.pack();
        onlineMenuFrame.setVisible(true);
        view.setController(this);
        view.setFrame(onlineMenuFrame);
        model = new AdminModel();
        view.makeVisible();
    }
    public void openView(){
        view.makeVisible();
    }
    public void setModel(AdminModel model){
        this.model = model;
    }
    public void postList(String[] players){
        view.post(players);
    }
    public void timedRequestList(){
        String users;
        while(true){
            try {
                model.getLoggedInList();
                sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Timer was interrupted");
            }
        }
    }
}
