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
    getBottom() x
    getBottoms() x
    getTopoing() x
    getToppings() x
    getAllOrders()
    -----------
    addNewUser(User user) x
    addToCupcakeDetails(LineItem item)
    addBottom(Bottom bottom) x
    addTopping(Topping topping) x
    addOrder(ShoppingBasket basket, User user)
    
     */
    private final String GET_USER_BY_USERNAME = "SELECT username, password, balance FROM CupcakeShop.user WHERE username = ?";
    private final String GET_ALL_USERS = "SELECT username, password, balance FROM CupcakeShop.user;";
    private final String GET_BOTTOM = "SELECT bottomName, price FROM CupcakeShop.bottom WHERE bottomName = ?";
    private final String GET_BOTTOMS = "SELECT bottomName, price FROM CupcakeShop.bottom;";
    private final String GET_TOPPING = "SELECT toppingName, price FROM CupcakeShop.topping Where toppingName = ?";
    private final String GET_TOPPINGS = "SELECT toppingName, price FROM CupcakeShop.topping;";
    private final String GET_NEXT_ORDERID = "SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES\n" + "WHERE table_name = 'order'";
    private final String GET_ALL_ORDERS = "SELECT orderID, invoice, price, orderDate, user FROM `order`;";

    private final String ADD_NEW_USER = "INSERT INTO user(username,password,balance) VALUES (?,?,?)";
    private final String ADD_TO_CUPCAKEDETAILS = "INSERT INTO cupcakeDetails(orderID, qty, topping, bottom) VALUES(?,?,?,?)";
    private final String ADD_BOTTOM = "INSERT INTO bottom(bottomName, price) VALUES (?,?)";
    private final String ADD_TOPPING = "INSERT INTO topping(toppingName, price) VALUES (?,?)";
    private final String ADD_ORDER = "INSERT INTO `order`(price, user) VALUES (?,?)";

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
     * Gets all users from database.
     *
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

    /**
     * Returns a bottom with the name or throws exception.
     *
     * @param bottomName
     * @return bottom
     * @throws Exception
     */
    public Bottom getBottom(String bottomName) throws Exception {
        Bottom bottom = null;
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(GET_BOTTOM);
            pStatement.setString(1, bottomName);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("bottomName");
                int price = rs.getInt("price");
                bottom = new Bottom(name, price);
            } else {
                throw new Exception("That bottom doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bottom;
    }

    /**
     * Gets all bottom names and prices from database.
     *
     * @return ArrayList of bottoms
     */
    public ArrayList<Bottom> getBottoms() {
        ArrayList<Bottom> bottoms = new ArrayList();
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(GET_BOTTOMS);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("bottomName");
                int price = rs.getInt("price");
                bottoms.add(new Bottom(name, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bottoms;
    }

    /**
     * Returns a topping with the name or throws exception.
     *
     * @param toppingName
     * @return topping
     * @throws Exception
     */
    public Topping getTopping(String toppingName) throws Exception {
        Topping topping = null;
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(GET_TOPPING);
            pStatement.setString(1, toppingName);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("toppingName");
                int price = rs.getInt("price");
                topping = new Topping(name, price);
            } else {
                throw new Exception("That topping doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topping;
    }

    /**
     * Gets all topping names and prices from database.
     *
     * @return ArrayList of toppings
     */
    public ArrayList<Topping> getToppings() {
        ArrayList<Topping> toppings = new ArrayList();
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(GET_TOPPINGS);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("toppingName");
                int price = rs.getInt("price");
                toppings.add(new Topping(name, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toppings;
    }

    public int getNextOrderID() {
        int orderID;

        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(GET_NEXT_ORDERID);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                orderID = rs.getInt("auto_increment");
                return orderID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList();
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(GET_ALL_ORDERS);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String invoice = rs.getString("invoice");
                int totalprice = rs.getInt("price");
              //String status = rs.getString("status");
                String orderDate = rs.getString("orderDate");
                String user = rs.getString("user");
                orders.add(new Order(orderID, invoice, totalprice, orderDate, getUser(user)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

public void addNewUser(User user) {
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(ADD_NEW_USER);
            pStatement.setString(1, user.getUsername());
            pStatement.setString(2, user.getPassword());
            pStatement.setInt(3, user.getBalance());
            pStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private PreparedStatement addCupcakeDetails(LineItem item, int orderID){
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(ADD_TO_CUPCAKEDETAILS);
            pStatement.setInt(1, orderID);
            pStatement.setInt(2, item.getQuantity());
            pStatement.setString(3, item.getCake().getTop().getName());
            pStatement.setString(4, item.getCake().getBottom().getName());
            return pStatement;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void addButtom(Bottom bottom) {
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(ADD_BOTTOM);
            pStatement.setString(1, bottom.getName());
            pStatement.setInt(2, bottom.getPrice());
            pStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addTopping(Topping topping) {
        try {
            Connection con = db.getConnection();
            PreparedStatement pStatement = con.prepareStatement(ADD_TOPPING);
            pStatement.setString(1, topping.getName());
            pStatement.setInt(2, topping.getPrice());
            pStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addOrder(ShoppingBasket basket, User user) {
        try {
            Connection con = db.getConnection();
            int orderID = getNextOrderID();
            System.out.println(orderID);
            con.setAutoCommit(false);
            PreparedStatement pStatement = con.prepareStatement(ADD_ORDER);
            pStatement.setInt(1, basket.getTotalPrice());
            pStatement.setString(2, user.getUsername());
            pStatement.executeUpdate();
            
            ArrayList<PreparedStatement> pStatements = new ArrayList();
            for(int i = 0; i < basket.getBasket().size(); i++) {
                pStatements.add(addCupcakeDetails(basket.getBasket().get(i), orderID));
                pStatements.get(i).executeUpdate();
            }
            con.commit();
            con.setAutoCommit(true);

            
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
