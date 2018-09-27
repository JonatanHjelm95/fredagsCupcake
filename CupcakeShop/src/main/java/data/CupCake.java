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
public class CupCake {
   private String name;
   private Topping top;
   private Bottom bottom;
   private int fullPrice;

    public CupCake(String name, Topping top, Bottom bottom, int fullPrice) {
        this.name = top.getName() + bottom.getName();
        this.top = top;
        this.bottom = bottom;
        this.fullPrice = top.getPrice() + bottom.getPrice();
    }

    public String getName() {
        return name;
    }

    public Topping getTop() {
        return top;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public int getFullPrice() {
        return fullPrice;
    }
}
