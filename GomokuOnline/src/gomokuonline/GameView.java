

package gomokuonline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 *
 * @author clarissapendleton
 */
public class GameView extends javax.swing.JPanel {
    private GameController controller;
    private JFrame gameViewFrame;
    private GameButton[][] buttonGrid;
    /**
     * Creates new form GameView
     */
    public GameView() {
        initComponents();
        lblPlayer.setText("");
        lblPlayerMove.setVisible(false);
        lblOpponentMove.setVisible(false);
    }
    
    
    
    /**
     * Instantiates a width X length board of buttons.
     * @param width width of the board
     * @param length length of the board
     */
     public void setupBoard(int width, int length){
        ActionListener buttonListener = new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameButtonActionPerformed(evt);
            }
        };
         
         
        this.buttonGrid = new GameButton[width][length];
        gameBoardPanel.setLayout(new GridLayout(width, length));
        for(int y = 0; y<length; y++){
                for(int x = 0; x <width; x++){
                        GameButton button = new GameButton(x, y, " ");
                        button.addActionListener(buttonListener);
                        buttonGrid[x][y] = button;
                        gameBoardPanel.add(buttonGrid[x][y]);

                }
        }
        this.setLayout(new BorderLayout());
        this.add(gameBoardPanel, BorderLayout.CENTER);
        this.add(this.btnEndMove, BorderLayout.PAGE_END);
        
    }
     
    private void gameButtonActionPerformed(java.awt.event.ActionEvent evt){
        for(GameButton[] buttons : buttonGrid){
            for(GameButton button : buttons){
                button.setSelected(false);
                button.setBackground(new JButton().getBackground());
                button.setOpaque(false);
                button.setBorderPainted(true);
            }
            
        }
        GameButton button = (GameButton)evt.getSource();
        button.setSelected(true);
        button.setBackground(Color.blue);
        button.setOpaque(true);
        button.setBorderPainted(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        btnEndMove = new javax.swing.JButton();
        lblPlayerMove = new javax.swing.JLabel();
        lblOpponentMove = new javax.swing.JLabel();
        lblPlayer = new javax.swing.JLabel();
        gameBoardPanel = new javax.swing.JPanel();

        btnEndMove.setText("End Move");
        btnEndMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndMoveActionPerformed(evt);
            }
        });

        lblPlayerMove.setText("Your Name- Your Move! ");

        lblOpponentMove.setText("Opponent Name- Your Move!");

        lblPlayer.setText("Player");

        javax.swing.GroupLayout gameBoardPanelLayout = new javax.swing.GroupLayout(gameBoardPanel);
        gameBoardPanel.setLayout(gameBoardPanelLayout);
        gameBoardPanelLayout.setHorizontalGroup(
            gameBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );
        gameBoardPanelLayout.setVerticalGroup(
            gameBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOpponentMove)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPlayerMove)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                                .addComponent(btnEndMove))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(gameBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPlayer))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameBoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblOpponentMove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEndMove)
                    .addComponent(lblPlayerMove))
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void btnEndMoveActionPerformed(java.awt.event.ActionEvent evt) {                                           
        int row, col = 0;
        for(int i = 0; i < buttonGrid.length; i++){
            for(int j = 0; j < buttonGrid.length; j++){
                GameButton button = buttonGrid[i][j];
                if(button.isSelected()){ 
                    this.btnEndMove.setEnabled(false);
                    this.controller.makeMove(i, j);
                    this.controller.setPlayerChar('O');
                    this.updateBoard(i, j, this.controller.getPlayerChar());
                    
                }
   
            }
        }
    }                                          

    public void setbtnEndMOveEnabled(boolean enabled){
        this.btnEndMove.setEnabled(enabled);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnEndMove;
    private javax.swing.JPanel gameBoardPanel;
    private javax.swing.JLabel lblOpponentMove;
    private javax.swing.JLabel lblPlayer;
    private javax.swing.JLabel lblPlayerMove;
    // End of variables declaration                   

public void setController(GameController controller){
    this.controller = controller;
}
public void setFrame(JFrame frame){
    this.gameViewFrame = frame;
    gameViewFrame.setTitle("Gomoku");

}
public void updateLblPlayerMove(String message){
    
}
public void updateLblOpponentMove(String message){
    
}
public void updateBoard(int row, int column, char playerChar){
     GameButton button = buttonGrid[row][column];
     button.setText(playerChar + "");
     button.setEnabled(false);
     button.setSelected(false);
     button.setBackground(new JButton().getBackground());
    
        
}
    
public void endGame(int row, int column, String status){
        
}

}


