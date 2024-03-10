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
    //private long ISBN;
    private String code; //3\d - 3\w
    private String name;
    private String author;
    private String editorial;
    private String edition;
    
    public Book(String code, String name, String author, String editorial, String edition){
        //this.ISBN = ISBN;
        this.code = code;
        this.name = name;
        this.author = author;
        this.editorial = editorial;
        this.edition = edition;
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
    
    
}
