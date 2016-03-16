/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.model;

/**
 *
 * @author Theriault
 */
public class Post {

    public Post(String title, String post, int year, String month, int day, int hour, int min, String ampm, String user) {
        this.title = title;
        this.post = post;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.ampm = ampm;
        this.user = user;
    }

    private String title;
    private String post;
    private int year;
    private String month;
    private int day;
    private int hour;
    private int min;
    private String ampm;
    private String user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
   /*@Override
    public String toString() {
        return book + " " + chapter + ":" + verse;
    }
*/
    public String toFileString() {
        return title + ";," + post + ";," + year + ";," + month + ";," + day + ";," + hour + ";," + min + ";," + ampm + ";," + user;
    }

    public void loadFromFileString(String str) {
        // TODO: Validation should be done here
        String[] parts = str.split(";,");
        

        title = parts[0];
        post = parts[1];
        year = Integer.parseInt(parts[2]);
        month = parts[3];
        day = Integer.parseInt(parts[4]);
        hour = Integer.parseInt(parts[5]);
        min = Integer.parseInt(parts[6]);
        ampm = parts[7];
        user = parts[8];
        
    }

}
