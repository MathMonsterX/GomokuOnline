
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    private List<GameModel> games;
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
    private String username;
    
    /**
     * Constructor, creates a new instance of AdminModel
     */
    public AdminModel(){
        try{
            this.games = Collections.synchronizedList(new ArrayList<GameModel>());
            clientSock = new Socket( InetAddress.getLocalHost().getHostAddress(), port ) ;
            is = clientSock.getInputStream() ;
            os = clientSock.getOutputStream() ;
            socketOut = new BufferedWriter(new OutputStreamWriter(os));
            socketIn = new BufferedReader( new InputStreamReader(is) ) ;
            
            
        } catch (IOException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            //System.exit(1);
        }
    }
    
    /**
     * Constructor, creates a new instance of AdminModel
     * @param address IP address
     * @param port port number
     */
    public AdminModel(String address, int port){
        try{
            this.games = Collections.synchronizedList(new ArrayList<GameModel>());
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
            String message;
            while((message = socketIn.readLine()) != null){
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
                }else if(input[0].equals("INVITED_BY")){
                    onlineMenuController.updateRequests(input[1],input[2]);
                }else if(input[0].equals("P2P")){
                    
                    int size = Integer.parseInt(input[3]);
                    this.openGame(input[1], input[2], size, input[4]);
                }else if(input[0].equals("STATS")){
                   // this.openStats();
                    this.postStats(Arrays.copyOfRange(input, 1, input.length));
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
            this.username=user;
            socketOut.write(message + "\n");
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
            this.username = user;
            socketOut.write(message + "\n");
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
            socketOut.write(message + "\n");
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
     * Sends the player stats to the StatController to be 
     * posted in the OnlineMenuView
     * @param input the list of online players
     */
    public void postStats(String[] stats){
        this.statController.postStats(stats);
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
      
    public void openChooseAI() {
        if(AIController==null){
            AIController = new ChooseAIController();
            AIController.setModel(this);
            AIController.createView();
        }
        else
            AIController.openView();
    }
    
    public void openStats(){
        if(statController==null){
            statController = new StatController();
            statController.setModel(this);
            statController.createView();
        }
        else
            statController.openView();
        statController.timedStatUpdate();
    }
      /**
     * Creates an instance of gameController and gameModel if gameController is null,
     * and calls a method in the controller to create the view. If gameController
     * is not null, a method in the controller is called to open the view
     */
      public void openGame(String player, int size){
          
              try {
                  GameModel gameModel = new GameModel(size,'O', player);
                  GameController gameController = new GameController();
                  gameModel.setController(gameController);
                  gameModel.setAdminModel(this);
                  gameController.setModel(gameModel);
                  gameController.createView();
                  gameController.setEndMoveEnabled(true);
                  String hostIP = gameModel.getServerIP();
                  int portNum = gameModel.getServerPort();
                  socketOut.write("CREATE_P2P "  + hostIP + " " + portNum + " " + player + " " + size+  " " + this.username + "\n"); 
                  gameModel.listen();
                  games.add(gameModel);
                  
              } catch (IOException ex) {
                  Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
              }
          
              
      }
      /**
     * Creates an instance of gameController and gameModel if gameController is null,
     * and calls a method in the controller to create the view. If gameController
     * is not null, a method in the controller is called to open the view
     * @param ip the IP address this user will connect to
     */
      public void openGame(String ip, String port, int size, String opponent){
          int connectPort = Integer.parseInt(port);
          
         // if(gameController==null){
              GameModel gameModel = new GameModel(ip, connectPort, size, 'X', opponent);
              GameController gameController = new GameController();
              gameModel.setController(gameController);
              gameModel.setAdminModel(this);
              gameController.setModel(gameModel);
              gameController.createView();
              gameController.setEndMoveEnabled(false);
              gameModel.listen();
              games.add(gameModel);
         // }
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
    /**
     * Writes a request to invite a player to the server
     * @param player the username of the player being invited
     */
    public void invite(String player, String size){
        String message = "INVITE " + player + " " + this.username +" " +size+ " ";
        try{
            socketOut.write(message + "\n");
            socketOut.flush();
        }catch (IOException ex){
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in sending invite");
        }
    }
    /**
     * When the user accepts an invitation, this method calls openGame() to create
     * the gameModel, gameController, and gameView
     */
    public void accept(String player){
        String[] input = player.split("\\s+");
        String gameSize = input[1].charAt(0) + ""+input[1].charAt(1);
        int size = Integer.parseInt(gameSize);
        this.openGame(input[0], size);
    }
    
    public void sendGameStats(String date, int endTime, String opponent, int result){
        try {
            String message = "UPDATE_STATS " + this.username + " " + date + " " + endTime + " " + opponent + " " + result;
            socketOut.write(message + "\n");
            socketOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Where the game between the AI and the player occurs
     * @param diffLevel the difficulty level
     * @param gameSize the size of the board
     */
   public void playAI(String diffLevel, String gameSize){
       int size=30;//default board size
       if(gameSize.equals("30x30")){
          size=30; 
       }
       else if(gameSize.equals("40x40")){
           size=40;
       }
       else if(gameSize.equals("50x50")){
           size=50;
       }   
       
       if(diffLevel.equals("Easy")){
               AI easy = new AI(size,0);
               easy.setAdmin(this);
               easy.createModel();  
       }
       else if(diffLevel.equals("Intermediate")){
            AI medium = new AI(size,1);
            medium.setAdmin(this);
               medium.createModel();
       }
       else{
           AI hard = new AI(size,2);
           hard.setAdmin(this);
           hard.createModel();
       }

   }
   
   /**
     * Writes to the server to request the stats for this user
     */
   public void getStats(){
        try {
            String message  = "GET_STATS " +this.username;
            this.socketOut.write(message + "\n");
            this.socketOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
  
/**
 * This sets the gameController and gameModel to null.
 */   
public void setNull(){
    this.gameController = null;
    
}

public void removeGame(GameModel game){
    this.games.remove(game);
}
/**
 * Returns this user's username
 * @return the username
 */
public String getUsername() {
    if(this.username==null)
        return "";
    else 
        return username;
}
}
