/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonab
 */
public class CupcakeShopDAO {

    DBConnector db;
    Connection con = db.getConnection();
    public CupcakeShopDAO() throws Exception {
        
        try {
            this.db = new DBConnector();
        } catch (Exception ex) {
            Logger.getLogger(CupcakeShopDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Couldn't connect to DB");
        }
    }

    public void createNewUser(User user) {
        
    }
}
