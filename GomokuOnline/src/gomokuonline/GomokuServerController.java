/**
*Name: Tom Kreamer
*Assignment: Lab 3
*Title: EchoServerController
*Course: CSCE 320
*Semester: Spring 2015
*Instructor: George Hauser
*Date: 3/6/2015
*Sources consulted: None
*Creativity: None
*Program Description: Serves as the controller for the EchoServer program.
* This program serves as the intermediary between the model and view. 
* The model handles all communication between the model and view: no direct communication
* occurs from either the view to the model, or the model to the view.
* EchoServerController handles all setup and I/O over the network connection,
* listening over its server socket, and forking new connection threads as connection objects
*/

package gomokuonline;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class GomokuServerController implements Runnable{

   /**Default port number*/ 
   private static final int defaultPort = 8080;
   
   
   private ServerSocket servSock;
   private GomokuServerModel model;
   private GomokuServerView view;
   private Thread listenWorker;
    
    
    /**
     * Default constructor, creates an instance of an EchoServerController
     * using default port number
     */
    public GomokuServerController(){
        try {
            this.servSock = new ServerSocket(defaultPort);
            
            
                    } catch (IOException ex) {
            Logger.getLogger(GomokuServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates an instance of an EchoServerController using 
     * the given port number for the server socket
     * @param portNum port number for the server socket
     * 
     */
    public GomokuServerController(int portNum){
        try {
            this.servSock = new ServerSocket(portNum);
            
                    } catch (IOException ex) {
            Logger.getLogger(GomokuServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Removes a connection from the model
     * @param con connection to be removed
     */
    public void removeConnection(Connection con){
        this.model.removeConnection(con);
        this.updateNumConnections();
        
    }
    
    /**
     * Sets the model for this controller
     * @param model the model to be used for this controller
     */
    public void setModel(GomokuServerModel model){
        this.model = model;
    }
    
    
    /**
     * Sets the view for this controller
     * @param view the view to be used for this controller
     */
    public void setView(GomokuServerView view){
        this.view = view;
    }
    
    /**
     * Broadcasts a message over all of the server's connections
     * @param message to be broadcast
     */
    public void broadcast(String message){
        Connection[] connections  = this.model.getConnections();
        this.model.addMessage(message);
        this.updateMessages();
        for(Connection con : connections){
            con.sendMessage(message);
        }
    }
    
    public void clearMessageHistory(){
        this.model.clearMessages();
        this.updateMessages();
    }
    
    /**
     * Updates the view with the current message history
     */
    public void updateMessages(){
        String[] messages = this.model.getMessages();
        StringBuilder messageHistory = new StringBuilder();
        for(String message : messages){
            messageHistory.append(message + "\n");
        }
        this.view.setMessages(messageHistory.toString().trim());
}
    /**
     * Listens for connections over the server socket,
     * then creates a new connection and adds it to the model
     */
    public void acceptConnection(){
        while(true){
            try {
                
                Socket s = servSock.accept();
                Connection con = new Connection(s, this);
                con.start();
                this.model.addConnection(con);
                this.updateNumConnections();
            } catch (IOException ex) {
                Logger.getLogger(GomokuServerController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error creating new Connection");
            }
        }
    }
    
    private String getOnlinePlayers(){
        String[] players = this.model.getOnlineUsers();
        
        StringBuilder userList = new StringBuilder("ONLINE_USERS");
        for(String user : players){
            userList.append(" "+user);
        }
        return userList.toString();
    }
    
    /**
     * Checks an input string for command, then returns appropriate response
     * @param message
     * @return 
     */
    public String processMessage(String message){
        String[] input = message.split("\\s+");
        
        switch(input[0]){
            case "LOGIN":
                return this.model.authenticate(input[1], input[2]);
            case "GET_USERS":
                return this.getOnlinePlayers();
            case "CREATE_ACCOUNT":
                return this.model.createAccount(input[1], input[2]);
        }
        
       
        
        return "ERROR: Unexpected Input: " + message;
    }
    
    /**
     * Updates the view with the current number
     * of active connections to the server
     */
    public void updateNumConnections(){
        this.view.setNumConnections("" + this.model.numConnections());
    }
    
    /**
     * Thread that listens for new connections
     */
    @Override
    public void run() {
        this.acceptConnection();
    }
    
    /**
     * Instantiates the thread that listens for
     * new connections, then starts the thread
     */
    public void listen(){
        listenWorker = new Thread(this);
        listenWorker.start();
    }
    
}
