package facebook;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public void addMovie(String fb_id, String title, String year, String rated, String released,
            String runtime, String[] genre, String[] director, String[] writer, String[] actors, String plot,
            String language, String country, String metascore) throws IOException {

        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        title = title.replace("'", "\\'");
        int movie_id = 0;
        int genre_id = 0;

        //  ADD DIRECTORS TO LIST AND ESCAPE APOSTROPHES
        List<String> dirs = new ArrayList<>();
        for (String dir : director) {
            dir = dir.replace("'", "\\'");
            dirs.add(dir);
        }
        FileWriter logs = new FileWriter("logs.txt", true);
        logs.write("\nJDBC Line 181 \n List<string> dirs: \n");
        for (String dirlog : dirs) {
            logs.write(dirlog + "\n");
        }

        //  ADD WRITERS TO LIST AND ESCAPE APOSTROPHES
        List<String> wris = new ArrayList<>();
        for (String wri : writer) {
            wri = wri.replace("'", "\\'");
            wris.add(wri);
        }
        logs.write("\n Line 192 \n List<string> wris: \n");
        for (String wrilog : wris) {
            logs.write(wrilog + "\n");
        }

        //  ADD ACTORS TO LIST AND ESCAPE APOSTROPHES
        List<String> acts = new ArrayList<>();
        for (String act : actors) {
            act = act.replace("'", "\\'");
            acts.add(act);
        }
        logs.write("\n Line 203 \n List<string> acts: \n");
        for (String actlog : acts) {
            logs.write(actlog + "\n");
        }
        // ESCAPE APOSTOPHES FOR PLOT
        plot = plot.replace("'", "\\'");
        plot = plot.replace("\"", "\\'");

        country = country.replace("'", "\\'");
        logs.write("\n Plot: \n" + plot + "\n");
        logs.write("\n Country: \n" + country + "\n");
        logs.flush();

        try {
            // ESTABLISH DATABASE CONNECTION
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            boolean xmov = checkExist2("movie", "title", "year", title, year);
            logs.write("\n LINE 218 \n CheckMovieExists: \n");
            logs.write(String.valueOf(xmov) + "\n");
            logs.flush();

            // ---- INSERT MOVIE TO MOVIE TABLE ----- 
            if (xmov != true) {

                logs.write("\n Xmov Not True Checks Out \n");
                logs.flush();

                sql = "INSERT INTO movie (title, year, rated, released, runtime, plot, language, country, metascore)"
                        + "VALUES ('" + title + "','" + year + "','" + rated + "','" + released + "','" + runtime + "','" + plot + "','" + language + "','" + country + "','" + metascore + "');";
                logs.write("\n" + sql);
                logs.flush();
                stmt = conn.prepareStatement(sql);
                logs.write("\n Prepared Statement \n");
                stmt.execute();
                logs.flush();
                movie_id = getMovieId(title, year);

                // ---- GENRE MANAGEMENT ----- //
                for (String genr : genre) {
                    // CHECK GENRE EXISTENCE
                    boolean genr1 = checkExists("genre", "genre", genr);
                    logs.write("\n CHECK if GENRE " + genr + " EXISTS: \n");
                    logs.write(String.valueOf(genr1) + "\n");
                    logs.flush();
                    // IF NEW GENRE, ADD TO GENRE TABLE
                    if (!genr1) {
                        logs.write("Genre Not True Passed \n");
                        logs.flush();
                        sql = "INSERT INTO genre (genre)"
                                + "VALUES ('" + genr + "')";
                        stmt = conn.prepareStatement(sql);
                        stmt.executeUpdate(sql);
                    } else {
                        logs.write("Genre Not True Failed... skip adding Genre \n\n");
                        logs.flush();
                    }

                    // ----- RELATE GENRE TO MOVIE ---- /
                    genre_id = getId("genre", "genre", genr);

                    logs.write("Genre Id — " + genre_id + " \nMovie Id — " + movie_id + "\n");
                    logs.flush();
                    // IF RELATIONSHIP DOESNT EXIST ADD IT TO MOVIE2GENRE TABLE ------
                    sql = "INSERT INTO movie2genre (movie_id, genre_id) "
                            + "VALUES ('" + movie_id + "', '" + genre_id + "')";
                    stmt = conn.prepareStatement(sql);
                    stmt.executeUpdate(sql);

                }
                // === INSERT DIRECTOR INTO CREW TABLE --- //
                for (String dire : dirs) {
                    boolean crew1 = checkExists("crew", "name", dire);
                    logs.write("\nCheckExists " + dire + " = " + crew1 + "\n");
                    logs.flush();
                    if (crew1 == false) {
                        sql = "INSERT INTO crew (name)"
                                + "VALUES ('" + dire + "')";
                        stmt = conn.prepareStatement(sql);
                        int insertD = stmt.executeUpdate(sql);
                        if (insertD != 0) {
                            logs.write("\nDirector " + dire + " inserted. \n");
                            logs.flush();
                        } else {
                            logs.write("\nDirector " + dire + " failed. \n");
                            logs.flush();
                        }
                    }
                    int crew_id = getId("crew", "name", dire);
                    logs.write("\ncrew_id = " + crew_id + "\n");
                    sql = "INSERT INTO crew2movie (crew_id, movie_id, position) "
                            + "VALUES ('" + crew_id + "', '" + movie_id + "', 'director')";
                    stmt = conn.prepareStatement(sql);
                    stmt.executeUpdate(sql);
                }

                // === INSERT WRITER INTO CREW TABLE --- //
                for (String writ : wris) {
                    boolean writ1 = checkExists("crew", "name", writ);
                    logs.write("\nCheckExists " + writ + " = " + writ1 + "\n");
                    logs.flush();
                    if (writ1 == false) {
                        sql = "INSERT INTO crew (name)"
                                + "VALUES ('" + writ + "')";
                        stmt = conn.prepareStatement(sql);
                        int insertW = stmt.executeUpdate(sql);
                        if (insertW != 0) {
                            logs.write("\nWriter " + writ + " inserted. \n");
                            logs.flush();
                        } else {
                            logs.write("\nWriter " + writ + " failed. \n");
                        }
                    }
                    int crew_id = getId("crew", "name", writ);
                    sql = "INSERT INTO crew2movie (crew_id, movie_id, position) "
                            + "VALUES ('" + crew_id + "', '" + movie_id + "', 'writer')";
                    stmt = conn.prepareStatement(sql);
                    stmt.executeUpdate(sql);
                }
                // === INSERT ACTOR INTO CREW TABLE --- //
                for (String acto : acts) {
                    boolean act1 = checkExists("crew", "name", acto);
                    logs.write("\nCheckExists " + acto + " = " + act1 + "\n");
                    logs.flush();
                    if (act1 == false) {
                        sql = "INSERT INTO crew (name)"
                                + "VALUES ('" + acto + "')";
                        stmt = conn.prepareStatement(sql);
                        int insertA = stmt.executeUpdate(sql);
                        if (insertA != 0) {
                            logs.write("\nDirector " + acto + " inserted. \n");
                            logs.flush();
                        } else {
                            logs.write("\nDirector " + acto + " failed. \n");
                        }
                    }
                    int crew_id = getId("crew", "name", acto);
                    sql = "INSERT INTO crew2movie (crew_id, movie_id, position) "
                            + "VALUES ('" + crew_id + "', '" + movie_id + "', 'actor')";
                    stmt = conn.prepareStatement(sql);
                    stmt.executeUpdate(sql);
                }
            }
            // --- CREATE MOVIE2USER RELATIONSHIP
            int mov_id = getMovieId(title, year);
            int use_id = getUserId(fb_id);
            sql = "INSERT INTO movie2user (movie_id, user_id) VALUES ('" + mov_id + "', '" + use_id + "')";
            stmt = conn.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return use_id;
    }

    private int getId(String table, String col, String value) {
        int id = 0;
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT id FROM " + table + " WHERE " + col + " = '" + value + "' LIMIT 1";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
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

                if (rs.getInt("id") >= 1) {
                    m2u = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return m2u;
    }

    public boolean checkExists(String table, String col, String value) {
        boolean checkEx = false;
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            sql = "SELECT id FROM " + table + " WHERE " + col + " = '" + value + "' LIMIT 1";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                if (rs.getInt("id") >= 1) {
                    checkEx = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return checkEx;
    }

    public boolean checkExist2(String table, String col1, String col2, String value1, String value2) {
        boolean checkR = false;
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            sql = "SELECT id FROM " + table + " WHERE " + col1 + " = '" + value1 + "' "
                    + "AND " + col2 + " = '" + value2 + "' LIMIT 1";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                if (rs.getInt("id") >= 1) {
                    checkR = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return checkR;
    }

    public List getInventory(String fb_id, String sort) throws IOException {

        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        List<Movie> list = new ArrayList<>();
        if (sort.equals("az") || sort.equals("")) { sort = "titlesort ASC"; }
        if (sort.equals("za")) { sort = "titlesort DESC"; }
        if (sort.equals("y09")) { sort = "movie.year ASC"; }
        if (sort.equals("y90")) { sort = "movie.year DESC"; }
        try {
            FileWriter logs = new FileWriter("inventory.txt", true);

            int user_id = this.getUserId(fb_id);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT *, CASE WHEN movie.title LIKE 'The %' THEN trim(substr(movie.title, 4)) "
                + "ELSE movie.title END AS titlesort FROM movie "
                + "INNER JOIN movie2user ON movie.id=movie2user.movie_id ";
            
            sql += "WHERE movie2user.user_id = '" + user_id + "' ";
            
            sql += "ORDER BY " + sort + " ";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Movie mov = new Movie();
                mov.setTitle(rs.getString("title"));
                mov.setYear(rs.getString("year"));
                mov.setRated(rs.getString("rated"));
                mov.setReleased(rs.getString("released"));
                mov.setRuntime(rs.getString("runtime"));
                mov.setPlot(rs.getString("plot"));
                mov.setLanguage(rs.getString("language"));
                mov.setCountry(rs.getString("country"));
                mov.setMetascore(rs.getString("metascore"));

                logs.write("\n Movie Details: \n" + mov.getTitle() + "\n" + mov.getMetascore() + "\n");
                logs.flush();

                List<String> g = getGenres(rs.getInt("id"));
                logs.write("\nGenres: \n");
                for (String gr : g) {
                    logs.write(gr + "\n");
                    logs.flush();
                }
                mov.setGenre(g);
                logs.write("\nGenres in Movie Object: \n");
                for (String gm : mov.getGenre()) {
                    logs.write(gm + "\n");
                    logs.flush();
                }
                
                List<String> d = getPositions(rs.getInt("id"), "director");
                logs.write("\nDirectors: \n");
                for (String di : d) {
                    logs.write(di + "\n");
                    logs.flush();
                }
                List<String> w = getPositions(rs.getInt("id"), "writer");
                logs.write("\nWriters: \n");
                for (String wr : w) {
                    logs.write(wr + "\n");
                    logs.flush();
                }
                List<String> a = getPositions(rs.getInt("id"), "actor");
                logs.write("\nActors: \n");
                for (String ac : a) {
                    logs.write(ac + "\n");
                    logs.flush();
                }
                
                mov.setDirector(d);
                mov.setWriter(w);
                mov.setActors(a);

                list.add(mov);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    Movie getMovie(String title, String year) throws IOException {

        int movid = getMovieId(title, year);

        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        Movie mov = new Movie();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT * FROM movie WHERE id = '" + movid + "' LIMIT 1";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                mov.setTitle(rs.getString("title"));
                mov.setYear(rs.getString("year"));
                mov.setRated(rs.getString("rated"));
                mov.setReleased(rs.getString("released"));
                mov.setRuntime(rs.getString("runtime"));
                mov.setPlot(rs.getString("plot"));
                mov.setLanguage(rs.getString("language"));
                mov.setCountry(rs.getString("country"));
                mov.setMetascore(rs.getString("metascore"));
            }
            List<String> genres = getGenres(movid);
            mov.setGenre(genres);
            
            List d = getPositions(movid, "director");
            mov.setDirector(d);
            
            List w = getPositions(movid, "writer");
            mov.setWriter(w);
            
            List a = getPositions(movid, "actor");
            mov.setActors(a);

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mov;
    }

    public List getGenres(int movie_id) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        List<String> genreList = new ArrayList<>();

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        sql = "SELECT genre FROM genre g "
                + "INNER JOIN movie2genre mg ON g.id=mg.genre_id "
                + "WHERE mg.movie_id = '" + movie_id + "' ";

        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            genreList.add(rs.getString("genre"));
        }

        return genreList;
    }

    public List getPositions(int movie_id, String pos) throws IOException {

        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        List<String> crew = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            sql = "SELECT c.name, cm.position "
                    + "FROM crew c "
                    + "INNER JOIN crew2movie cm "
                    + "ON c.id=cm.crew_id "
                    + "WHERE cm.movie_id = '" + movie_id + "' "
                    + "AND cm.position = '" + pos + "'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                crew.add(rs.getString("name"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            FileWriter exc = new FileWriter("exception.txt", true);
            exc.write(ex.getMessage());
            exc.flush();
            exc.close();
        }

        return crew;

    }

    void getGenreMovies(String fb_id, String genre) {

        
    
    }

    

}
