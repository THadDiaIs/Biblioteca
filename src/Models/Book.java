package Models;

import java.util.Date;

/**
 *
 * @author matvey
 */
public class Book {
    private String code; //3\d - 3\w
    private String name;
    private String author;
    private String editorial;
    private String edition;
    private int stock;
    
    public Book( String name, String code, String author, String editorial, String edition, String stock){
        this.code = code;
        this.name = name;
        this.author = author;
        this.editorial = editorial;
        this.edition = edition;
        this.stock = Integer.parseInt(stock.strip());
    }
   //set the logic to control the actual quantity of books and the borrowed ones

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getEdition() {
        return edition;
    }
    
    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "NAME:" + name + "\nCODE:" + code + "\nAUTHOR:" + author + "\nEDITORIAL:" + editorial + "\nEDITION:" + edition + "\nSTOCK:" + stock;
    }
    
    
}
