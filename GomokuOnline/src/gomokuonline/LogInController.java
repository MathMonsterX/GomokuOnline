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
        
    }
    
    public void invalidSignIn(){
        
    }
