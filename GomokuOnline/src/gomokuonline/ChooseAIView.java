
package gomokuonline;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;

/**
 * This view lets the user choose the AI they want to play with
 * @author clarissapendleton
 */
public class ChooseAIView extends javax.swing.JPanel {
    private ChooseAIController controller;
    private JFrame chooseAIFrame;
    private String difficultyLevel;
    /**
     * Creates new form AIOptionsView
     */
    public ChooseAIView() {
        initComponents();
        ButtonGroup group = new ButtonGroup();
        group.add(rbEasy);
        group.add(rbHard);
        group.add(rbIntermediate);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diffucultyLabel = new javax.swing.JLabel();
        sizeLabel = new javax.swing.JLabel();
        btnPlay = new javax.swing.JButton();
        rbEasy = new javax.swing.JRadioButton();
        rbIntermediate = new javax.swing.JRadioButton();
        rbHard = new javax.swing.JRadioButton();
        cbBoard = new javax.swing.JComboBox();
        btnBack = new javax.swing.JButton();

        diffucultyLabel.setText("Select Level of Difficulty");

        sizeLabel.setText("Input Size of Board");

        btnPlay.setText("Play!");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        rbEasy.setText("Easy");
        rbEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEasyActionPerformed(evt);
            }
        });

        rbIntermediate.setText("Intermediate");
        rbIntermediate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbIntermediateActionPerformed(evt);
            }
        });

        rbHard.setText("Difficult");
        rbHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbHardActionPerformed(evt);
            }
        });

        cbBoard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30x30", "40x40", "50x50" }));
        cbBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBoardActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbEasy)
                    .addComponent(rbIntermediate)
                    .addComponent(rbHard)
                    .addComponent(diffucultyLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(sizeLabel)
                        .addGap(81, 81, 81))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnPlay))
                            .addComponent(cbBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diffucultyLabel)
                    .addComponent(sizeLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbEasy)
                        .addGap(8, 8, 8)
                        .addComponent(rbIntermediate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbHard)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlay)
                        .addGap(121, 121, 121)
                        .addComponent(btnBack)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        String gameSize = (String)cbBoard.getSelectedItem();
        controller.play(difficultyLevel, gameSize);
    }//GEN-LAST:event_btnPlayActionPerformed

    private void rbIntermediateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbIntermediateActionPerformed
        this.difficultyLevel="Intermediate";
    }//GEN-LAST:event_rbIntermediateActionPerformed

    private void cbBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBoardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBoardActionPerformed

    private void rbEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEasyActionPerformed
        this.difficultyLevel="Easy";
    }//GEN-LAST:event_rbEasyActionPerformed

    private void rbHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbHardActionPerformed
        this.difficultyLevel="Hard";
    }//GEN-LAST:event_rbHardActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        controller.openMainMenuView();
        chooseAIFrame.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPlay;
    private javax.swing.JComboBox cbBoard;
    private javax.swing.JLabel diffucultyLabel;
    private javax.swing.JRadioButton rbEasy;
    private javax.swing.JRadioButton rbHard;
    private javax.swing.JRadioButton rbIntermediate;
    private javax.swing.JLabel sizeLabel;
    // End of variables declaration//GEN-END:variables
    /**
     * controller setter
     * @param control the ChooseAIController
     */
    public void setController(ChooseAIController control){
        this.controller=control;
    }
    /**
     * chooseAIFrame setter
     * @param frame the JFrame
     */
    public void setFrame(JFrame frame){
        this.chooseAIFrame = frame;
        chooseAIFrame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                btnCloseActionPerformed(windowEvent);
            }
            
        });
    }
    /**
     * Sets the frame to visible
     */
    public void makeVisible(){
        chooseAIFrame.setVisible(true);
    }
    /**
     * Sets the frame to invisible and opens the main menu
     * @param evt 
     */
    private void btnCloseActionPerformed(java.awt.event.WindowEvent evt){
        chooseAIFrame.setVisible(false);
        controller.openMainMenuView();
    }
    
}

