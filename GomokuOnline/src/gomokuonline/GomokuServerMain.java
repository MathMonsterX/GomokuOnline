/**
*Name: Tom Kreamer
*Assignment: Lab 3
*Title: EchoClientMain
*Course: CSCE 320
*Semester: Spring 2015
*Instructor: George Hauser
*Date: 3/6/2015
*Sources consulted: None
*Creativity: None
*Program Description: Main method, creates instances of the model, view,
* and controller, then connects them. As the main class of the Server program, 
* this class should be run with no arguments to start the application. 
* Additionally, for advanced users, the option exists to run main with 1 
* argument: a port number to be used for the network connection. With
* no arguments, the controller will default to using port 8080.
*/

package gomokuonline;

/**
 *
 * @author Tom
 */
public class GomokuServerMain {

    /**
     * 
     * @param args for advanced users, a
     * portNum different from the default. 
     * Since this is meant for advanced users
     * only, it is hidden from the GUI.
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
