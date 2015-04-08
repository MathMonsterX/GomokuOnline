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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
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
    private Map<String, String> userLogins;
    private File loginDatabase = new File("loginData.txt");
    
    
    /**
     * Constructor, creates a new instance of an GomokuServerModel
     */
    public GomokuServerModel(){
        this.connections = Collections.synchronizedList(new ArrayList<Connection>());
        this.messages = Collections.synchronizedList(new ArrayList<String>());
        this.onlineUsers = Collections.synchronizedSortedSet(new TreeSet<String>()); 
        this.userLogins = Collections.synchronizedMap(new HashMap<String, String>());
        this.getLoginsFromDatabase();
    }
    
    /**
     * Gets usernames and passwords from the database file
     */ 
    private void getLoginsFromDatabase(){
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
    }
    
    /**
     * Updates the database with the given username and password pair
     * @param uname username to be added
     * @param pass password to be added
     */
    private void updateDatabase(String uname, String pass){
        try {
            FileWriter writer = new FileWriter(loginDatabase, true);
            writer.append(uname + " " + pass + "\n");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
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
        if(!userLogins.containsKey(uname)){
            userLogins.put(uname, pass);
            this.updateDatabase(uname, pass);
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
        String storedPass;
        if((storedPass = this.userLogins.get(uname)) != null ){
            if(storedPass.equals(pass)){
                response = "AUTHENTICATION success";
                onlineUsers.add(uname);
            }
                
            
        }
        return response;
    }
    
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
    
}
