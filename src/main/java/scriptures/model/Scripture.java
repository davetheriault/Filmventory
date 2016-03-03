/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptures.model;

/**
 *
 * @author Theriault
 */
public class Scripture {

    private String book;
    private int chapter;
    private int verse;

    public Scripture(String proverbs, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Scripture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getVerse() {
        return verse;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    @Override
    public String toString() {
        return book + " " + chapter + ":" + verse;
    }

    public String toFileString() {
        return book + "," + chapter + "," + verse;
    }

    public void loadFromFileString(String str) {
        // TODO: Validation should be done here
        String[] parts = str.split(",");

        book = parts[0];
        chapter = Integer.parseInt(parts[1]);
        verse = Integer.parseInt(parts[2]);
    }
}
