package gomokuonline;

/**
 *
 * @author clarissapendleton
 */
public class RegisterController {
    RegisterView view;
    AdminModel model;
    
    public RegisterController(){
        
    }
    
    public void setModel(AdminModel model){
        this.model = model;
    }
    
    public void setView(RegisterView view){
        this.view = view;
        
    }
    public void createAccount(String username, String password){
        model.createAccount(username, password);
    }
    public void backClick(){
        model.openLogIn();
    }
    public void createView(){
        this.view = new RegisterView();
        model.createRegisterFrame(view);
        view.makeVisible();
        
    }
    public void openView(){
        view.makeVisible();
    }
    
}
