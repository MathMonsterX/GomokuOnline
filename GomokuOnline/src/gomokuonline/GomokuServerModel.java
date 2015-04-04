/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuonline;

import java.util.ArrayList;

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
    private ArrayList<Connection> connections;
    private ArrayList<String> messages;
    
    /**
     * Constructor, creates a new instance of an EchoServerModel
     */
    public GomokuServerModel(){
        this.connections = new ArrayList<Connection>();
        this.messages = new ArrayList<String>();
    }
    
    /**
     * Adds a connection to the model's list of 
     * connections.
     * @param con 
     */
    public void addConnection(Connection con){
        this.connections.add(con);
        String[] p;
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
        this.connections.remove(con);
    }
    
}
