package facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "CallBack", urlPatterns = {"/CallBack"})
public class CallBack extends HttpServlet {

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
            out.println("<title>Servlet CallBack</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CallBack at " + request.getContextPath() + "</h1>");
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

        //get the Facebook4j object that we stored on the Session:
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");

        //get the oauthCode parameter that was passed by Facebook as a GET parameter named, "code"
        String oauthCode = request.getParameter("code");

        //give this oauthCode to the Facebook object, by calling the getOAuthAccessToken method, 
        //which will set up the Facebook object for future queries
        try {
            facebook.getOAuthAccessToken(oauthCode);
        } catch (FacebookException e) {
            request.getSession().setAttribute("exception", e);
        }

        JDBC db = new JDBC();
        String fbid = null;
        String fname = null;
        String lname = null;
       // String email = null;
        String name = null;
       // String exist = null;

        try {

            name = facebook.getMe().getName();
            String[] names = name.split(" ", 2);

            fbid = facebook.getMe().getId();
            fname = names[0];
            lname = names[1];
          //  email = facebook.getMe().getEmail();

            if (db.checkUser(fbid) == true) {
                User existU = db.getUser(fbid);
                request.getSession().setAttribute("user", existU);
                request.getSession().setAttribute("id", existU.getFbId());
                request.getSession().setAttribute("fname", existU.getFirstName());
                request.getSession().setAttribute("exist", "UserExists");
          //      exist = "exists";
            } else {
                db.addUser(fbid, fname, lname);
                if (db.checkUser(fbid) == true) {
                    User newU = db.getUser(fbid);
                    request.getSession().setAttribute("user", newU);
                    request.getSession().setAttribute("id", newU.getFbId());
                    request.getSession().setAttribute("fname", newU.getFirstName());
                    request.getSession().setAttribute("exist", "Not Existed");
           //         exist = "not exists";
                }
            }

        } catch (FacebookException ex) {
            Logger.getLogger(CallBack.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("exception", ex);
        }

        response.sendRedirect("fvhome.jsp");
        /*
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CallBack</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CallBack at " + request.getContextPath() + "</h1>");
            out.println(oauthCode);
            out.println("<br>");
            out.println("<br>");
            out.println(fbid);
            out.println("<br>");
            out.println("<br>");
            out.println(fname);
            out.println("<br>");
            out.println("<br>");
            out.println(name);
            out.println("<br>");
            out.println("<br>");
            out.println(exist);
            out.println("<br>");
            out.println("<br>");
            out.println(request.getSession().getAttribute("fname"));
            out.println("</body>");
            out.println("</html>");
        }*/

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
