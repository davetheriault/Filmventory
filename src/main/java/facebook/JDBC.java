package facebook;

import java.sql.Connection;
import java.sql.DriverManager;
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

    /*public List getPeople() {
     Connection conn = null;
     Statement stmt = null;
     String sql = null;
     ResultSet rs = null;
     List people = new ArrayList<>();
     try {
     //connect

     conn = DriverManager.getConnection(DB_URL, USER, PASS);
     stmt = conn.createStatement();
     sql = "SELECT * FROM ancestor;";
     rs = stmt.executeQuery(sql);
     while (rs.next()) {

     Person person = new Person();
     person.setFirstName(rs.getString("first_name"));
     person.setLastName(rs.getString("last_name"));
     person.setId(rs.getInt("id"));
     person.setBirthday(rs.getDate("birthday"));
     person.setGender(rs.getString("gender"));

     people.add(person);
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

     return people;
     }

     public List<Person> getParents(String child) {
     Connection conn = null;
     Statement stmt = null;
     String sql = null;
     ResultSet rs = null;
     List<Person> parents = new ArrayList<>();
     try {
     //connect

     conn = DriverManager.getConnection(DB_URL, USER, PASS);
     stmt = conn.createStatement();
     sql = "SELECT * FROM ancestor INNER JOIN relationship ON ancestor.id=relationship.parent_id WHERE relationship.child_id = " + child + ";";
     rs = stmt.executeQuery(sql);
     while (rs.next()) {

     Person person = new Person();
     person.setFirstName(rs.getString("first_name"));
     person.setLastName(rs.getString("last_name"));
     person.setId(rs.getInt("id"));
     person.setBirthday(rs.getDate("birthday"));
     person.setGender(rs.getString("gender"));

     parents.add(person);
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

     return parents;
     }

     public List<Person> getKids(String parent) {
     Connection conn = null;
     Statement stmt = null;
     String sql = null;
     ResultSet rs = null;
     List<Person> kids = new ArrayList<>();
     try {
     //connect

     conn = DriverManager.getConnection(DB_URL, USER, PASS);
     stmt = conn.createStatement();
     sql = "SELECT * FROM ancestor INNER JOIN relationship ON ancestor.id=relationship.child_id WHERE relationship.parent_id = " + parent + " ORDER BY birthday;";
     rs = stmt.executeQuery(sql);
     while (rs.next()) {

     //   Person person = new Person();
     person.setFirstName(rs.getString("first_name"));
     person.setLastName(rs.getString("last_name"));
     person.setId(rs.getInt("id"));
     person.setBirthday(rs.getDate("birthday"));
     person.setGender(rs.getString("gender"));

     kids.add(person);
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

     return kids;
     }
    
     */
    Connection conn = null;
    Statement stmt = null;
    String sql = null;
    ResultSet rs = null;

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
        }
        return check;
    }

    public void addMovie(String fb_id, String title, String year, String rated, String released,
            String runtime, String genre, String director, String writer, String actors, String plot,
            String language, String country, String metascore) {

        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "SELECT title FROM movie WHERE title = '" + title + "' AND year = '" + year + "' LIMIT 1;";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if ("".equals(rs.getString("title")) || rs.getString("title") == null) {
                    sql = "INSERT INTO movie (title,year,rated,released,runtime,genre,director,writer,actors,plot,language,country,metascore)"
                            + "VALUES ('" + title + "','" + year + "','" + rated + "','" + released + "','" + runtime + "','" + genre + "',"
                            + "'" + director + "','" + writer + "','" + actors + "','" + plot + "','" + language + "','" + country + "','" + metascore + "')";
                    stmt.executeUpdate(sql);
                }
            }
            int mov_id = getMovieId(title, year);
            int use_id = getUserId(fb_id);
            sql = "INSERT INTO movie2user (movie_id,user_id) VALUES ('"+mov_id+"','"+use_id+"')";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getMovieId(String title, String year) {
        int mov_id = 0;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM movie WHERE title = '" + title + "' AND year = '" + year + "' LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                mov_id = rs.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mov_id;
    }

    private int getUserId(String fb_id) {
        int use_id = 0;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM user WHERE fb_id = '" + fb_id + "' LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                use_id = rs.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return use_id;
    }
    
    public boolean checkMovie2User(String fb_id, String title, String year) {
        boolean m2u = false;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            int mov = getMovieId(title, year);
            int user = getUserId(fb_id);
            String sql = "SELECT id FROM movie2user WHERE movie_id = '" + mov + "' AND user_id = '" + user + "' LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                if ( rs.getInt("id") >= 1 ) {
                    m2u = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m2u;
    }
}
