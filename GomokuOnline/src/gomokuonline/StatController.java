package gomokuonline;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *This is the controller for the StatView.
 * @author clarissapendleton
 */
public class StatController implements Runnable{
    private AdminModel model;
    private StatView view;
    private JFrame statFrame;
    /**
     * model setter
     * @param model the AdminModel 
     */
    public void setModel(AdminModel model) {
        this.model = model;
    }
    /**
     * Opens the StatView.
     */
    public void openView(){
        view.makeVisible();

    }
    /**
     * Creates the StatView.
     */
    public void createView(){
        this.view = new StatView();
        statFrame = new JFrame();
        statFrame.setContentPane(view);
        statFrame.pack();
        statFrame.setVisible(true);
        view.setController(this);
        view.setFrame(statFrame);
        
    }
    
    /**
     * This thread requests the list of online users every 15 seconds.
     */
    @Override
    public void run(){
        String users;
        while(statFrame!=null && statFrame.isVisible()){
            try {
                model.getStats();
                sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Timer was interrupted");
            }
        }
    }
    /**
     * Opens the main menu.
     */
    public void openMainMenuView(){
        model.openMainMenu();
    }
    
    public void timedStatUpdate(){
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public void postStats(String[] stats){
        String statMessage = "";

        for(int i = 0; i < stats.length; i+=2){
            statMessage += stats[i] + " " + stats[i+1] + "\n";
        }
        this.view.postStats(statMessage);
    }
}
