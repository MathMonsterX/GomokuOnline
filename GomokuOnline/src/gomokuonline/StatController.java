package gomokuonline;

import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class StatController {
    private AdminModel model;
    private StatView view;
    private JFrame statFrame;

    public void setModel(AdminModel model) {
        this.model = model;
    }

    public void openView(){
        view.makeVisible();

    }
    
    public void createView(){
        this.view = new StatView();
        statFrame = new JFrame( );
        statFrame.setContentPane(view);
        statFrame.pack();
        statFrame.setVisible(true);
        view.setController(this);
        view.setFrame(statFrame);
        
    }
    public void openMainMenuView(){
        model.openMainMenu();
    }
    
}
