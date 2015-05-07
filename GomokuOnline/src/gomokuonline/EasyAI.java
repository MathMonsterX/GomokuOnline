/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuonline;

import java.util.Random;

/**
 *
 * @author clarissapendleton
 */
public class EasyAI {
    private int gameSize;
    private GameModel model;
    private final char AIChar = 'x';
    
    public EasyAI(int size){
        gameSize = size;
        model = new GameModel(size);
        model.setEasyAI(this);
        
    }
    
    public void generateMove(){
        Random rand = new Random();
        int row = rand.nextInt(gameSize);
        int col = rand.nextInt(gameSize);
        model.AIGamePlay(row, col, AIChar);
      
    }
    
}
