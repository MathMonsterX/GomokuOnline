package gomokuonline;

import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class RegisterController {
    RegisterView view;
    JFrame registerFrame;
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
    public void invalidSignIn(String error){
        view.updateLblError(error);
    }
    public void setInvisible(){
        view.setInvisible();
    }
    public void createView(){
        this.view = new RegisterView();
        registerFrame = new JFrame();
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setContentPane(view);
        registerFrame.pack();
        registerFrame.setVisible(true); 
        view.setController(this);
        view.setFrame(registerFrame);
    }
  
    public void openView(){
        view.makeVisible();
    }
    public void openMainMenuView(){
        model.openMainMenu();
    }
    
}
