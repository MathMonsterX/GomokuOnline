
/**
*Name: Tom Kreamer
*Assignment: Lab 3
*Title: Connection
*Course: CSCE 320
*Semester: Spring 2015
*Instructor: George Hauser
*Date: 3/6/2015
*Sources consulted: None
*Creativity: None
*Program Description: Connection object representing a connection to the EchoServer.
* Maintains a reference to its parent EchoServerController, allowing the Connection
* to be removed from the model once it is terminated. 
*/


package gomokuonlineserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Connection extends Thread implements Comparable<Connection>{
    private Socket sock;
    private BufferedReader dataIn;
    private DataOutputStream dataOut;
    private GomokuServerController serverController;
    
    
    /**
     * Constructs a new Connection 
     * @param s socket associated with this connection
     * @param server EchoServerController associated with this connection
     */
    public Connection(Socket s, GomokuServerController server ) {
        try {
            this.sock = s;
            this.serverController = server;
            dataIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            dataOut = new DataOutputStream(sock.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in setting up connection");
        }
        
    }
    
    /**
     * Sends a string over the socket
     * @param message string to be sent over the network
     */
    public void sendMessage(String message){
        try {
            dataOut.writeBytes(message);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in sending message over server");
        }
    }
    
    /**
     * Thread that listens for input over the socket, then passes the input
     * to the controller to be broadcast
     */
    public void run(){
        try {
            int input;
            while((input = dataIn.read()) != -1 ){
                char msgChar = (char)input;
                String message = msgChar + "";
               while(dataIn.ready()){
                   msgChar = (char)dataIn.read();
                   message+= msgChar;
               }
               String response = this.serverController.processMessage(message);
               this.sendMessage(response);
            }
            this.sock.close();
            this.serverController.removeConnection(this);
                
        } catch (IOException ex) {
            
            this.serverController.removeConnection(this);
          
        }
    }

    /**
     * Overrides the compareTo method
     * @param that Connection to be compared with this
     * @return 0 if the objects are equal, a negative integer
     * if that is greater than this, or a positive integer 
     * if this connection is greater than that
     */
    @Override
    public int compareTo(Connection that) {
        return this.sock.getLocalPort() - that.sock.getLocalPort();
    }
    
}

