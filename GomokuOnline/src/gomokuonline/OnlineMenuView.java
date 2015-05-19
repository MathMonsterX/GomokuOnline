

package gomokuonline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.accessibility.AccessibleContext;
import javax.swing.JFrame;
import javax.swing.ListModel;

/**
 * This is the view that displays a list of online players and play requests
 * @author clarissapendleton
 */
public class OnlineMenuView extends javax.swing.JPanel {
    private AdminModel model;
    private OnlineMenuController controller;
    private JFrame onlineMenuFrame;
    /**
     * Creates new form OnlineMenuView
     */
    public OnlineMenuView() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        listOnlinePlayers = new javax.swing.JList();
        playerLabel = new javax.swing.JLabel();
        btnSendRequest = new javax.swing.JButton();
        cbBoard = new javax.swing.JComboBox();
        requestsLabel = new javax.swing.JLabel();
        listRequests = new javax.swing.JScrollPane();
        listReq = new javax.swing.JList();
        btnAccept = new javax.swing.JButton();
        btnDecline = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        jScrollPane1.setViewportView(listOnlinePlayers);

        playerLabel.setText("Online Players");

        btnSendRequest.setText("Ask Player");
        btnSendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendRequestActionPerformed(evt);
            }
        });

        cbBoard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30x30", "40x40", "50x50" }));
        cbBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBoardActionPerformed(evt);
            }
        });

        requestsLabel.setText("Play Requests");

        listRequests.setViewportView(listReq);

        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        btnDecline.setText("Reject");
        btnDecline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeclineActionPerformed(evt);
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
                .addGap(38, 38, 38)
                .addComponent(playerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(requestsLabel)
                .addGap(67, 67, 67))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAccept)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDecline))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(listRequests, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerLabel)
                    .addComponent(requestsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listRequests, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSendRequest)
                    .addComponent(cbBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAccept)
                    .addComponent(btnDecline))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendRequestActionPerformed
        String player;
        player = (String)listOnlinePlayers.getSelectedValue();
        btnSendRequest_Click(player);
    }//GEN-LAST:event_btnSendRequestActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        String player;
        player = (String)listReq.getSelectedValue();
        this.btnAccept_Click(player);
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnDeclineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeclineActionPerformed
        String player;
        player = (String)listReq.getSelectedValue();
        btnDecline_Click(player);
        
    }//GEN-LAST:event_btnDeclineActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        btnBack_Click();
    }//GEN-LAST:event_btnBackActionPerformed

    private void cbBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBoardActionPerformed
        
    }//GEN-LAST:event_cbBoardActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDecline;
    private javax.swing.JButton btnSendRequest;
    private javax.swing.JComboBox cbBoard;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listOnlinePlayers;
    private javax.swing.JList listReq;
    private javax.swing.JScrollPane listRequests;
    private javax.swing.JLabel playerLabel;
    private javax.swing.JLabel requestsLabel;
    // End of variables declaration//GEN-END:variables
/**
 * Sets this controller
 * @param controller the OnlineMenuController object
 */
public void setController(OnlineMenuController controller){
    this.controller = controller;
}
/**
 * Sets this model
 * @param model the AdminModel object
 */
public void setModel(AdminModel model){
    this.model = model;
}
/*public void updateOnlinePlayers(Player player){
    
}
public void updateRequests(Player player, Gomoku board){
    
}*/
/**
 * This invites player to play a game
 * @param player the username of the player getting an invitation
 */
    private void btnSendRequest_Click(String player){
        String gameSize = (String)cbBoard.getSelectedItem();
        controller.invite(player, gameSize);
        
    }
    /**
     * When the user accepts an invitation, this method calls the accept method
     * in the controller
     */
    private void btnAccept_Click(String player){
        
        this.removePlayer(player);
        controller.accept(player);
        
    }
    /**
     * Removes a player from the list 
     * @param player the player being removed
     */
    private void btnDecline_Click(String player){
        this.removePlayer(player);
    }
    /**
     * Sets this view to invisible and goes back to the main menu
     */
    private void btnBack_Click(){
        onlineMenuFrame.setVisible(false);
        controller.back();
    }
    /**
     * Sets the frame visibility to true
     */
    public void makeVisible(){
        onlineMenuFrame.setVisible(true);
    }
    /**
     * Posts a list of online players on the view
     * @param players the list of online players
     */
    public void post(String[] players){
       // String[] onlinePlayers = new String[players.length];
        ArrayList<String> onlinePlayers = new ArrayList<>();
        int count=0;
        ArrayList<String> requests = new ArrayList<>(Arrays.asList(this.getRequests()));
        ArrayList<String> names = new ArrayList<>();
        
        for(String request: requests){
                if(request != null){
                String[] temp = request.split("\\s+");
                names.add(temp[0]);
            }
        }
        
        for(int i=0;i<players.length;i++){
            if(!players[i].equals(controller.getUname()) && !names.contains(players[i])){
                onlinePlayers.add(players[i]);
                //onlinePlayers[count]=players[i];
               // count++;
            }
        }
        listOnlinePlayers.setListData(onlinePlayers.toArray(new String[onlinePlayers.size()]));
        
    }
    /**
     * Sets this frame 
     * @param frame the JFrame object 
     */
    public void setFrame(JFrame frame) {
        this.onlineMenuFrame = frame;
        onlineMenuFrame.setTitle(controller.getUname() + ", Choose Your Game!");
        onlineMenuFrame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                btnCloseActionPerformed(windowEvent);
            }
            
        });
    }
    /**
     * This updates the list of requests on the view
     * @param player the username of the player that sent an invitation
     * @param size the size of the board
     */
    public void updateRequests(String player, String size){
        ArrayList<String> req=new ArrayList<>(Arrays.asList(this.getRequests()));
        String newRequest = player + " " + size;
        if(!req.contains(newRequest))
            req.add(newRequest);
        listReq.setListData(req.toArray(new String[req.size()]));
        this.controller.getLoggedInList();
    }
    /**
     * Gets the list of requests and converts it to an array of Strings
     * @return the array of requests
     */
    private String[] getRequests(){
        ListModel modelList = listReq.getModel();
        String[] req=new String[modelList.getSize()];
        for(int i=0; i < modelList.getSize(); i++){
            req[i] = (String)modelList.getElementAt(i);  
        }
        return req;
    }
    /**
     * Removes a player from the list of requests
     * @param player the player getting removed
     */
    private void removePlayer(String player){
        ArrayList<String> requests = new ArrayList<>(Arrays.asList(this.getRequests()));
        
        requests.remove(player);
        
        listReq.setListData(requests.toArray(new String[requests.size()]));
        
    }
    
    /**
     * This sets this view to invisible and goes back to the main menu
     * @param evt 
     */
    private void btnCloseActionPerformed(java.awt.event.WindowEvent evt){
        onlineMenuFrame.setVisible(false);
        controller.back();
    }
}
