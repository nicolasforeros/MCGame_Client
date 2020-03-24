/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC;

import co.edu.autonoma.MC.ui.LoginWindow;

/**
 *
 * @author nikof
 */
public class Main {
    
    public static void main(String[] args)
    {
        Cliente cliente = new Cliente();
        
        LoginWindow login = new LoginWindow(cliente);
        
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
    
}
