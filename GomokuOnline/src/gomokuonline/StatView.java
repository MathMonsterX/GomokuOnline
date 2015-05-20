package gomokuonline;
import javax.swing.JFrame;

/**
 *
 * @author clarissapendleton
 */
public class StatView extends javax.swing.JPanel {
    private JFrame statFrame;
    private StatController controller;
    /**
     * Creates new form StatsView
     */
    public StatView() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultLabel = new javax.swing.JLabel();
        resultsTA = new javax.swing.JScrollPane();
        statTA = new javax.swing.JTextArea();
        btnBack = new javax.swing.JButton();

        resultLabel.setText("Game Results");

        statTA.setEditable(false);
        statTA.setColumns(20);
        statTA.setRows(5);
        resultsTA.setViewportView(statTA);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(resultsTA, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBack)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(resultLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(resultLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultsTA, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        statFrame.setVisible(false);
        controller.openMainMenuView();
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JScrollPane resultsTA;
    private javax.swing.JTextArea statTA;
    // End of variables declaration//GEN-END:variables

/**
 * Sets this view to invisible and opens the main menu.
 * @param evt 
 */
 private void btnCloseActionPerformed(java.awt.event.WindowEvent evt){
    statFrame.setVisible(false);
    controller.openMainMenuView();
}
/**
* Sets this view to visible.
*/
public void makeVisible(){
    statFrame.setVisible(true);
    
}

/**
 * controller setter
 * @param controller the StatController 
 */
public void setController(StatController controller) {
    this.controller=controller;
}
/**
 * Posts the statistics of this player to the view
 * @param message 
 */
public void postStats(String message){
    this.statTA.setText(message);
}
/**
 * Sets statFrame to frame
 * @param frame the JFrame
 */
public void setFrame(JFrame frame) {
    this.statFrame=frame;
    statFrame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                btnCloseActionPerformed(windowEvent);
            }
            
        });
}

}
