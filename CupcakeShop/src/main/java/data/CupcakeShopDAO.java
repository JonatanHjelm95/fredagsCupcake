package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonab
 */
public class CupcakeShopDAO {

    /*
    Methods:
    
    getUser(String username) x 
    getUsers() x
    getBot()
    getBottoms()
    getTop()
    getToppings()
    -----------
    addNewUser(User user)
//    addCupcake(Cupcake cupcake)
//    addBottom(Bottom bottom)
//    addTopping(Topping topping)
    
     */
    private final String GET_USER_BY_USERNAME = "SELECT username, password, balance FROM CupcakeShop.user WHERE username = ?";
    private final String GET_ALL_USERS = "SELECT username, password, balance FROM CupcakeShop.user";
    
    private DBConnector db;

    public CupcakeShopDAO() {
        try {
            this.db = new DBConnector();
        } catch (Exception ex) {
            Logger.getLogger(CupcakeShopDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Couldn't connect to DB");
        }
    }

    /**
     * Returns a user with the name or throws exception.
     *
     * @param username
     * @return User
     * @throws Exception
     */
    public User getUser(String username) throws Exception {
        User user = null;
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(GET_USER_BY_USERNAME);
            pStatement.setString(1, username);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("username");
                String password = rs.getString("password");
                int balance = rs.getInt("balance");
                user = new User(username, password, balance);
            } else {
                throw new Exception("That user doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
/**
 *  Gets all users from database.
 * @return ArrayList of users
 */
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList();
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(GET_ALL_USERS);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                int balance = rs.getInt("balance");
                users.add(new User(username, password, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addNewUser(User user) {

    }
}
