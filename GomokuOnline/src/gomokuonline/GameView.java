

package gomokuonline;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

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
        this.buttonGrid = new GameButton[width][length];
        gameBoardPanel.setLayout(new GridLayout(width, length));
        for(int y = 0; y<length; y++){
                for(int x = 0; x <width; x++){
                        this.buttonGrid[x][y] = new GameButton(x,y," ");
                        gameBoardPanel.add(buttonGrid[x][y]);

                }
        }
        
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
                row = i;
                col = j;
                
                
                        
                
            }
        }
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
public void updateBoard(int row, int column){
        
}
    
public void endGame(int row, int column){
        
}

}




