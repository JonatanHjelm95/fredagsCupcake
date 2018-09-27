/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

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
        if (isLoggedIn) {

            menuHTML = "<div class=\"topnav\">\n"
                    + "            <a id=\"login\" href=\"?origin=signup\">logout</a>\n"
                    + "            <h1 id=\"header\" >Cupcake Shop</h1>\n"
                    + "\n"
                    + "            <a id=\"home\" class=\"active\" href=\"?origin=index\">Home</a>\n"
                    + "            <a id=\"products\" href=\"?origin=products\">Products</a>\n"
                    + "        </div>";

        } else {
            menuHTML = "<div class=\"topnav\">\n"
                    + "            <a id=\"login\" href=\"?origin=signup\">sign up</a>\n"
                    + "            <a id=\"login\" href=\"?origin=login\">Login</a>\n"
                    + "            <h1 id=\"header\" >Cupcake Shop</h1>\n"
                    + "\n"
                    + "            <a id=\"home\" class=\"active\" href=\"?origin=index\">Home</a>\n"
                    + "            <a id=\"products\" href=\"?origin=products\">Products</a>\n"
                    + "        </div>";

        }
        return menuHTML;
    }
}
