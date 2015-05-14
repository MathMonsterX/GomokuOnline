
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
 * This class is the model that controls the game. It is the model implemented
 * using the MVC design.
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
    private AI AI;

    /**
     * Constructor for AI Game Play
     * @param size 
     */
    GameModel(int size){
        this.size = size;
        this.matrix = new char[size][size];
    }
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
     * Checks to see if the current game is being played with by an AI
     * @return if the opponent is an AI
     */
    public boolean isAIGame(){
        return this.AI != null;
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
        char letter;
        if(input[5].equalsIgnoreCase("false")){
            
            if(this.playerChar == 'X'){
                controller.updateBoard(row, col, 'O');
                this.updateMatrix(row, col, 'O');
            }
            else{
                controller.updateBoard(row, col, 'X');
                this.updateMatrix(row, col, 'X');
            }
            controller.setEndMoveEnabled(true);
        }
        else{
            if(this.playerChar=='X')
              letter = 'O';
            else
                letter = 'X';
            controller.endGame(row, col,"Loser", letter);
        }
        
    }
    /**
     * Sends a string over the socket
     * @param message string to be sent over the network
     */
    public void sendMessage(String message){
        try {
            dataOut.writeBytes(message + "\n");
            dataOut.flush();
            System.out.println("Just sent " + message);
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
        if(hostSocket != null){//facilitates the creation of the connection
            this.hostGame();
        }
        
        try {
            
            String message;
           
            while((message = dataIn.readLine()) != null){
                System.out.println("Just received " + message);
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
    private String updateMatrix(int row, int column, char moveChar){
        String condition = "false";
        this.matrix[row][column]= moveChar;
        System.out.println("Matrix: " +"n = "+this.matrix.length + " m = " + this.matrix[0].length);
        //search through to see if player won
        System.out.println("Matrix size: "+matrix.length);
         for(int i=0; i<size; i++){
             for(int j=0; j<size; j++){
                 System.out.println("(i,j) = (" + i + "," + j+")") ;
                 
                 if(matrix[i][j]==playerChar){
                     if(j+4<size && matrix[i][j+1]==playerChar && matrix[i][j+2]==playerChar //changed i+4<=size to j+4<=size
                             && matrix[i][j+3]==playerChar&&matrix[i][j+4]==playerChar)
                         condition="true";
                     
                     if(i+4<size && j+4<size && matrix[i+1][j+1]==playerChar && matrix[i+2][j+2]==playerChar
                             && matrix[i+3][j+3]==playerChar&&matrix[i+4][j+4]==playerChar)
                         condition="true";
                     
                     if(i+4<size && matrix[i+1][j]==playerChar && matrix[i+2][j]==playerChar //changed j+4<=size to i+4<=size
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
    /**
     * This calls update matrix, checks to see if this player won. If they did,
     * the game is ended. sendMessage is always called.
     * @param row the row of the current move
     * @param column the column of the current move
     */
    public void makeMove(int row, int column){
        String condition = updateMatrix(row, column, this.playerChar);
        String message = "ROW " + row + " COLUMN " + column + " GAMEOVER "+ condition;
        if(condition.equals("true"))
            controller.endGame(row,column, "Winner", this.playerChar);
        this.sendMessage(message);
        
    }
    /**
     * This gets the size of the game board 
     * @return the size of the board
     */
    public int getSize(){
        return this.size;
    }
    /**
     * This gets this player's character-either X or O
     * @return the player's character
     */
    public char getPlayerChar(){
        return this.playerChar;
    }
    /**
     * This sets this player's character-either X or O
     * @param x the player's character
     */
    public void setPlayerChar(char x){
        this.playerChar=x;
    }
    
    /**
     * This sets the AI instance
     * @param ai the AI
     */
    public void setAI(AI ai){
        this.AI = ai;
    }
        
    /**
     * This is where the game with the AI occurs. It checks to see if anyone has won.
     * If someone has won, it checks to see who made the last move, decided if the
     * AI or player was the winner and ends the game. If someone has not won, 
     * the game continues. 
     * @param row the row of the current move
     * @param col the column of the current move
     * @param pChar the player's character
     */    
    public void AIGamePlay(int row, int col, char pChar){
        String condition = updateMatrixAI(row, col, pChar);
        if(condition.equals("true")){
            if(pChar=='X')
                controller.endGame(row, col, "Loser", pChar);
            else
                controller.endGame(row, col, "Winner", pChar);
        }
        else{
            if(pChar=='X'){
                controller.setEndMoveEnabled(true);
                controller.updateBoard(row, col, 'X');
            }
            else
                AI.generateMove(matrix);
        }
    }
    /**
     * Updates the matrix and checks to see if this player won
     * @param row the row
     * @param column the column
     * @return the String true if the player won and false otherwise
     */
    public String updateMatrixAI(int row, int column, char pChar){
        String condition = "false";
        this.matrix[row][column]=pChar;
       
        //search through to see if player won
         for(int i=0; i<size; i++){
             for(int j=0; j<size; j++){
                 if(matrix[i][j]==pChar){
                     if(j+4<size && matrix[i][j+1]==pChar && matrix[i][j+2]==pChar
                             && matrix[i][j+3]==pChar&&matrix[i][j+4]==pChar)
                         condition="true";
                     
                     if(i+4<size && j+4<size && matrix[i+1][j+1]==pChar && matrix[i+2][j+2]==pChar
                             && matrix[i+3][j+3]==pChar&&matrix[i+4][j+4]==pChar)
                         condition="true";
                     
                     if(i+4<size && matrix[i+1][j]==pChar && matrix[i+2][j]==pChar
                             && matrix[i+3][j]==pChar&&matrix[i+4][j]==pChar)
                         condition="true";
                     
                     if(i-4>=0 && j-4>=0 && matrix[i-1][j-1]==pChar && matrix[i-2][j-2]==pChar
                             && matrix[i-3][j-3]==pChar&&matrix[i-4][j-4]==pChar)
                         condition="true";
                         
                 }       
             }  
        }
        return condition;
    }
    
}
