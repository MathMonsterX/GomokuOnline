package gomokuonline;

import java.io.BufferedReader;
import java.io.DataOutputStream;

/**
 *
 * @author clarissapendleton
 */
public class GameModel implements Runnable{
    private GameController controller ;
    private Thread listenWorker;
    private BufferedReader dataIn;
    private DataOutputStream dataOut;
    
    GameModel(){
        
    }
    
    GameModel(String ip){
        
    }
    
    @Override
    public void run(){
        
    }
}
