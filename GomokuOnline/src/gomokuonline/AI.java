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
public class AI {
    private int gameSize;
    private GameModel model;
    private GameController controller;
    private final char AIChar = 'X';
    private int difficultyLevel;
    private int row;
    private int column;
    
    public AI(int size, int difflvl){
        gameSize = size;
        difficultyLevel=difflvl;
        
        
    }
    
    public void generateMove(char[][] matrix){
        if(difficultyLevel==0){
            easyMove(matrix);
        }
        else if(difficultyLevel==1){
            notEasyMove(matrix, 'X');
        }
        else
           notEasyMove(matrix, 'O');

        model.AIGamePlay(row, column, AIChar);
      
    }
    
    public void easyMove(char[][] matrix){
        Random rand = new Random();
        boolean condition = true;
        row = rand.nextInt(gameSize);
        column = rand.nextInt(gameSize);
        while(condition){
            if(matrix[row][column]=='X'|| matrix[row][column]=='O'){
                row = rand.nextInt(gameSize);
                column = rand.nextInt(gameSize);
            }
            else
                condition=false;
        }
    }
    
    public void notEasyMove(char[][] matrix, char searchChar){
        Random rand = new Random();
        int randomNumber = rand.nextInt(7);
        boolean condition=false;
        outerloop:
        for(int i=0;i<matrix.length;i++){
            for(int j=0; j<matrix.length;j++){
                if(matrix[i][j]== searchChar){
                    if(randomNumber==0 && j<gameSize-1 && matrix[i][j+1]!='X' && matrix[i][j+1]!='O'){
                        row=i;
                        column=j+1;
                        condition=true;
                        break outerloop;
                    }
                    else if(randomNumber==1&&i<gameSize-1 && j<gameSize-1 &&matrix[i+1][j+1]!='X' && matrix[i+1][j+1]!='O'){
                        row=i+1;
                        column=j+1;
                        condition=true;
                        break outerloop;
                    }
                    else if(randomNumber==2&& i<gameSize-1 && matrix[i+1][j]!='X' && matrix[i+1][j]!='O'){
                        row=i+1;
                        column=j;
                        condition=true;
                        break outerloop;
                    }
                    else if(randomNumber==3&& i<gameSize-1 && j>0 &&matrix[i+1][j-1]!='X' && matrix[i+1][j-1]!='O'){
                        row=i+1;
                        column=j-1;
                        condition=true;
                        break outerloop;
                    }
                    else if(randomNumber==4&& j>0 &&matrix[i][j-1]!='X' && matrix[i][j-1]!='O'){
                        row=i;
                        column=j-1;
                        condition=true;
                        break outerloop;
                    }
                    else if(randomNumber==5&& i>0 && j>0 &&matrix[i-1][j-1]!='X' && matrix[i-1][j-1]!='O'){
                        row=i-1;
                        column=j-1;
                        condition=true;
                        break outerloop;
                    }
                    else if(randomNumber==6&& i>0 &&matrix[i-1][j]!='X' && matrix[i-1][j]!='O'){
                        row=i-1;
                        column=j;
                        condition=true;
                        break outerloop;
                    }
                    else if(randomNumber==7&& i>0 && j<gameSize-1 &&matrix[i-1][j+1]!='X' && matrix[i-1][j+1]!='O'){
                        row=i-1;
                        column=j+1;
                        condition=true;
                        break outerloop;
                    }     
                }
            }
        }
        if(condition==false)
            notEasyMove(matrix,'O');
        
    }
    
    
    
    public void setDifficultyLevel(int diffLvl){
        this.difficultyLevel=diffLvl;
    }

    public void createModel() {
        model = new GameModel(gameSize);
        model.setAI(this);
        controller = new GameController();
        controller.setModel(model);
        model.setController(controller);
        controller.createView();
        model.setPlayerChar('O');

    }
    
     
    
}
