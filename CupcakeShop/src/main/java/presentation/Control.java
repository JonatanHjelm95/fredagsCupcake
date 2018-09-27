/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.CupcakeShopDAO;
import data.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

            if (origin != null) {
                switch (origin) {
                    case "login":
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        break;
                    case "logout":
                        request.getSession(false).invalidate();
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        break;
                    case "check password":
                        checkPassword(request, response);
                        break;
                    case "signup":
                        request.getRequestDispatcher("signUp.jsp").forward(request, response);
                        break;
                    case "create user":
                        createUser(request, response);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;
                    case "index":
                        generateHtmlMenu(request);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;
                    case "products":
                        request.setAttribute("bottoms", dao.getBottoms());
                        request.setAttribute("toppings", dao.getToppings());
                        request.getRequestDispatcher("products.jsp").forward(request, response);
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

    private void generateHtmlMenu(HttpServletRequest request) {
        LogicController lc = new LogicController();
        String html = lc.generateMenu(request);
        request.setAttribute("html", html);
    }

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
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            boolean isLoggedIn = true;
            session.setAttribute("isLoggedIn", isLoggedIn);
            response.sendRedirect("Control?origin=index");
        } else {
            request.setAttribute("error", "wrong password");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    private void createUser(HttpServletRequest request, ServletResponse response) throws NumberFormatException, ServletException, IOException {
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
