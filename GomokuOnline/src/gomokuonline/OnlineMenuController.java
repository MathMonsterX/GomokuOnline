package gomokuonline;

/**
 *
 * @author clarissapendleton
 */
public class OnlineMenuController {
    AdminModel model;
    OnlineMenuView view; 
    
    public void createView(){
        view = new OnlineMenuView();
        view.makeVisible();
    }
    public void openView(){
        view.makeVisible();
    }
}
