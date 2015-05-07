package gomokuonline;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clarissapendleton
 */
public class GameModel implements Runnable{
    private GameController controller ;
    private Thread listenWorker;
    private BufferedReader dataIn;
    private DataOutputStream dataOut;
    private ServerSocket hostSocket;
    private Socket sock;
    private char[][] matrix;
    private int size;
    private char playerChar;
    
    /**
     * Default constructor. Assumes this GameModel will  host the connection,
     * using an arbitrary available port
     */
    GameModel(int size, char pChar){
        try {
            this.size = size;
            this.matrix = new char[size][size];
            this.playerChar = pChar;
            hostSocket = new ServerSocket(0);
           
        } catch (IOException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
    /**
     * Client constructor. Connects this GameModel to the host GameModel
     * at the specified IP address and port number
     * @param ip network address of the Game's host
     * @param portNum port that the Host is receiving messages on
     */
    GameModel(String ip, int portNum, int size, char pChar){
        try {
            this.size = size;
            this.matrix = new char[size][size];
            this.playerChar=pChar;
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
    private void hostGame(){
        try {
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
     */
    public void processMessage(String message){
        String[] input = message.split("\\s+");
        int row = Integer.parseInt(input[1]);
        int col = Integer.parseInt(input[3]);
        
        if(input[5].equalsIgnoreCase("false")){
            this.updateMatrix(row, col);
            controller.updateBoard(row, col);
            
        }
        else{
            controller.endGame(row, col);
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
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in sending message over server");
        }
    }
    
    public void setController(GameController control){
        this.controller = control;
    }
    /**
     * Receives messages over the peer to peer connection
     */
    private void receiveMessage(){
        if(hostSocket != null){
            this.hostGame();
        }
        
        try {
            int input;
            while((input = dataIn.read()) != -1 ){
                char msgChar = (char)input;
                String message = msgChar + "";
               while(dataIn.ready()){
                   msgChar = (char)dataIn.read();
                   message+= msgChar;
               }
               
               this.processMessage(message);
               
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
     * Gets the IP address associated with the server socket
     */
    public String getServerIP(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Updates the matrix and checks to see if this player won
     * @param row the row
     * @param column the column
     * @return the String true if the player won and false otherwise
     */
    private String updateMatrix(int row, int column){
        String condition = "false";
        this.matrix[row][column]=playerChar;
       
        //search through to see if player won
         for(int i=0; i<size; i++){
             for(int j=0; j<size; j++){
                 if(matrix[i][j]==playerChar){
                     if(i+4<=size && matrix[i][j+1]==playerChar && matrix[i][j+2]==playerChar
                             && matrix[i][j+3]==playerChar&&matrix[i][j+4]==playerChar)
                         condition="true";
                     
                     if(i+4<=size && j+4<=size && matrix[i+1][j+1]==playerChar && matrix[i+2][j+2]==playerChar
                             && matrix[i+3][j+3]==playerChar&&matrix[i+4][j+4]==playerChar)
                         condition="true";
                     
                     if(j+4<=size && matrix[i+1][j]==playerChar && matrix[i+2][j]==playerChar
                             && matrix[i+3][j]==playerChar&&matrix[i+4][j]==playerChar)
                         condition="true";
                     
                     if(i-4>=0 && j-4>=0 && matrix[i-1][j-1]==playerChar && matrix[i-2][j-2]==playerChar
                             && matrix[i-3][j-3]==playerChar&&matrix[i-4][j-4]==playerChar)
                         condition="true";
                         
                 }       
             }  
        }
        return condition;
    }
    
    public void makeMove(int row, int column){
        String condition = updateMatrix(row, column);
        String message = "ROW " + row + " COLUMN " + column + " GAMEOVER "+ condition;
        this.sendMessage(message);
        
    }
    
    public int getSize(){
        return this.size;
    }
    
    
}
