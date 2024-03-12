/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassTemplates;

import java.util.Date;

/**
 *
 * @author matvey
 */
public class Book {
    private String ISBN; //3\d - 3\w
    private String name;
    private String author;
    private String editorial;
    private String edition;
    private int stock;
    
    public Book( String name, String ISBN, String author, String editorial, String edition, String stock){
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.editorial = editorial;
        this.edition = edition;
        this.stock = Integer.parseInt(stock.strip());
    }
   //set the logic to control the actual quantity of books and the borrowed ones

    public String getISBN() {
        return ISBN;
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
    
    
}
