/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.Bottom;
import data.CupCake;
import data.CupcakeShopDAO;
import data.LineItem;
import data.ShoppingBasket;
import data.Topping;
import data.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.LogicController;

/**
 *
 * @author jonab
 */
@WebServlet(name = "Control", urlPatterns = {"/Control"})
public class Control extends HttpServlet {

    private CupcakeShopDAO dao;

    public Control() throws Exception {
        this.dao = new CupcakeShopDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String origin = request.getParameter("origin");
            LogicController lc = new LogicController();
            if (origin != null) {
                switch (origin) {
                    case "login":
                        generateHtmlMenu(request);

                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        break;
                    case "logout":
                        request.getSession(false).invalidate();
                        generateHtmlMenu(request);

                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        break;
                    case "check password":
                        generateHtmlMenu(request);

                        checkPassword(request, response);
                        break;
                    case "signup":
                        generateHtmlMenu(request);

                        request.getRequestDispatcher("signUp.jsp").forward(request, response);
                        break;
                    case "create user":
                        generateHtmlMenu(request);

                        createUser(request, response);
                        break;
                    case "index":
                        generateHtmlMenu(request);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;
                    case "products":
                        executeProducts(request, lc, response);
                        break;
                    case "addtocart":
                        executeAddToCart(request, response, lc);
                        break;
                    case "shoppingbasket":
                        generateHtmlMenu(request);
                        ShoppingBasket sb = (ShoppingBasket) request.getSession(false).getAttribute("shoppingbasket");

                        request.setAttribute("cartContent", lc.generateShoppingCart(sb));

                        request.getRequestDispatcher("shoppingbasket.jsp").forward(request, response);
                        break;

                    case "checkout":
                        sb = (ShoppingBasket) request.getSession(false).getAttribute("shoppingbasket");
                        if (!sb.getBasket().isEmpty()) {
                            User user = (User) request.getSession(false).getAttribute("user");
                            if (user.getBalance() < sb.getTotalPrice()) {
                                request.setAttribute("error", "Not enough money");
                                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                            } else {
                                //withdraw
                                dao.addOrder(sb, user);
                                user.withdraw(sb.getTotalPrice());
                                dao.updateUser(user);
                                sb.getBasket().clear();
                                generateHtmlMenu(request);
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                        }
                        generateHtmlMenu(request);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;
                    default:
                        generateHtmlMenu(request);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;

                }
            } else {
                generateHtmlMenu(request);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
    
    /**
     * @param request servlet request
     * @param response servlet response
     * @param lc LogicController
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    private void executeAddToCart(HttpServletRequest request, HttpServletResponse response, LogicController lc) throws IOException, NumberFormatException, ServletException {
        generateHtmlMenu(request);
        if (request.getSession(false) == null) {
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
        if (request.getSession(false) != null) {
            String bottomName = request.getParameter("bottom");
            String toppingName = request.getParameter("topping");
            int qty = Integer.parseInt(request.getParameter("qty"));
            System.out.println(bottomName + " " + toppingName);
            Bottom bottom = null;
            Topping topping = null;
            //int qty = 1;
            try {
                bottom = dao.getBottom(bottomName);
                topping = dao.getTopping(toppingName);
                
                ShoppingBasket sb = (ShoppingBasket) request.getSession(false).getAttribute("shoppingbasket");
                sb.addItem(new LineItem(new CupCake(topping, bottom), qty));
                request.getSession(false).setAttribute("shoppingbasket", sb);
                request.setAttribute("cartContent", lc.generateShoppingCart(sb));
            } catch (Exception ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("shoppingbasket.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "not logged in");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
    
    /**
     * @param request servlet request
     * @param response servlet response
     * @param lc LogicController
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    private void executeProducts(HttpServletRequest request, LogicController lc, HttpServletResponse response) throws ServletException, IOException {
        generateHtmlMenu(request);
        ArrayList<Bottom> bottoms = dao.getBottoms();
        ArrayList<Topping> toppings = dao.getToppings();
        
        request.setAttribute("bottoms", lc.generateBottom(bottoms));
        request.setAttribute("toppings", lc.generateTopping(toppings));
        
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }

    /**
     * @param request servlet request
     */

    private void generateHtmlMenu(HttpServletRequest request) {
        LogicController lc = new LogicController();
        String html = lc.generateMenu(request);
        request.setAttribute("html", html);
    }
    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void checkPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            user = dao.getUser(username);
        } catch (Exception e) {
            request.setAttribute("error", "wrong username");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            return;
        }
        if (user.getPassword().equals(password)) {
            succesfulLogin(request, user, response);
        } else {
            request.setAttribute("error", "wrong password");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
    
    /**
     * @param request servlet request
     * @param response servlet response
     * @param user User
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    private void succesfulLogin(HttpServletRequest request, User user, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("shoppingbasket", new ShoppingBasket());
        generateHtmlMenu(request);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    
    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws NumberFormatException if NumberFormatException occurs
     */

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String balance_str = request.getParameter("balance");
        int balance = 0;
        try {
            balance = Integer.parseInt(balance_str.trim());
        } catch (NumberFormatException ne) {
            request.setAttribute("error", "invalid number");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
        User user = new User(username, password, balance);
        dao.addNewUser(user);
        succesfulLogin(request, user, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
