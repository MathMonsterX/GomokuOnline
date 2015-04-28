package gomokuonline;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clarissapendleton
 */
public class GameModel implements Runnable{
    private static final int DEFAULT_HOSTPORT = 8080;
    private GameController controller ;
    private Thread listenWorker;
    private BufferedReader dataIn;
    private DataOutputStream dataOut;
    private ServerSocket hostSocket;
    private Socket sock;
    
    /**
     * Default constructor. Assumes this GameModel will  host the connection,
     * using the DEFAULT_HOSTPORT 8080 for the server socket
     */
    GameModel(){
        this.hostGame(0);
    }
    
    /**
     * Host Constructor. Allows this GameModel to host the peer-to-peer connection, using
     * hostPort as the port for the server socket
     * @param hostPort port that the ServerSocket is bound to
     */
    GameModel(int hostPort){
        this.hostGame(hostPort);
    }
    
    
    /**
     * Client constructor. Connects this GameModel to the host GameModel
     * at the specified IP address and port number
     * @param ip network address of the Game's host
     * @param portNum port that the Host is receiving messages on
     */
    GameModel(String ip, int portNum){
        try {
            sock = new Socket(ip, portNum);
            dataOut = new DataOutputStream(sock.getOutputStream());
            dataIn  = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Listens for another Game's request to connect, then sets up a 
     * peer-to-peer connection between this Game and that Game,
     * @param hostPort the port on which this GameModel will listen for
     * requests.
     */
    private void hostGame(int hostPort){
        try {
            hostSocket = new ServerSocket(hostPort);
            this.sock = hostSocket.accept();
            dataOut = new DataOutputStream(sock.getOutputStream());
            dataIn  = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            hostSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Processes a message received from another player
     * @return 
     */
    private String processMessage(){
        //do something
        return "";
    }
    
    /**
     * Receives messages over the peer to peer connection
     */
    private void receiveMessage(){
        try {
            int input;
            while((input = dataIn.read()) != -1 ){
                char msgChar = (char)input;
                String message = msgChar + "";
               while(dataIn.ready()){
                   msgChar = (char)dataIn.read();
                   message+= msgChar;
               }
               
               this.processMessage();
               
            }
            this.sock.close();
            
                
        } catch (IOException ex) {
            
            
          
        }
    }
    /**
     * Thread of execution that listens over the peer-to-peer connection
     */
    @Override
    public void run(){
        this.receiveMessage();
    }
    
    
    /**
     * Starts the thread that listens for
     * messages over the socket
     */
    public void listen(){
        listenWorker = new Thread(this);
        listenWorker.start();
    }
    
    /**
     * Gets the port on which the server socket is listening
     * @return the port number of the server socket
     */
    public int getServerPort(){
        return this.hostSocket.getLocalPort();
    }
    
    /**
     * Get's the IP address associated with the server socket
     */
    public String getServerIP(){
        return this.hostSocket.getInetAddress().getHostAddress();
    }
}
