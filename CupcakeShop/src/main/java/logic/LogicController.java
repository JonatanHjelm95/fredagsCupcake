/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jonab
 */
public class LogicController {

    public String generateMenu(HttpServletRequest request) {
        String menuHTML = "";
        boolean isLoggedIn = (boolean) request.getSession(false).getAttribute("isLoggedIn");
        User user = (User) request.getSession(false).getAttribute("user");
        if (isLoggedIn) {

            menuHTML= "<a id=\"login\" href=\"?origin=logout\">logout</a>"
                    + "<h4 id=\"user\" > Logged in as: "+user.getUsername()+"</h4>\n";

        } else {
            menuHTML = "<a id=\"login\" href=\"?origin=signup\">sign up</a>\n"
                    + "<a id=\"login\" href=\"?origin=login\">Login</a>";

        }
        return menuHTML;
    }
}
