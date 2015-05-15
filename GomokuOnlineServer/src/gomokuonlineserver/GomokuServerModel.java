/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuonlineserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*Name: Tom Kreamer
*Assignment: Lab 3
*Title: EchoServerModel
*Course: CSCE 320
*Semester: Spring 2015
*Instructor: George Hauser
*Date: 3/6/2015
*Sources consulted: None
*Creativity: None
*Program Description: Serves as the model for the EchoServer program.
*The primary purpose of the program is to maintain a list of active connections, 
* and pass the updated information to the controllers. This
* model does not interface at all with views at any time, instead communicating
* with an EchoServerController to make updates to the view
*/
public class GomokuServerModel {
    private List<Connection> connections;
    private List<String> messages;
    private SortedSet<String> onlineUsers;
    //private Map<String, String> userLogins;
    private List<Player> players;
    private final File loginDatabase = new File("loginData.txt");
    private AtomicBoolean fileKey;
    
    
    /**
     * Constructor, creates a new instance of an GomokuServerModel
     */
    public GomokuServerModel(){
        this.connections = Collections.synchronizedList(new ArrayList<Connection>());
        this.messages = Collections.synchronizedList(new ArrayList<String>());
        this.onlineUsers = Collections.synchronizedSortedSet(new TreeSet<String>()); 
       // this.userLogins = Collections.synchronizedMap(new HashMap<String, String>());
        this.players = Collections.synchronizedList(new ArrayList<Player>());
        this.fileKey = new AtomicBoolean(true);

        this.readFromDatabase();
    }
    
    /**
     * Gets usernames and passwords from the database file
     */ 
   /* private void getLoginsFromDatabase(){
        try{
            if( !loginDatabase.exists() ){ loginDatabase.createNewFile(); }
        } catch (IOException ex) {
            Logger.getLogger(GomokuServerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Scanner scan = new Scanner(this.loginDatabase);
            while(scan.hasNext()){
                String[] login = scan.nextLine().split("\\s+");
                if(login.length > 1)
                    this.userLogins.put(login[0], login[1]);
            }
            scan.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GomokuServerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    /**
     * 
     * Updates the database with the given username and password pair
     * @param uname username to be added
     * @param pass password to be added
     */
    /*private void updateDatabase(String uname, String pass){
        try {
            FileWriter writer = new FileWriter(loginDatabase, true);
            writer.append(uname + " " + pass + "\n");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(GomokuServerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    private void updateDatabase(){
        try {
            while(!fileKey.compareAndSet(true, false));
            FileWriter writer = new FileWriter(loginDatabase);
            writer.write("");
            for(Player player : players){
                writer.append(player.toString());
            }
            writer.flush();
            writer.close();
            fileKey.compareAndSet(false, true);
        } catch (IOException ex) {
            Logger.getLogger(GomokuServerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void readFromDatabase(){
        try{
            if( !loginDatabase.exists() ){ loginDatabase.createNewFile(); }
        } catch (IOException ex) {
            Logger.getLogger(GomokuServerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Scanner scan = new Scanner(this.loginDatabase);
            while(scan.hasNext()){
                String[] login = scan.nextLine().split("\\s+");
                System.out.println("loginsize " +login.length);
                if(login.length > 1){
                    Player player = new Player(login[0], login[1]);
                    int numGames = scan.nextInt();
                    for(int i = 0; i < numGames; i++){
                        String[] gameData = scan.nextLine().split("\\s+");
                        player.addGameStats(DateFormat.getDateInstance().parse(gameData[0]), 
                                Integer.parseInt(gameData[1]), new Player(gameData[2], null), Integer.parseInt(gameData[3]));
                    }
                   this.players.add(player);
                }
            }
            scan.close();
        } catch (FileNotFoundException | ParseException ex) {
            Logger.getLogger(GomokuServerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Attempts to add the given username and password to the database of registered users
     * @param uname username to be associated with account
     * @param pass password to be associated with account
     * @return String response indicating success or failure in account creation
     */
    public String createAccount(String uname, String pass){
        String response = "ACCOUNT_CREATION fail";
        
        if(!players.contains(new Player(uname, null))){
            Player player = new Player(uname, pass);
            players.add(player);
            this.updateDatabase();
            response = "ACCOUNT_CREATION success";
            onlineUsers.add(uname); 
        }
        
        
        return response;
    }
    
    /**
     * Adds a connection to the model's list of 
     * connections.
     * @param con 
     */
    public void addConnection(Connection con){
        this.connections.add(con);
       
    }
    
    /**
     * Returns an array representation of the model's messages
     * @return array of message strings
     */
    public String[] getMessages(){
        return this.messages.toArray( new String[this.messages.size()] );
    }
    
    /**
     * Clears the model's message history list
     */
    public void clearMessages(){
        this.messages.clear();
    }
    
    
    /**
     * Adds a message to the model's message history
     * @param message to be added
     */
    public void addMessage(String message){
        this.messages.add(message);
    }
    
    /**
     * Attempts to authenticate the given username and password
     * @param uname username to be authenticated
     * @param pass password to be authenticated
     * @return the authentication status represented as a string
     */
    public String authenticate(String uname, String pass){
        String response = "AUTHENTICATION fail";       
        Player player = this.getPlayer(uname);
        if(player!=null){
            String storedPass = player.getPassword();
            if(storedPass.equals(pass)){
               response = "AUTHENTICATION success";
               onlineUsers.add(uname); 
            }
        }
        
        return response;
    }
    
    /**
     * Gets the player object associated with the given username
     * @param username username of player
     * @return Player object with specified username, or null
     * if no such player is found.
     */
    private Player getPlayer(String username){
        int playerIndex;
        if(( playerIndex = this.players.indexOf(new Player(username, null))) >= 0){
            return this.players.get(playerIndex);
        }
        return null;
    }
    
    /**
     * Returns a string array containing the usernames of online players
     * @return String array of usernames
     */
    public String[] getOnlineUsers(){
        return this.onlineUsers.toArray(new String[onlineUsers.size()]);
    }
    
    
    /**
     * Returns an array representation of the list of
     * connections
     * @return an array containing the active connections
     */
    public Connection[] getConnections(){
        return this.connections.toArray(new Connection[this.connections.size()]);
    }
    
    /**
     * Returns the number of active connections
     * @return the number of active connections
     */
    public int numConnections(){
        return this.connections.size();
    }
    
    /**
     * Removes a connection from the model
     * @param con connection to be removed
     */
    public void removeConnection(Connection con){
        if(con.getUsername() != null){
            this.onlineUsers.remove(con.getUsername());
        }
        this.connections.remove(con);
    }
    
    private void addOnlineUser(String user){
        this.onlineUsers.add(user);
    }
    /**
     * Sends the invite to the specified player
     * @param player the username that receives an invitation
     * @param invitedBy the username that sent the invitation
     * @return the invitation status represented as a String
     */
    public String sendInvite(String player, String invitedBy, String size){
        String message = "INVITED_BY " + invitedBy + " " + size;
        String response = "NOT INVITED";
        for(int i=0; i< connections.size();i++){
            if(this.connections.get(i).uname.equals(player)){
                response = "INVITED";
                connections.get(i).sendMessage(message);
                
            }
        }
        return response;
    }
    /**
     * Sends the IP address to the specified user
     * @param username the user receiving the IP address
     * @return the sent status represented as a String
     */
   public String sendIP(String ip, String portNum, String username, String size){
        String message = "P2P " + ip + " " + portNum + " " + size;
        String response = "IP NOT SENT TO " + username.toUpperCase();
        for(int i=0; i< connections.size();i++){
            if(this.connections.get(i).uname.equals(username)){
                connections.get(i).sendMessage(message);//issue if more than one instance with the same username
                response = "IP SENT TO " + username.toUpperCase();
            }
        }
        
        return response;
        
    } 
   /**
    * Gets the user statistics for the player with the given username
    * @param username username of the player
    * @return User's player statistics, or null if no Player with the
    * specified username is found.
    */
   public String getStats(String username){
       Player player = this.getPlayer(username);
       if(player != null){
           return "STATS " + player.getStats();
       }
       return null;
   }
    
}
