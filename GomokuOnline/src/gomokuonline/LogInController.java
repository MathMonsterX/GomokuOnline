package gomokuonline;

import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class LogInController {
    AdminModel model;
    LogInView view;
    
    public void setModel(AdminModel model){
        this.model = model;
    }
    public void setView(LogInView view){
        this.view = view;
    }
    
    public void openRegisterView(){
        model.openRegister();
    }
    
    public void openMainMenuView(){
        model.openMainMenu();
    }
    public void signIn(String username, String password){
        model.sendUserLogin(username, password);
    }
    
    public void invalidSignIn(String error){
        view.updatelblError(error);
    }
    public void openView(){
        view.makeVisible();
    }
}
