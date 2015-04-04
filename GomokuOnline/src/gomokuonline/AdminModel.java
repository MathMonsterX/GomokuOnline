
package gomokuonline;

/**
 * This model is meant to dictate what views appear and disappear
 * after certain buttons are clicked. Each method is called by the
 * appropriate controllers and views
 * @author Chris
 */
public class AdminModel {
    ChooseAIController AIController;
    GameController gameController;
    LogInController logInController;
    MainMenuController mainMenuController;
    OnlineMenuController onlineMenuController;
    RegisterController registerController;
    StatController statController;
    
    
    public void openOnlineMenu(){
        if(onlineMenuController==null){
            onlineMenuController = new OnlineMenuController();
            onlineMenuController.createView();
        }
        else
            onlineMenuController.openView();
            
        
        
    }
}
