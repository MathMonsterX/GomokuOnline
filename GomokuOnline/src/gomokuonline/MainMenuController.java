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
        model.createMainMenuFrame(view);
        view.makeVisible();
    }
    public void setModel(AdminModel model){
        this.model = model;
    }
    public void openView(){
        view.makeVisible();
    }
    
   
 
}
