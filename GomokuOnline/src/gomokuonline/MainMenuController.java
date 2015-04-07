package gomokuonline;

import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class MainMenuController {
    JFrame mainMenuFrame;
    MainMenuView view;
    AdminModel model;
    
    public void createView(){
        this.view = new MainMenuView();
        mainMenuFrame = new JFrame( );
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setContentPane(view);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
        view.setController(this);
        view.setFrame(mainMenuFrame);
    }
    public void setModel(AdminModel model){
        this.model = model;
    }
   
    public void openView(){
        view.makeVisible();
    }

    public void openOnlineMenuView() {
        model.openOnlineMenu();
    }
    
   
 
}
