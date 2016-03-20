/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omdb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import static java.net.URLEncoder.encode;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "MovieSearch2", urlPatterns = {"/MovieSearch2"})
public class MovieSearch2 extends HttpServlet {

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
            out.println("<title>Servlet MovieSearch</title>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovieSearch at " + request.getContextPath() + "</h1>");

            String title = request.getParameter("title");

            String urltitle = encode(title, "UTF-8");

            URL url = new URL("http://www.omdbapi.com/?s=" + urltitle);

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(url, Map.class);

            List list = (List) map.get("Search");

            for (Object item : list) {
                Map<String, Object> innerMap = (Map<String, Object>) item;
                out.println("<br>");
                for (String key : innerMap.keySet()) {
                    if (key.equals("Poster")) {
                        String keystr = (String) innerMap.get(key);
                        String id = keystr.substring(44, 50);
                        out.println("<a href='#' id='"+id+"' >View Poster</a> <br>");
                        out.println("<script>$('#"+id+"').click(function(){ window.location.assign(\""+innerMap.get(key)+"\"); });</script>");
                    } else {
                        if (key.equals("Title")) {
                            out.println(key + ": <a href='MovieSearch?title=" + encode((String) innerMap.get(key), "UTF-8") + "'>" + innerMap.get(key) + "</a><br>");
                        }
                        if (key.equals("Year")) {
                            out.println(key + ": " + innerMap.get(key) + "<br>");
                        }
                    }

                }
            }
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


// https://api.themoviedb.org/3/movie/550?api_key=485892eacda398b32d06aa04114b3974
