package gomokuonline;

import javax.swing.JFrame;

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
        view.setController(this);
        view.makeVisible();  
    }
    public void setFrame(JFrame frame){
        view.setFrame(frame);
    }
    public void openView(){
        view.makeVisible();
    }
    public void openMainMenuView(){
        model.openMainMenu();
    }
    
}
