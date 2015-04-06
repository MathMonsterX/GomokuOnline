package gomokuonline;

import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class MainMenuController {

    MainMenuView view;
    AdminModel model;
    
    public void createView(){
        view = new MainMenuView();
        model.createMainMenuFrame(view);
        view.setController(this);
        view.makeVisible();
    }
    public void setModel(AdminModel model){
        this.model = model;
    }
    public void setFrame(JFrame frame){
        view.setFrame(frame);
    }
    public void openView(){
        view.makeVisible();
    }

    public void openOnlineMenuView() {
        model.openOnlineMenu();
    }
    
   
 
}
