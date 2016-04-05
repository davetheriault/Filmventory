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
    public void addUser(String fbid, String f_name, String l_name, String email) {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        //  ResultSet rs = null;
        try {
            //connect

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql = "INSERT INTO user (fb_id,first_name,last_name,email) "
                    + "VALUES ('" + fbid + "','" + f_name + "','" + l_name + "','" + email + "') ;";

            stmt.executeQuery(sql);

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
                user.setEmail(rs.getString("email"));
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
                if (rs.getString("fb_id") != "" && rs.getString("fb_id") != null ) {
                    check = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
