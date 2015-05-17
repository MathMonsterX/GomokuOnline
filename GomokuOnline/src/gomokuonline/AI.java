/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuonline;

import java.util.Random;

/**
 * This class is the AI that plays against a player. 
 * It contains three different difficulty levels- easy, intermediate, and hard.
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
    private int count;
    private AdminModel adminModel;
    
    /**
     * The constructor for the AI. Initializes size, difflvl, and count.
     * @param size the size of the game
     * @param difflvl the difficulty level of the AI
     */
    public AI(int size, int difflvl){
        gameSize = size;
        difficultyLevel=difflvl;
        count=0;
        
        
    }
    /**
     * Generates a move based on the difficulty of the AI
     * @param matrix the char matrix of X's and O's that correspond to the game board
     */
    public void generateMove(char[][] matrix){
        //easy AI
        if(difficultyLevel==0){
            easyMove(matrix);
        }
        //Intermediate AI
        else if(difficultyLevel==1){
            notEasyMove(matrix, 'X');
        }
        //Hard AI
        else
           notEasyMove(matrix, 'O');

        count++;
        model.AIGamePlay(row, column, AIChar);
      
    }
    /**
     * Generates an easy move
     * @param matrix the char matrix of X's and O's that correspond to the game board
     */
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
    
    /**
     * Generates either an intermediate or hard move based on the searchChar. 
     * The intermediate plays purely offensive, except for the first move, and 
     * the hard plays purely defensive
     * A random move is generated based off the eight spots around the 'X' or 'O'
     * @param matrix the char matrix of X's and O's that correspond to the game board
     * @param searchChar either the chars 'X' or 'O' that the program searches for 
     *          in the matrix
     */
    public void notEasyMove(char[][] matrix, char searchChar){
        Random rand = new Random();
        int randomNumber = rand.nextInt(7);
        boolean condition=true;
        if (count == 0){
            count++;
            notEasyMove(matrix,'O');
        }
        else{
        
        while(condition){
        outerloop:
        for(int i=0;i<matrix.length;i++){
            for(int j=0; j<matrix.length;j++){
                if(matrix[i][j]== searchChar){
                    if(randomNumber==0 && j<gameSize-1 && matrix[i][j+1]!='X' && matrix[i][j+1]!='O'){
                        row=i;
                        column=j+1;
                        condition=false;
                        break outerloop;
                    }
                    else if(randomNumber==1&&i<gameSize-1 && j<gameSize-1 &&matrix[i+1][j+1]!='X' && matrix[i+1][j+1]!='O'){
                        row=i+1;
                        column=j+1;
                        condition=false;
                        break outerloop;
                    }
                    else if(randomNumber==2&& i<gameSize-1 && matrix[i+1][j]!='X' && matrix[i+1][j]!='O'){
                        row=i+1;
                        column=j;
                        condition=false;
                        break outerloop;
                    }
                    else if(randomNumber==3&& i<gameSize-1 && j>0 &&matrix[i+1][j-1]!='X' && matrix[i+1][j-1]!='O'){
                        row=i+1;
                        column=j-1;
                        condition=false;
                        break outerloop;
                    }
                    else if(randomNumber==4&& j>0 &&matrix[i][j-1]!='X' && matrix[i][j-1]!='O'){
                        row=i;
                        column=j-1;
                        condition=false;
                        break outerloop;
                    }
                    else if(randomNumber==5&& i>0 && j>0 &&matrix[i-1][j-1]!='X' && matrix[i-1][j-1]!='O'){
                        row=i-1;
                        column=j-1;
                        condition=false;
                        break outerloop;
                    }
                    else if(randomNumber==6&& i>0 &&matrix[i-1][j]!='X' && matrix[i-1][j]!='O'){
                        row=i-1;
                        column=j;
                        condition=false;
                        break outerloop;
                    }
                    else if(randomNumber==7&& i>0 && j<gameSize-1 &&matrix[i-1][j+1]!='X' && matrix[i-1][j+1]!='O'){
                        row=i-1;
                        column=j+1;
                        condition=false;
                        break outerloop;
                    }     
                }
            }
        }
        if(condition==true)
            randomNumber = rand.nextInt(7);
       }
       
        } 
    }
    
    /**
     * Sets the difficulty level of the AI
     * @param diffLvl the difficulty level
     */
    public void setDifficultyLevel(int diffLvl){
        this.difficultyLevel=diffLvl;
    }
    
    /**
     * Creates an instance of GameModel and GameController and sets the game up 
     * appropriately.
     */
    public void createModel() {
        model = new GameModel(gameSize);
        model.setAI(this);
        controller = new GameController();
        controller.setModel(model);
        model.setController(controller);
        model.setAdminModel(adminModel);
        controller.createView();
        model.setPlayerChar('O');

    }
    
    public void setAdmin(AdminModel model){
        this.adminModel = model;
    }
    
     
    
}
