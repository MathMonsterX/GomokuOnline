package gomokuonline;

/**
 *
 * @author clarissapendleton
 */
public class MainMenuController {

    MainMenuView view;
    AdminModel model;
    
    public void createView(){
        view = new MainMenuView();
        view.makeVisible();
    }
    public void openView(){
        view.makeVisible();
    }
    
   
 
}
