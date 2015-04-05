package gomokuonline;

/**
 *
 * @author clarissapendleton
 */
public class LogInController {
    AdminModel model;
    LogInView view;
    
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
