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
                    + "            Details for &quot;" + request.getParameter("title") + "&quot;</h3>\n"
                    + "        <div class=\"w3-container w3-padding\">");
            for (String mess : messages) {
                out.println("messages: " + mess);
            }

            String title = request.getParameter("title");

            String urltitle = encode(title, "UTF-8");

            URL url = new URL("http://www.omdbapi.com/?t=" + urltitle);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(url, Map.class);

            out.println("<ul class=\"w3-ul\">");
            for (String key : map.keySet()) {
                if (key.equals("Poster")) {

                } else if (key.equals("Title")) {
                    out.println("<li>" + key + ": " + map.get(key) + " ");

                    boolean own = db.checkMovie2User((String) request.getSession().getAttribute("id"), (String) map.get(key), (String) map.get("Year"));
                    if (own == false) {
                        /*  out.println("<a class=\"w3-button w3-right\" href=\"/AddMovie?add=yes&tit="+encode((String)map.get("Title"), "UTF-8")
                         +"&yea="+(String)map.get("Year")+"&rel="+encode((String)map.get("Released"), "UTF-8")
                         +"&rat="+encode((String)map.get("Rated"), "UTF-8")+"&run="+encode((String)map.get("Runtime"), "UTF-8")
                         +"&gen="+encode((String)map.get("Genre"), "UTF-8")+"&dir="+encode((String)map.get("Director"), "UTF-8")
                         +"&wri="+encode((String)map.get("Writer"), "UTF-8")+"&act="+encode((String)map.get("Actors"), "UTF-8")
                         +"&plo="+encode((String)map.get("Plot"), "UTF-8")+"&lan="+encode((String)map.get("Language"), "UTF-8")
                         +"&cou="+encode((String)map.get("Country"), "UTF-8")+"&met="+encode((String)map.get("Metascore"), "UTF-8")
                         +"&title="+urltitle+"\">Add Movie</a>"); */
                        out.println("<form id=\"addFilm\" method=\"post\" action=\"AddMovie\">"
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
                                + "<input type=\"submit\" name=\"sub\" value=\"Add to Collection\" form=\"addFilm\"/>"
                                + "</form>");
                    }
                    out.println("</li>");
                } else {
                    out.println("<li>" + key + ": " + map.get(key) + "</li>");
                }
            }
            URL url2 = new URL("https://api.themoviedb.org/3/find/" + map.get("imdbID") + "?external_source=imdb_id&api_key=485892eacda398b32d06aa04114b3974");

            ObjectMapper mapper2 = new ObjectMapper();
            Map<String, Object> map2 = mapper2.readValue(url2, Map.class);
            for (String key2 : map2.keySet()) {
                out.println("<li>" + key2 + ": " + map2.get(key2) + "</li>");
            }
            out.println("<li><img alt='" + map.get("Title") + "' src='http://image.tmdb.org/t/p/w100" + map2.get("poster_path") + "' /></li> ");
            out.println("</ul>");
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
            if (key.equals("Poster")) {
                URL url2 = new URL("https://api.themoviedb.org/3/find/" + map.get("imdbID") + "?external_source=imdb_id&api_key=485892eacda398b32d06aa04114b3974");
                
                FileWriter postLog = new FileWriter("poster.txt", true);
                postLog.write(url2 + "\n");
                postLog.flush();
                postLog.write("\nImdb ID: " + map.get("imdbID"));
                postLog.flush();
                
                ObjectMapper postmap = new ObjectMapper();
                Map<String, Object> map2 = postmap.readValue(url2, Map.class);
                for ( String keyP : map2.keySet() ) {
                    postLog.write( keyP + ": " + map2.get(keyP) + "\n");
                    postLog.flush();
                }
                String movres = map2.get("movie_results").toString();
                postLog.write(movres);
                postLog.flush();
                String purl = movres.substring(movres.lastIndexOf("poster_path=") + 12, movres.indexOf(", popularity"));
                
                posterU = new URL("http://image.tmdb.org/t/p/w500" + purl);
                posterURL = posterU.toString();
                
                request.setAttribute("poster", posterURL);
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
        /*
        URL url2 = new URL("https://api.themoviedb.org/3/find/" + map.get("imdbID") + "?external_source=imdb_id&api_key=485892eacda398b32d06aa04114b3974");

        ObjectMapper mapper2 = new ObjectMapper();
        Map<String, Object> map2 = mapper2.readValue(url2, Map.class);
        for (String key2 : map2.keySet()) {
            if (key2.equals("Response") || key2.equals("movie_results") || key2.equals("person_results")
                    || key2.equals("tv_results") || key2.equals("tv_episode_results")
                    || key2.equals("tv_season_results")) {

            } else {
                results += "<li>" + key2 + ": " + map2.get(key2) + "</li>";
            }
        } */
        results += "</ul>";
        request.setAttribute("results", results);

        request.getRequestDispatcher("fvfind2.jsp").forward(request, response);

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

        URL url = new URL("http://www.omdbapi.com/?t=" + urltitle + "&y=" + urlyear);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(url, Map.class);

        String results = "<ul class=\"w3-ul\">";
        for (String key : map.keySet()) {
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
                            + "<input type=\"submit\" name=\"sub\" value=\"Add to Collection\" form=\"addFilm\"/>"
                            + "</form>";
                }
                results += "</li>";
            } else {
                results += "<li>" + key + ": " + map.get(key) + "</li>";
            }
        }
        URL url2 = new URL("https://api.themoviedb.org/3/find/" + map.get("imdbID") + "?external_source=imdb_id&api_key=485892eacda398b32d06aa04114b3974");

        ObjectMapper mapper2 = new ObjectMapper();
        Map<String, Object> map2 = mapper2.readValue(url2, Map.class);
        for (String key2 : map2.keySet()) {
            if (key2.equals("Response") || key2.equals("movie_results") || key2.equals("person_results")
                    || key2.equals("tv_results") || key2.equals("tv_episode_results")
                    || key2.equals("tv_season_results")) {

            } else {
                results += "<li>" + key2 + ": " + map2.get(key2) + "</li>";
            }
        }
        results += "</ul>";
        request.setAttribute("results", results);

        request.getRequestDispatcher("fvfind2.jsp").forward(request, response);
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
