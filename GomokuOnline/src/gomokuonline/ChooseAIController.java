
package gomokuonline;

import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class ChooseAIController {
    private JFrame chooseAIFrame;
    private AdminModel model;
    private ChooseAIView view; 
    
    /**
     * This creates the view and onlineMenuFrame  
     */
    public void createView(){
        view = new ChooseAIView();
        chooseAIFrame = new JFrame( );
        chooseAIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseAIFrame.setContentPane(view);
        chooseAIFrame.pack();
        chooseAIFrame.setVisible(true);
        view.setController(this);
        view.setFrame(chooseAIFrame);
        
    }
    /**
     * This sets the visibility of the onlineMenuFrame to false in the view
     */
    public void openView(){
        view.makeVisible();
    }
    /**
     * This sets the model of this controller
     * @param model the AdminModel object
     */
    public void setModel(AdminModel model){
        this.model = model;
    }
    
    /**
     * This send the difficulty level and size to the model
     * @param diffLevel the difficulty level of the AI
     * @param size the size of the board
     */
    public void play(String diffLevel, String size){
        model.playAI(diffLevel, size);
 
    }
    
    /**
     * This opens the log in view by calling the method in the model to open
     * the log in view
     */
    public void openMainMenuView(){
        model.openMainMenu();
    }
}
