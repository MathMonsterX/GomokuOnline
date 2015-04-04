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
        model.createOnlineMenuFrame(view);
        view.makeVisible();
    }
    public void openView(){
        view.makeVisible();
    }
    public void postList(String[] players){
        view.post(players);
    }
}
