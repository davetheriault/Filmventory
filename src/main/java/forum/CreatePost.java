/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum;

import forum.model.Post;
import forum.model.PostFileHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "CreatePost", urlPatterns = {"/CreatePost"})
public class CreatePost extends HttpServlet {

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
            out.println("<title>Servlet CreatePost</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreatePost at " + request.getContextPath() + "</h1>");
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

        String title = request.getParameter("title");
        String post = request.getParameter("post");
        String escTitle = StringEscapeUtils.escapeHtml(title);
        String escPost = StringEscapeUtils.escapeHtml(post);

        HttpSession session = request.getSession(true);
        String user = (String) session.getAttribute("username");

        long timeStamp = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH) + 1;
        String monthString;
        switch (mMonth) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int ampm = calendar.get(Calendar.AM_PM);
        String am_pm;
        if (ampm == 1) {
            am_pm = "PM";
        } else {
            am_pm = "AM";
        }

        PostFileHandler handler = new PostFileHandler("posts2.txt");

        Post newPost = new Post(escTitle, escPost, mYear, monthString, mDay, hour, minute, am_pm, user);

        handler.addPost(newPost);

        response.sendRedirect("ShowPosts");
    /*
        request.setAttribute("title", escTitle);
        request.setAttribute("post", escPost);
        request.setAttribute("user", user);
        request.setAttribute("year", mYear);
        request.setAttribute("month", mMonth);
        request.setAttribute("day", mDay);
        request.setAttribute("hour", hour);
        request.setAttribute("min", minute);
        request.setAttribute("ampm", ampm);

        request.getRequestDispatcher("viewposts.jsp").forward(request, response);
    */
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
