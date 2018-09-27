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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                    case "signup":
                        request.getRequestDispatcher("signUp.jsp").forward(request, response);
                        break;
                    case "create user":
                        createUser(request);
                        request.getRequestDispatcher("signUp.jsp").forward(request, response);
                        break;
                    case "index":
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;
                    case "products":
                        request.getRequestDispatcher("products.jsp").forward(request, response);
                        break;
                    default:
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;

                }
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    private void createUser(HttpServletRequest request) throws NumberFormatException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String balance_str = request.getParameter("balance");
        int balance = Integer.parseInt(balance_str);
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
