/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "AddMovie", urlPatterns = {"/AddMovie"})
public class AddMovie extends HttpServlet {

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
            out.println("<title>Servlet AddMovie</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddMovie at " + request.getContextPath() + "</h1>");
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

        String titl = request.getParameter("tit");
        String year = request.getParameter("yea");
        String rate = request.getParameter("rat");
        String rele = request.getParameter("rel");
        String runt = request.getParameter("run");
        String[] genr = request.getParameter("gen").split(", ");
        String[] dire = request.getParameter("dir").split(", ");
        String writer = request.getParameter("wri");
        
        writer = writer.replaceAll("(\\s\\(.*?\\))", "");
        String[] writ = writer.split(", "); 
        
        String[] acto = request.getParameter("act").split(", ");
        String plot = request.getParameter("plo");
        String lang = request.getParameter("lan");
        String coun = request.getParameter("cou");
        String meta = request.getParameter("met");
        
        FileWriter urll = new FileWriter("URL.txt", true);
        urll.write(request.getParameter("img") + "\n");
        urll.flush();
        
        URI imgurl = null;
        try {
            imgurl = new URI(request.getParameter("img"));
        } catch (URISyntaxException ex) {
            Logger.getLogger(AddMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        File post = new File(imgurl);
        FileInputStream poster = new FileInputStream(post);
        
        String fbid = (String) request.getSession().getAttribute("id");
        db.addMovie(fbid, titl, year, rate, rele, runt, genr, dire, writ, acto, plot, lang, coun, meta, poster);
        
        response.sendRedirect("/MyMovies");

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

        String titl = request.getParameter("tit");
        String year = request.getParameter("yea");
        String rate = request.getParameter("rat");
        String rele = request.getParameter("rel");
        String runt = request.getParameter("run");
        String[] genr = request.getParameter("gen").split(", ");
        String[] dire = request.getParameter("dir").split(", ");
        String writer = request.getParameter("wri");
        
        writer = writer.replaceAll("(\\s\\(.*?\\))", "");
        String[] writ = writer.split(", "); 
        
        String[] acto = request.getParameter("act").split(", ");
        String plot = request.getParameter("plo");
        String lang = request.getParameter("lan");
        String coun = request.getParameter("cou");
        String meta = request.getParameter("met");
        
        FileWriter urll = new FileWriter("URL.txt", true);
        urll.write(request.getParameter("img") + "\n");
        urll.flush();
        
         URI imgurl = null;
        try {
            imgurl = new URI(request.getParameter("img"));
        } catch (URISyntaxException ex) {
            Logger.getLogger(AddMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        File post = new File(imgurl);
        FileInputStream poster = new FileInputStream(post);
        
        String fbid = (String) request.getSession().getAttribute("id");
        db.addMovie(fbid, titl, year, rate, rele, runt, genr, dire, writ, acto, plot, lang, coun, meta, poster);
        

        response.sendRedirect("/MyMovies");

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
