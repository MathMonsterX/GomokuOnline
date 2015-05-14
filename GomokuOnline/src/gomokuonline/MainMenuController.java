package gomokuonline;

import javax.swing.JFrame;

/**
 * The controller for the MainMenuView
 * @author clarissapendleton
 */
public class MainMenuController {
    private JFrame mainMenuFrame;
    private MainMenuView view;
    private AdminModel model;
    
    /**
     * This creates instances of MainMenuView and JFrame.  
     */
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
    /**
     * This sets this model
     * @param model the AdminModel object
     */
    public void setModel(AdminModel model){
        this.model = model;  
    }
    /**
     * This opens the OnlineMenuView via the model.
     */
    public void openOnlineMenuView(){
        model.openOnlineMenu();
    }
    /**
     * This makes this view visible.
     */
    public void openView(){
        view.makeVisible();
    }
    
    public void openChooseAIView(){
        model.openChooseAI();
    }
    
   public void openStatsView(){
       model.openStats();
   }
 
}
