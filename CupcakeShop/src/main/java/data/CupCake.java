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

    /**
     *Constructor for a cupcake
     * @param top
     * @param bottom
     */
    public CupCake(Topping top, Bottom bottom) {
        this.name = top.getName() + bottom.getName();
        this.top = top;
        this.bottom = bottom;
        this.fullPrice = top.getPrice()+ bottom.getPrice();
    }

    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return top
     */
    public Topping getTop() {
        return top;
    }

    /**
     *
     * @return bottom
     */
    public Bottom getBottom() {
        return bottom;
    }

    /**
     *
     * @return full price of cupcake
     */
    public int getFullPrice() {
        return fullPrice;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
