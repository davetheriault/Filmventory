package facebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

public class JDBC {

    private String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    private String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    private String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    private String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");

    // JDBC driver name and database URL
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://" + host + ":" + port + "/java";

    //  Database credentials
    private String USER = username;
    private String PASS = password;

    public JDBC() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        JDBC db = new JDBC();
        //db.getPeople();
    }

    public void addUser(String fbid, String f_name, String l_name) {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        //  ResultSet rs = null;
        try {
            //connect

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "INSERT INTO user (fb_id,first_name,last_name) VALUES ('" + fbid + "','" + f_name + "','" + l_name + "') ;";

            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
    }

    public User getUser(String fb_id) {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        User user = new User();

        //  ResultSet rs = null;
        try {
            //connect
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT * FROM user WHERE fb_id = '" + fb_id + "' LIMIT 1;";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user.setFbId(rs.getString("fb_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
        return user;
    }

    public boolean checkUser(String fb_id) {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        boolean check = false;

        //  ResultSet rs = null;
        try {
            //connect
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT fb_id FROM user WHERE fb_id = '" + fb_id + "' LIMIT 1;";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("fb_id") != "" && rs.getString("fb_id") != null) {
                    check = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
        return check;
    }

    public void addMovie(String fb_id, String title, String year, String rated, String released,
            String runtime, String genre, String director, String writer, String actors, String plot,
            String language, String country, String metascore) {

        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //sql = "SELECT title FROM movie WHERE title = '" + title + "' AND year = '" + year + "' LIMIT 1;";

            //rs = stmt.executeQuery(sql);
            //while (rs.next()) {
            //    if ("".equals(rs.getString("title")) || rs.getString("title") == null) {
                    sql = "INSERT INTO movie (title,year,rated,released,runtime,genre,director,writer,actors,plot,language,country,metascore)"
                            + "VALUES ('" + title + "','" + year + "','" + rated + "','" + released + "','" + runtime + "','" + genre + "',"
                            + "'" + director + "','" + writer + "','" + actors + "','" + plot + "','" + language + "','" + country + "','" + metascore + "')";
                    sql = sql.replace("'", "\\'");
                    stmt = conn.prepareStatement(sql);
                    stmt.executeUpdate(sql);
            //    }
            //}
            int mov_id = getMovieId(title, year);
            int use_id = getUserId(fb_id);
            sql = "INSERT INTO movie2user (movie_id,user_id) VALUES ('"+mov_id+"','"+use_id+"')";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
    }

    private int getMovieId(String title, String year) {
        int mov_id = 0;
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT id FROM movie WHERE title = '" + title + "' AND year = '" + year + "' LIMIT 1";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                mov_id = rs.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
        return mov_id;
    }

    private int getUserId(String fb_id) {
        int use_id = 0;
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT id FROM user WHERE fb_id = '" + fb_id + "' LIMIT 1";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                use_id = rs.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
        return use_id;
    }
    
    public boolean checkMovie2User(String fb_id, String title, String year) {
        boolean m2u = false;
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            int mov = getMovieId(title, year);
            int user = getUserId(fb_id);
            sql = "SELECT id FROM movie2user WHERE movie_id = '" + mov + "' AND user_id = '" + user + "' LIMIT 1";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                if ( rs.getInt("id") >= 1 ) {
                    m2u = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
        return m2u;
    }

    public List getInventory(String fb_id) {

        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        List<Movie> list = new ArrayList<>();

        try {
            int user_id = this.getUserId(fb_id);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT * FROM movie INNER JOIN movie2user ON movie.id=movie2user.movie_id WHERE movie2user.user_id = '"+user_id+"' ORDER BY movie.title";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Movie mov = new Movie();
                mov.setTitle(rs.getString("title"));
                mov.setYear(rs.getString("year"));
                mov.setRated(rs.getString("rated"));
                mov.setReleased(rs.getString("released"));
                mov.setRuntime(rs.getString("runtime"));
                mov.setGenre(rs.getString("genre"));
                mov.setDirector(rs.getString("director"));
                mov.setWriter(rs.getString("writer"));
                mov.setActors(rs.getString("actors"));
                mov.setPlot(rs.getString("plot"));
                mov.setLanguage(rs.getString("language"));
                mov.setCountry(rs.getString("country"));
                mov.setMetascore(rs.getString("metascore"));
                                               
                list.add(mov);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
        return list;
    }

    Movie getMovie(String title, String year) {

        int movid = getMovieId(title, year);
        
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        Movie mov = new Movie();
        
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT * FROM movie WHERE id = '"+movid+"' LIMIT 1";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                mov.setTitle(rs.getString("title"));
                mov.setYear(rs.getString("year"));
                mov.setRated(rs.getString("rated"));
                mov.setReleased(rs.getString("released"));
                mov.setRuntime(rs.getString("runtime"));
                mov.setGenre(rs.getString("genre"));
                mov.setDirector(rs.getString("director"));
                mov.setWriter(rs.getString("writer"));
                mov.setActors(rs.getString("actors"));
                mov.setPlot(rs.getString("plot"));
                mov.setLanguage(rs.getString("language"));
                mov.setCountry(rs.getString("country"));
                mov.setMetascore(rs.getString("metascore"));
                                               
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (conn != null) { conn.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
            try { if (stmt != null) { stmt.close(); }
            } catch (SQLException ex) { Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex); }
        }
        return mov;
    }
    
    
}
