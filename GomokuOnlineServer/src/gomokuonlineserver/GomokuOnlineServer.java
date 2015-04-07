/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuonlineserver;

/**
 *
 * @author Sheyla
 */
public class GomokuOnlineServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GomokuServerModel model = new GomokuServerModel();
        GomokuServerController controller;
        
        if(args.length>0)
            controller = new GomokuServerController(Integer.parseInt(args[0]));
        else
            controller = new GomokuServerController();
        
        GomokuServerView view = new GomokuServerView();
        
        controller.setView(view);
        controller.setModel(model);
        view.setController(controller);
        view.setVisible(true);
    }
    
}
