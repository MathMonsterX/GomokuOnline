
package gomokuonline;




/**
 *
 * @author clarissapendleton
 */
public class MainMenuView extends javax.swing.JPanel {
    MainMenuController controller;
    
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

        btnOnline.setText("Play Online");

        btnOffline.setText("Play Offline");

        btnStats.setText("View Stats");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOffline)
                    .addComponent(btnStats, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOnline, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btnOnline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOffline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStats)
                .addContainerGap(130, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

public void setController(MainMenuController controller){
    this.controller=controller;
    
}
private void btnOnline_Click(java.awt.event.ActionEvent evt){
    this.setVisible(false);
    
}

private void btnOffline_Click(java.awt.event.ActionEvent evt){
    
}
private void btnStats_Click(java.awt.event.ActionEvent evt){
    
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOffline;
    private javax.swing.JButton btnOnline;
    private javax.swing.JButton btnStats;
    // End of variables declaration//GEN-END:variables
}
