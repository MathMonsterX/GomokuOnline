
package gomokuonline;

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Container;
import javax.swing.JFrame;


/**
 * This model is meant to dictate what views appear and disappear
 * after certain buttons are clicked. Each method is called by the
 * appropriate controllers and views
 * @author Chris
 */
public class AdminModel implements Runnable{
    ChooseAIController AIController;
    GameController gameController;
    LogInController logInController;
    MainMenuController mainMenuController;
    OnlineMenuController onlineMenuController;
    RegisterController registerController;
    StatController statController;
    
    private int port = 8080;
    private Socket clientSock;
    private InputStream is;
    private OutputStream os;
    private BufferedWriter socketOut;
    private BufferedReader socketIn;
    
    private Thread worker;
    
    //opens default local address
    public AdminModel(){
        try{
            System.out.println("Attempting to connect socket");
            clientSock = new Socket( InetAddress.getLocalHost().getHostAddress(), port ) ;
            System.out.println("Local Socket connection successful!");
            is = clientSock.getInputStream() ;
            os = clientSock.getOutputStream() ;
            socketOut = new BufferedWriter(new OutputStreamWriter(os));
            socketIn = new BufferedReader( new InputStreamReader(is) ) ;
            
            
        } catch (IOException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    //connects to specified address
    public AdminModel(String address, int port){
        try{
            clientSock = new Socket(address, port);
            System.out.println("Connected: "+clientSock.isConnected());
            is = clientSock.getInputStream();
            os = clientSock.getOutputStream() ;
            socketOut = new BufferedWriter(new OutputStreamWriter(os));
            socketIn = new BufferedReader( new InputStreamReader(is));
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
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
                System.out.println("INPUT" + input[0]);
                if(input[0].equals("AUTHENTICATION")){
                    if(input[1].equals("success")){
                        System.out.println("login successful");
                        logInController.setInvisible();
                        openMainMenu();
                        
                    }
                    else{
                        System.out.println("login failed");
                        logInController.invalidSignIn("** Login failed. Please reenter fields **");
                    }
                }else if(input[0].equals("ONLINE_USERS")){
                    String[] players = new String[input.length-1];
                    System.out.println("Receiving list of players...");
                    for(int i=1; i<input.length; i++)
                        players[i-1] = input[i];
                    
                    postList(players);
                }else if(input[0].equals("ACCOUNT_CREATION")){
                    if(input[1].equals("success")){
                    System.out.println("account created");
                    registerController.setInvisible();
                    openMainMenu();
                    
                    }
                    else{
                        System.out.println("account not created");
                        registerController.invalidSignIn("** Account not created. Please enter a different username **");
                
                    }
                }
            }
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed in receiving message");
        }
    }
    public void beginListening(){
        worker = new Thread(this);
        worker.start();
    }
    public void sendUserLogin(String user, String pass){
        String message = "LOGIN "  + user + " " + pass;
        try{
            socketOut.write(message);
            socketOut.flush();
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in writing to socket");
        }
    }
    public void createAccount(String user, String pass){
        String message = "CREATE_ACCOUNT " + user + " " + pass;
        try{
            socketOut.write(message);
            socketOut.flush();
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in writing to socket");
        }
    }
    
    //get list of online players from server
    public void getLoggedInList(){
        String message = "GET_USERS";
        try{
            socketOut.write(message);
            socketOut.flush();
            System.out.println("Wrote get users");
        }catch (IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in sending list request");
        }
    }
    public void postList(String[] input){
        onlineMenuController.postList(input);
    }
    public void close(){
        try{
            this.clientSock.close();
        }catch(IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in closing socket");

        }
    }
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
    public void openRegister(){
        if(registerController==null){
            registerController = new RegisterController();
            registerController.setModel(this);
            registerController.createView();
            
        }
        else 
            registerController.openView();
    }

    public void openMainMenu(){
        if(mainMenuController == null){
            mainMenuController = new MainMenuController();
            mainMenuController.setModel(this);
            mainMenuController.createView();
            
        }
        else
            mainMenuController.openView();
    }
   

    public void openLogIn(){
        logInController.openView();
    }
    
 
}
