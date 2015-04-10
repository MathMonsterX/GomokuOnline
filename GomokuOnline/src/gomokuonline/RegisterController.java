package gomokuonline;

import javax.swing.JFrame;

/**
 *This is the controller for the RegisterView
 * @author clarissapendleton
 */
public class RegisterController {
    private RegisterView view;
    private JFrame registerFrame;
    private AdminModel model;
    
    /**
     * Sets this model
     * @param model the AdminModel object
     */
    public void setModel(AdminModel model){
        this.model = model;
    }
    /**
     * Sets this view
     * @param view the RegisterView object
     */
    public void setView(RegisterView view){
        this.view = view;  
    }
    /**
     * Sends the username and password to the model to create a new account
     * @param username the username to send to the model
     * @param password the password to send to the model
     */
    public void createAccount(String username, String password){
        model.createAccount(username, password);
    }
    /**
     * Opens the logInView via the model.
     */
    public void backClick(){
        model.openLogIn();
    }
    /**
     * Sends an error message to appear on the view.
     * @param error the error message
     */
    public void invalidSignIn(String error){
        view.updateLblError(error);
    }
    /**
     * Sets the visibility of the view to false.
     */
    public void setInvisible(){
        view.setInvisible();
    }
    /**
     * Creates new instances of ReisterView and JFrame. 
     */
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
    
    /**
     * Sets the visibility of the view to true
     */
    public void openView(){
        view.makeVisible();
    }
    /**
     * Opens MainMenuView via the model
     */
    public void openMainMenuView(){
        model.openMainMenu();
    }
    
}
