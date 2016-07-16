/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import static java.net.URLEncoder.encode;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "FindMovie", urlPatterns = {"/FindMovie"})
public class FindMovie extends HttpServlet {

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
        
        String title = request.getParameter("search");

        String urltitle = encode(title, "UTF-8");

        URL url = new URL("http://www.omdbapi.com/?s=" + urltitle);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.readValue(url, Map.class);

        List list = (List) map.get("Search");

        request.setAttribute("list", list);

        String results = "";

        for (Object item : list) {
            Map<String, Object> innerMap = (Map<String, Object>) item;
            results += "<br>";
            results += "<div class=\"w3-card\"><ul class=\"w3-ul\">";
            for (String key : innerMap.keySet()) {

                if (key.equals("Title")) {
                    results += "<li>" + key + ": <a href='FindMovie2?title=" + encode((String) innerMap.get(key), "UTF-8") + "&y=" + encode((String) innerMap.get("Year"), "UTF-8") + "'>" + innerMap.get(key) + "</a></li>";
                }
                if (key.equals("Year")) {
                    results += "<li>" + key + ": " + innerMap.get(key) + "</li>";
                }
            }
            results += "</ul></div>";

        }

        request.setAttribute("results", results);
        
        request.setAttribute("query", title);

        request.getRequestDispatcher("fvfind1.jsp").forward(request, response);
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
