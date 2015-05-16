
package gomokuonline;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * This is the controller for the OnlineMenuView 
 * @author clarissapendleton
 */
public class OnlineMenuController implements Runnable{
    private JFrame onlineMenuFrame;
    private AdminModel model;
    private OnlineMenuView view; 
    
    /**
     * This creates the view and onlineMenuFrame  
     */
    public void createView(){
        view = new OnlineMenuView();
        onlineMenuFrame = new JFrame( );
        onlineMenuFrame.setContentPane(view);
        onlineMenuFrame.pack();
        onlineMenuFrame.setVisible(true);
        view.setController(this);
        view.setFrame(onlineMenuFrame);
        
    }
    /**
     * This sets the visibility of the onlineMenuFrame to false in the view
     */
    public void openView(){
        view.makeVisible();
    }
    /**
     * This sets the model of this controller
     * @param model the AdminModel object
     */
    public void setModel(AdminModel model){
        this.model = model;
    }
    /**
     * This sends the list of online players to be posted in the view
     * @param players the list of online players
     */
    public void postList(String[] players){
        view.post(players);
    }
    /**
     * This thread requests the list of online users every 15 seconds.
     */
    @Override
    public void run(){
        String users;
        while(onlineMenuFrame!=null){
            try {
                model.getLoggedInList();
                sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OnlineMenuController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Timer was interrupted");
            }
        }
    }
    /**
     * This instantiates a new thread to request a list of online players and 
     * starts it.
     */
    public void timedRequestList(){
        Thread thread = new Thread(this);
        thread.start();
    }
    /**
     * This invites player to play a game
     * @param player the username of the player receiving an invitation
     */
    public void invite(String player, String size){
        model.invite(player, size);
    }
    /**
     * This method updates the requests list in the view
     * @param player the username of the player that send an invitation
     */
    public void updateRequests(String player, String size){
        
        view.updateRequests(player, size);
        
    }
    /**
     * When the user accepts an invitation, this method calls the accept method
     * in the model
     */
    public void accept(String player){
        model.accept(player);
    }

    /**
     * Returns the username of the player
     * @return the player's username
     */
    public String getUname() {
        return model.getUsername();
    }
    /**
     * Opens the main menu
     */
    public void back(){
        model.openMainMenu();
    }
    
}

