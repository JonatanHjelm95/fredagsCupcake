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
        CupcakeShopDAO dao = new CupcakeShopDAO();
        User user = null, user2 = null;
        try {
            user = dao.getUser("davsdu");
            user2 = dao.getUser("hans123");
        } catch (Exception ex) {
            System.out.println("User didnt exist");
        }
        ShoppingBasket sb = new ShoppingBasket();
        try {
            sb.addItem(new LineItem(new CupCake(dao.getTopping("Blue Cheese"),dao.getBottom("Nutmeg")), 1));
            sb.addItem(new LineItem(new CupCake(dao.getTopping("Blue Cheese"),dao.getBottom("Almond")), 2));
            sb.addItem(new LineItem(new CupCake(dao.getTopping("Crispy"),dao.getBottom("Vanilla")), 4));
        } catch (Exception ex) {
            Logger.getLogger(DAOtest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao.addOrder(sb, user);
    }

}
