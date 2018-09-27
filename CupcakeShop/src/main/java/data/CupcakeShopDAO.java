package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonab
 */
public class CupcakeShopDAO {
    /*
    Methods:
    
    getUser(String username)
    getUsers()
    getCupcake(String name)
    getAllCupcakes()
    -----------
    addNewUser(User user)
//    addCupcake(Cupcake cupcake)
//    addBottom(Bottom bottom)
//    addTop(Top top)
    
    */
    private final String GET_USER_BY_USERNAME = "SELECT username, password, balance FROM recipesDB.User WHERE username = ?";

    private DBConnector db;
    private Connection con;
    
    public CupcakeShopDAO() throws Exception {        
        try {
            this.db = new DBConnector();
            con = db.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(CupcakeShopDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Couldn't connect to DB");
        }
    }
    
    /** Returns a User object if a matching username is found.
     *  Returns null if username gives no match.
     * @param username
     * @return 
     */
    public User getUser(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        
        
        
        return null;
    }
    

    public void addNewUser(User user) {
        
        
    }
}
