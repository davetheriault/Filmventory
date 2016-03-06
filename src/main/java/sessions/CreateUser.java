/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessions.model.PasswordHandler;
import sessions.model.User;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "CreateUser", urlPatterns = {"/CreateUser"})
public class CreateUser extends HttpServlet {

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
            out.println("<title>Servlet CreateUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confpass");

        if (password.equals(confirm)) {

            PasswordHandler handler = new PasswordHandler("users.txt");
            List<User> userlist = handler.getAllPasswords();
            Boolean exist = false;

            for (User i : userlist) {
                if (username.equals(i.getUsername())) {
                    exist = true;
                    break;
                }
            }
            if (exist.equals(true)) {
                User newUser = new User(username, password);

                handler.addUser(newUser);

                response.sendRedirect("login.jsp");
            } else {
                String message = "Username Not Available";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }

        } else {

            String message = "Password and Confirm Password Do Not Match.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/register.jsp").forward(request, response);

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
