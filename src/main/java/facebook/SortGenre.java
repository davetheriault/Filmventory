/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "SortGenre", urlPatterns = {"/SortGenre"})
public class SortGenre extends HttpServlet {

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
            out.println("<title>Servlet SortGenre</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SortGenre at " + request.getContextPath() + "</h1>");
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

        response.setContentType("text/html");
        
        FileWriter sortlog = new FileWriter("sort.txt", true);

        String genre = (String) request.getParameter("genre");
        String sort = (String) request.getParameter("sort");

        sortlog.write("\nsort value: " + sort);
        sortlog.write("\ngenre value: " + genre);
        sortlog.flush();

        JDBC db = new JDBC();

        String fb_id = (String) request.getSession().getAttribute("id");

        sortlog.write("\nFacebook id: " + fb_id);
        sortlog.flush();

        List<Movie> movies = db.getInventory(fb_id, sort);

        for (Movie mov : movies) {
            boolean genreCheck = false;
            for (String gnr : mov.getGenre()) {
                sortlog.write("\n" + mov.getTitle() + " " + gnr);
                sortlog.flush();
                if (gnr.equals(genre)) {
                    genreCheck = true;
                }
            }
            if (genreCheck == true || genre.equals("All")) {
                sortlog.write("\nGenreCheck Passed");
                sortlog.flush();
                String title = mov.getTitle();
                String year = (String) mov.getYear();
                String outp = "<div class=\"w3-card w3-margin\">";
                outp += "<ul class=\"w3-ul\">";
                outp += "<li><strong><a href=\"/MovieDetails?title=" + title + "&year=" + year + "\" >" + title + "</a></strong> &#40;" + year + "&#40; </li>";
                outp += "</ul>";
                outp += "</div>";
                sortlog.write("\n" +outp);
                sortlog.flush();
                response.getWriter().write(outp);
            }
        }

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
