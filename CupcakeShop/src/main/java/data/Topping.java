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
public class Topping {
    private String name = "";
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Topping(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
