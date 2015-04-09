
package gomokuonline;

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Container;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JFrame;


/**
 * This model is meant to dictate what views appear and disappear
 * after certain buttons are clicked. Each method is called by the
 * appropriate controllers and views
 * @author Chris
 */
public class AdminModel implements Runnable{
    private ChooseAIController AIController;
    private GameController gameController;
    private LogInController logInController;
    private MainMenuController mainMenuController;
    private OnlineMenuController onlineMenuController;
    private RegisterController registerController;
    private StatController statController;
    
    private int port = 8080;
    private Socket clientSock;
    private InputStream is;
    private OutputStream os;
    private BufferedWriter socketOut;
    private BufferedReader socketIn;
    
    private Thread worker;
    
    /**
     * Constructor, creates a new instance of AdminModel
     */
    public AdminModel(){
        try{
            clientSock = new Socket( InetAddress.getLocalHost().getHostAddress(), port ) ;
            is = clientSock.getInputStream() ;
            os = clientSock.getOutputStream() ;
            socketOut = new BufferedWriter(new OutputStreamWriter(os));
            socketIn = new BufferedReader( new InputStreamReader(is) ) ;
            
            
        } catch (IOException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    /**
     * Constructor, creates a new instance of AdminModel
     * @param address IP address
     * @param port port number
     */
    public AdminModel(String address, int port){
        try{
            clientSock = new Socket(address, port);
            is = clientSock.getInputStream();
            os = clientSock.getOutputStream() ;
            socketOut = new BufferedWriter(new OutputStreamWriter(os));
            socketIn = new BufferedReader( new InputStreamReader(is));
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    /**
     * Thread that reads in and processes messages from the server.
     */
    @Override
    public void run() {
        try{
            while(true){
                String message = "";
                char msgChar = (char)socketIn.read();
                message += msgChar;
                while(socketIn.ready()){
                    msgChar = (char)socketIn.read();
                    message += msgChar;
                }
                String[] input = message.split("\\s+");
                if(input[0].equals("AUTHENTICATION")){
                    if(input[1].equals("success")){
                        logInController.setInvisible();
                        openMainMenu();
                        
                    }
                    else{
                        logInController.invalidSignIn("** Login failed. Please reenter fields **");
                    }
                }else if(input[0].equals("ONLINE_USERS")){
                    String[] players = new String[input.length-1];
                    for(int i=1; i<input.length; i++){
                        players[i-1] = input[i];
                    }
                    postList(players);
                }else if(input[0].equals("ACCOUNT_CREATION")){
                    if(input[1].equals("success")){
                    registerController.setInvisible();
                    openMainMenu();
                    }
                    else{
                        registerController.invalidSignIn("** Account not created. Please enter a different username **");
                    
                    }
                }
            }
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed in receiving message");
        }
    }
    /**
     * Instantiates the thread that reads in messages from the server, then
     * starts the thread.
     */
    public void beginListening(){
        worker = new Thread(this);
        worker.start();
    }
    /**
     * Sets the LogInConroller for this model
     * @param controller the LogInController used for this model
     */
    public void setLogInController(LogInController controller){
        this.logInController = controller;
    }
    
    /**
     * Hashes a string password using the SHA-256 cryptographic hash
     * SOURCE: Mkyong, open source Java tutorial writer, provided 
     * the basic outline for using the MessageDigest class
     * http://www.mkyong.com/java/java-sha-hashing-example/
     * @param password the password to be hashed
     * @param salt string to be used to salt the password
     * @return A string representation of the hashed password
     */
    private static String hashPassword(String password, String salt){
        //StringBuilder hashedPassword = new StringBuilder("");
        String hashedPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((salt + password).getBytes());
            byte[] byteData = md.digest();   
            BigInteger hashVal = new BigInteger(byteData);
            hashedPassword = hashVal.toString(16);   
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return hashedPassword;
    }
    
    /**
     * Sends the username and password to be authenticated to the server.
     * @param user the username to be sent to the server
     * @param pass the password to be sent to the server
     */
    public void sendUserLogin(String user, String pass){
        String message = "LOGIN "  + user + " " + hashPassword(pass,user);
        try{
            socketOut.write(message);
            socketOut.flush();
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in writing to socket");
        }
    }
    /**
     * Sends the username and password to the server to create a new account
     * @param user the username to be sent to the server
     * @param pass the password to be sent to the server
     */
    public void createAccount(String user, String pass){
        String message = "CREATE_ACCOUNT " + user + " " + hashPassword(pass, user);
        try{
            socketOut.write(message);
            socketOut.flush();
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in writing to socket");
        }
    }
    
    /**
     * Writes to the server to request a list of online users
     */
    public void getLoggedInList(){
        String message = "GET_USERS";
        try{
            socketOut.write(message);
            socketOut.flush();
        }catch (IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in sending list request");
        }
    }
    /**
     * Sends the list of online players to the OnlineMenuController to be 
     * posted in the OnlineMenuView
     * @param input the list of online players
     */
    public void postList(String[] input){
        onlineMenuController.postList(input);
    }
    /**
     * A method in the logInController is called to open the view that logs 
     * players in.
     */
    public void openLogIn(){
        logInController.openView();
    }
    /**
     * Creates an instance of RegisterController if registerController is null,
     * and calls a method in the controller to create the view. If registerController
     * is not null, a method in the controller is called to open the view
     */
    public void openRegister(){
        if(registerController==null){
            registerController = new RegisterController();
            registerController.setModel(this);
            registerController.createView();
            
        }
        else 
            registerController.openView();
    }
    /**
     * Creates an instance of MainMenuController if mainMenuController is null,
     * and calls a method in the controller to create the view. If mainMenuController
     * is not null, a method in the controller is called to open the view
     */
    public void openMainMenu(){
        if(mainMenuController == null){
            mainMenuController = new MainMenuController();
            mainMenuController.setModel(this);
            mainMenuController.createView();
            
        }
        else
            mainMenuController.openView();
    }
  
    /**
     * Creates an instance of OnlineMenuController if onlineMenuController is null,
     * and calls a method in the controller to create the view. If onlineMenuController
     * is not null, a method in the controller is called to open the view
     */
      public void openOnlineMenu(){
        if(onlineMenuController==null){
            onlineMenuController = new OnlineMenuController();
            onlineMenuController.setModel(this);
            onlineMenuController.createView();
        }
        else
            onlineMenuController.openView();
        onlineMenuController.timedRequestList();
   
    }
      /**
     * Closes the socket
     */
    public void close(){
        try{
            this.clientSock.close();
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in closing socket");

        }
    }
 
}
