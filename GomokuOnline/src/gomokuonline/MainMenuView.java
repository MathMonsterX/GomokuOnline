
package gomokuonline;

import javax.swing.JFrame;

/**
 * This is the view that the user can choose to play online, play offline, or
 * view statistics.
 * @author clarissapendleton
 */
public class MainMenuView extends javax.swing.JPanel {
    private JFrame mainMenuFrame;
    private MainMenuController controller;
    
    /**
     * Creates new form MainMenuView
     */
    public MainMenuView() {
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

        btnOnline = new javax.swing.JButton();
        btnOffline = new javax.swing.JButton();
        btnStats = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        btnOnline.setText("Play Online");
        btnOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnlineActionPerformed(evt);
            }
        });

        btnOffline.setText("Play Offline");
        btnOffline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOfflineActionPerformed(evt);
            }
        });

        btnStats.setText("View Stats");
        btnStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatsActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gomoku!");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnOnline, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStats, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnOffline, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOnline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOffline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStats)
                .addContainerGap(130, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnlineActionPerformed
        btnOnline_Click();
    }//GEN-LAST:event_btnOnlineActionPerformed

    private void btnOfflineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOfflineActionPerformed
        btnOffline_Click();
    }//GEN-LAST:event_btnOfflineActionPerformed

    private void btnStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatsActionPerformed
        btnStats_Click();
    }//GEN-LAST:event_btnStatsActionPerformed
    /**
     * Sets this controller.
     * @param controller the MainMenuController object
     */
    public void setController(MainMenuController controller){
        this.controller = controller;
    }
    /**
     * Sets this mainMenuFrame to false and opens the OnlineMenu view via the model.
     */
    private void btnOnline_Click(){
        mainMenuFrame.setVisible(false);
        controller.openOnlineMenuView();
    }
    /**
     * Sets the visibility to this mainMenuFrame to true.
     */
    public void makeVisible(){
        mainMenuFrame.setVisible(true);
    }
    /**
     * Sets mainMenuFrame
     * @param frame the JFrame object
     */
    public void setFrame(JFrame frame){
        this.mainMenuFrame = frame;
        mainMenuFrame.setTitle("Main Menu");
    }

    /**
     * Sets this view to invisible and opens the view to choose an AI
     */
    private void btnOffline_Click(){
        mainMenuFrame.setVisible(false);
        controller.openChooseAIView();
    }
    
    /**
     * Sets this view to invisible and opens the statistics view.
     */
    private void btnStats_Click(){
        mainMenuFrame.setVisible(false);
        controller.openStatsView();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOffline;
    private javax.swing.JButton btnOnline;
    private javax.swing.JButton btnStats;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables


   
}
