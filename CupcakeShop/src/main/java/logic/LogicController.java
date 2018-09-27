/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

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
        boolean isLoggedIn = false;
        User user = null;
        if (request.getSession(false) != null) {
            try {
                isLoggedIn = (boolean) request.getSession(false).getAttribute("isLoggedIn");
                user = (User) request.getSession(false).getAttribute("user");
            } catch(NullPointerException ne) {
                ne.printStackTrace();
            }
            if (isLoggedIn) {
                menuHTML = "<a id=\"login\" href=\"?origin=logout\">logout</a>"
                        + "<h4 id=\"user\" > Logged in as: " + user.getUsername()
                        + " Balance: " + user.getBalance() + "</h4>\n";
                return menuHTML;
            }
        }
        menuHTML = "<a id=\"login\" href=\"?origin=signup\">sign up</a>\n"
                + "<a id=\"login\" href=\"?origin=login\">Login</a>";
        return menuHTML;
    }
    

}
