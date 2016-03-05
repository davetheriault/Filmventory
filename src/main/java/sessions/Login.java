/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessions.model.HardCodedPasswordHandler;
import sessions.model.PasswordDataHandler;
import sessions.model.User;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PasswordDataHandler handler = new HardCodedPasswordHandler();

        List<User> pwlist = handler.getAllPasswords();

        Boolean valid = false;
        List<String> names = new ArrayList<>();
        List<String> pwds = new ArrayList<>();
        
        for (User i : pwlist) {
            names.add(i.getUsername());
            pwds.add(i.getPassword());
            if (username.equals(i.getUsername()) && password.equals(i.getPassword())) {
                valid = true;
                break;
            }
        }
        if (valid == true) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect("home.jsp");
        } else {
            String message = "Invalid Login";
            request.setAttribute("message", message);
            request.setAttribute("valid", valid);
            request.setAttribute("users", names);
            request.setAttribute("pws", pwds);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
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
