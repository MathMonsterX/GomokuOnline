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
    
    public void generateMove(char[][] matrix){
        Random rand = new Random();
        boolean condition = true;
        int row = rand.nextInt(gameSize);
        int col = rand.nextInt(gameSize);
        while(condition){
            if(matrix[row][col]=='x'|| matrix[row][col]=='o'){
                row = rand.nextInt(gameSize);
                col = rand.nextInt(gameSize);
            }
            else
                condition=false;
        }
        model.AIGamePlay(row, col, AIChar);
      
    }
    
}
