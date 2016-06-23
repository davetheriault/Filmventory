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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>Movie Search</title>\n"
                    + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>"
                    + "        <link rel=\"stylesheet\" href=\"css/w3.css\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Lobster\">\n"
                    + "        <link href='https://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'>\n"
                    + "        <link href='https://fonts.googleapis.com/css?family=Play' rel='stylesheet' type='text/css'>\n"
                    + "        <link rel=\"stylesheet\" href=\"http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css\">\n"
                    + "\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <header class=\"w3-topbar w3-red\">\n"
                    + "            <nav class=\"w3-roboto w3-xlarge\">\n"
                    + "                <ul class=\"w3-navbar\">\n"
                    + "                    <li class=\"w3-yellow w3-text-red\"><a class=\"w3-text-red\" href=\"filmventory.jsp\">Filmventory</a></li>\n"
                    + "                    <li><a href=\"\">Options</a></li>\n"
                    + "\n"
                    + "                </ul>\n"
                    + "            </nav>\n"
                    + "            <div class=\"w3-padding w3-red\" style=\"position: fixed; bottom: 0; right: 0;\"><a href=\"index.html\">Back to Java</a></div>\n"
                    + "        </header>\n"
                    + "");
            out.println("\n"
                    + "<div class=\"w3-sidenav w3-collapse w3-red w3-card w3-animate-left\">\n"
                    + "    <a href=\"#\" onclick=\"w3_close()\" class=\"w3-closenav w3-hide-large\">Close X</a> \n"
                    + "    <ul class=\"w3-ul\">\n"
                    + "        <li><a href=\"mymovies.jsp\">My Movies</a></li>\n"
                    + "        <li><a href=\"addmovies.jsp\">Add Movies</a></li>\n"
                    + "        <li><a href=\"/LogOut\">Log Out</a></li>\n"
                    + "    </ul>\n"
                    + "</div>\n"
                    + "\n"
                    + "<div class=\"w3-text-red w3-opennav w3-hide-large w3-xxlarge w3-padding-left\" onclick=\"w3_open()\"><i class=\"fa fa-bars\"></i></div>\n"
                    + "\n"
                    + "<script>\n"
                    + "    function w3_open() {\n"
                    + "        document.getElementsByClassName(\"w3-sidenav\")[0].style.display = \"block\";\n"
                    + "    }\n"
                    + "    function w3_close() {\n"
                    + "        document.getElementsByClassName(\"w3-sidenav\")[0].style.display = \"none\";\n"
                    + "    }\n"
                    + "</script>");
            out.println("<main class=\"w3-main w3-roboto\" style=\"margin-left:200px\">\n"
                    + "\n"
                    + "    <div class=\"w3-card w3-margin\">\n"
                    + "        <h3 class=\"w3-red w3-padding-left w3-margin-0 w3-roboto\">\n"
                    + "            Results for &quot;" + request.getParameter("title") + "&quot;</h3>\n"
                    + "        <div class=\"w3-container\">");

            String title = request.getParameter("title");

            String urltitle = encode(title, "UTF-8");

            URL url = new URL("http://www.omdbapi.com/?s=" + urltitle);

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(url, Map.class);

            List list = (List) map.get("Search");

            String results = "";

            for (Object item : list) {
                Map<String, Object> innerMap = (Map<String, Object>) item;
                results += "<br>";
                results += "<div class=\"w3-card\"><ul class=\"w3-ul\">";
                for (String key : innerMap.keySet()) {

                    if (key.equals("Title")) {
                        results += "<li>" + key + ": <a href='FindMovie2?title=" + encode((String) innerMap.get(key), "UTF-8") + "'>" + innerMap.get(key) + "</a></li>";
                    }
                    if (key.equals("Year")) {
                        results += "<li>" + key + ": " + innerMap.get(key) + "</li>";
                    }
                }
                results += "</ul></div>";

            }
            out.println("</div></div></main>");
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
request.setAttribute("title", request.getAttribute("title"));

        String title = request.getParameter("title");

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
        
        request.getRequestDispatcher("fvfind1.jsp").forward(request, response);
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

        request.setAttribute("title", request.getAttribute("title"));

        String title = request.getParameter("title");

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
                    results += "<li>" + key + ": <a href='FindMovie2?title=" + encode((String) innerMap.get(key), "UTF-8") + "'>" + innerMap.get(key) + "</a></li>";
                }
                if (key.equals("Year")) {
                    results += "<li>" + key + ": " + innerMap.get(key) + "</li>";
                }
            }
            results += "</ul></div>";

        }
        
        request.setAttribute("results", results);
        
        request.getRequestDispatcher("fvfind1.jsp").forward(request, response);

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
