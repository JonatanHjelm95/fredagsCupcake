/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author jonab
 */
public class ShoppingBasket {

    private ArrayList<LineItem> basket;

    @Override
    public String toString() {
        return "ShoppingBasket{" + "basket=" + basket + ", totalPrice=" + totalPrice + '}';
    }
    private int totalPrice;


    public ShoppingBasket() {
        basket = new ArrayList();
    }
    
    public int getTotalPrice() {
        totalPrice = 0;
        for (int i = 0; i < basket.size(); i++) {
            totalPrice += (basket.get(i).getCake().getFullPrice()*basket.get(i).getQuantity());
        }
        return totalPrice;
    }
    
    public ArrayList<LineItem> getBasket() {
        return basket;
    }
    
    public void addItem(LineItem item){
        basket.add(item);
    }
    
}
