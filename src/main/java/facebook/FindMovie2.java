/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import static java.net.URLEncoder.encode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "FindMovie2", urlPatterns = {"/FindMovie2"})
public class FindMovie2 extends HttpServlet {

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

        JDBC db = new JDBC();

        List<String> messages = new ArrayList();

        if (request.getSession().getAttribute("facebook") == null || request.getSession().getAttribute("facebook") == "") {
            response.sendRedirect("filmventory.jsp");
        }
        String message = "";
        for (String mess : messages) {
            message += " " + mess + " ";
        }
        request.setAttribute("message", message);

        String title = request.getParameter("title");
        String year = request.getParameter("y");

        String urltitle = encode(title, "UTF-8");
        String urlyear = encode(year, "UTF-8");

        URL posterU = null;
        String posterURL = null;

        URL url = new URL("http://www.omdbapi.com/?t=" + urltitle + "&y=" + urlyear);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(url, Map.class);

        String results = "<ul class=\"w3-ul\">";
        for (String key : map.keySet()) {
            URL url2 = new URL("https://api.themoviedb.org/3/find/" + map.get("imdbID") + "?external_source=imdb_id&api_key=485892eacda398b32d06aa04114b3974");

            ObjectMapper postmap = new ObjectMapper();
            Map<String, Object> map2 = postmap.readValue(url2, Map.class);
            
            String movres = map2.get("movie_results").toString();
            
            String purl = movres.substring(movres.lastIndexOf("poster_path=") + 12, movres.indexOf(", popularity"));

            posterU = new URL("http://image.tmdb.org/t/p/w500" + purl);
            posterURL = posterU.toString();

            request.setAttribute("poster", posterURL);

            if (key.equals("Poster")) {

            } else if (key.equals("Title")) {
                results += "<li>" + key + ": " + map.get(key) + " ";

                boolean own = db.checkMovie2User((String) request.getSession().getAttribute("id"), (String) map.get(key), (String) map.get("Year"));
                if (own == false) {

                    results += "<form id=\"addFilm\" method=\"post\" action=\"AddMovie\">"
                            + "<input type=\"hidden\" name=\"tit\" value=\"" + map.get("Title") + "\" />"
                            + "<input type=\"hidden\" name=\"yea\" value=\"" + map.get("Year") + "\" />"
                            + "<input type=\"hidden\" name=\"rat\" value=\"" + map.get("Rated") + "\" />"
                            + "<input type=\"hidden\" name=\"rel\" value=\"" + map.get("Released") + "\" />"
                            + "<input type=\"hidden\" name=\"run\" value=\"" + map.get("Runtime") + "\" />"
                            + "<input type=\"hidden\" name=\"gen\" value=\"" + map.get("Genre") + "\" />"
                            + "<input type=\"hidden\" name=\"dir\" value=\"" + map.get("Director") + "\" />"
                            + "<input type=\"hidden\" name=\"wri\" value=\"" + map.get("Writer") + "\" />"
                            + "<input type=\"hidden\" name=\"act\" value=\"" + map.get("Actors") + "\" />"
                            + "<input type=\"hidden\" name=\"plo\" value=\"" + map.get("Plot") + "\" />"
                            + "<input type=\"hidden\" name=\"lan\" value=\"" + map.get("Language") + "\" />"
                            + "<input type=\"hidden\" name=\"cou\" value=\"" + map.get("Country") + "\" />"
                            + "<input type=\"hidden\" name=\"met\" value=\"" + map.get("Metascore") + "\" />"
                            + "<input type=\"hidden\" name=\"imd\" value=\"" + map.get("imdbID") + "\" />"
                            + "<input type=\"hidden\" name=\"img\" value=\"" + posterURL + "\" />"
                            + "<input type=\"submit\" name=\"sub\" value=\"Add to Collection\" form=\"addFilm\"/>"
                            + "</form>";
                }
                results += "</li>";
            } else {
                results += "<li>" + key + ": " + map.get(key) + "</li>";
            }
        }
        
        results += "</ul>";
        request.setAttribute("results", results);
        
        request.setAttribute("mtitle", title);

        request.getRequestDispatcher("fvfind2.jsp").forward(request, response);
    
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
