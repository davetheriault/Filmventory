/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ancestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Theriault
 */
public class JDBCClass {
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

    public JDBCClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        JDBCClass db = new JDBCClass();
        db.getPeople();
    }

    public List getPeople() {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        List<Person> people = new ArrayList<>();
        try {
            //connect
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println("Connected!");
            sql = "SELECT * FROM ancestor;";
            rs = stmt.executeQuery(sql);
            System.out.println("Executed!");
            while (rs.next()) {
                System.out.println(rs.getString("first_name"));
                System.out.println(rs.getString("last_name"));
                System.out.println(rs.getDate("birthday"));
                System.out.println(rs.getInt("id"));
                Person person = new Person();
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setId(rs.getInt("id"));
                person.setBirthday(rs.getDate("birthday"));
                person.setGender(rs.getString("gender"));

                people.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
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
                System.out.println(rs.getString("first_name"));
                System.out.println(rs.getString("last_name"));
                System.out.println(rs.getDate("birthday"));
                System.out.println(rs.getInt("id"));
                Person person = new Person();
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setId(rs.getInt("id"));
                person.setBirthday(rs.getDate("birthday"));
                person.setGender(rs.getString("gender"));

                parents.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return parents;
    }
}
