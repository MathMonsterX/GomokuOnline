package gomokuonline;

/**
 * This is the controller for the LogInView
 * @author clarissapendleton
 */
public class LogInController {
    AdminModel model;
    LogInView view;
    /**
     * Sets this model
     * @param model the AdminModel object
     */
    public void setModel(AdminModel model){
        this.model = model;
    }
    /**
     * Sets this view
     * @param view the LogInView object
     */
    public void setView(LogInView view){
        this.view = view;
    }
    /**
     * This opens the RegisterView via the model.
     */
    public void openRegisterView(){
        model.openRegister();
    }
    /**
     * This opens the MainMenuView via the model.
     */
    public void openMainMenuView(){
        model.openMainMenu();
    }
    /**
     * Sends the username and password to sign in.
     * @param username the username to send to the model
     * @param password the password to send to the model
     */
    public void signIn(String username, String password){
        model.sendUserLogin(username, password);
    }
    /**
     * This displays an error message on the view.
     * @param error the error message
     */
    public void invalidSignIn(String error){
        view.updatelblError(error);
    }
    /**
     * Sets the visibility of the view to true.
     */
    public void openView(){
        view.makeVisible();
    }
    /**
     * Sets the visibility of the view to false. 
     */
    public void setInvisible(){
        view.setInvisible();
    }
}
