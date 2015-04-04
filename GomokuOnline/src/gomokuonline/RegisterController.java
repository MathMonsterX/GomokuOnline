/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuonline;

/**
 *
 * @author clarissapendleton
 */
public class RegisterController {
    RegisterView view;
    AdminModel model;
    
    public RegisterController(){
        
    }
    
    public void setModel(AdminModel model){
        this.model = model;
    }
    
    public void setView(RegisterView view){
        this.view = view;
        
    }
    public void createAccount(String username, String password){
        
    }
    
}
