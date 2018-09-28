/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author porse
 */
public class Order {

    private int orderID;
    private String invoice;
    private int totalprice;
    private String orderDate;
    private User user;

    public Order(int orderID, String invoice, int totalprice, String orderDate, User user) {
        this.orderID = orderID;
        this.invoice = invoice;
        this.totalprice = totalprice;
        this.orderDate = orderDate;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", invoice=" + invoice + ", totalprice=" + totalprice + ", orderDate=" + orderDate + ", user=" + user + '}';
    }
    
    public int getOrderID() {
        return orderID;
    }

    public String getInvoice() {
        return invoice;
    }

    public int getTotalprice() {
        return totalprice;
    }


    public String getOrderDate() {
        return orderDate;
    }

    public User getUser() {
        return user;
    }

}
