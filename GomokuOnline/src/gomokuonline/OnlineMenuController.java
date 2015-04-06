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
    AdminModel model;
    OnlineMenuView view; 
    
    public void createView(){
        view = new OnlineMenuView();
        model.createOnlineMenuFrame(view);
        view.setController(this);
        view.makeVisible();
    }
    public void openView(){
        view.makeVisible();
    }
    public void setModel(AdminModel model){
        this.model = model;
    }
    public void setFrame(JFrame frame){
        view.setFrame(frame);
    }
    public void postList(String[] players){
        view.post(players);
    }
    public void timedRequestList(){
        while(true){
            try {
                sleep(15000);
                model.getLoggedInList();
                timedRequestList();
            } catch (InterruptedException ex) {
                Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Timer was interrupted");
            }
        }
    }
}
