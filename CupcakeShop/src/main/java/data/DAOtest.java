/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fen
 */
public class DAOtest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CupcakeShopDAO dao = new CupcakeShopDAO();
        } catch (Exception ex) {
            Logger.getLogger(DAOtest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
