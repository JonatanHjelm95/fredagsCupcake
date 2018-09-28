/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.Bottom;
import data.Topping;
import data.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jonab
 */
public class LogicController {

    public String generateMenu(HttpServletRequest request) {
        String menuHTML = "";
        User user = null;
        if (request.getSession(false) != null) {
            try {
                user = (User) request.getSession(false).getAttribute("user");
                menuHTML = "<a id=\"login\" href=\"?origin=logout\">logout</a>"
                        + " <a id=\"cartIcon\" href =\"?origin=shopping cart\"><i style =\"font-size:21px\" class=\"fa\">&#xf07a;</i></a>"
                        + "<h4 id=\"user\" > Logged in as: " + user.getUsername()
                        + " Balance: " + user.getBalance() + "</h4>\n"  ;
                return menuHTML;
            } catch (NullPointerException ne) {
                ne.printStackTrace();
            }
        }
        menuHTML = "<a id=\"login\" href=\"?origin=signup\">sign up</a>\n"
                + "<a id=\"login\" href=\"?origin=login\">Login</a>";
        return menuHTML;
    }

    public String generateBottom(ArrayList<Bottom> bottoms) {
        String bottomsDropdown = "<select name='bottom' form='extras'>";
        for (int i = 0; i < bottoms.size(); i++) {
            bottomsDropdown += "<option value='" + bottoms.get(i).getName() + "'>" + bottoms.get(i).getName() + ", " + bottoms.get(i).getPrice() + "</option>";
        }
        bottomsDropdown += "</select>";
        System.out.println(bottomsDropdown);
        return bottomsDropdown;
    }

    public String generateTopping(ArrayList<Topping> toppings) {
        String toppingsDropdown = "<select name='topping' form='extras'>";
        for (int i = 0; i < toppings.size(); i++) {
            toppingsDropdown += "<option value='" + toppings.get(i).getName() + "'>" + toppings.get(i).getName() + ", " + toppings.get(i).getPrice() + "</option>";
        }
        toppingsDropdown += "</select>";
        return toppingsDropdown;
    }

}
