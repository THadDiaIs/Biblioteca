/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Tester;

import ClassTemplates.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utiler.FileProcessor;

/**
 *
 * @author antonh
 */
public class TesterMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //       System.out.println(borrow0.borrowTime());
//       System.out.println(borrow0.getBorrowDate()[0]);
//       System.out.println(borrow0.returnedBook());
        //String dateString = "2024-02-21";
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = null;
        //try {
        //    date = sdf.parse(dateString);
        //} catch (ParseException ex) {
        //    Logger.getLogger(TesterMain.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //System.out.println(date);
        
        
        // TODO code application logic here
        FileProcessor fp = new FileProcessor();
        File file = new File("src/DataBase/books.txt");
        ArrayList<Book> books = fp.FileParser(file);
        
        System.out.println(books.size());
        for (Book b : books){
            System.out.println(b.getName());
        }
//        try {
//            System.out.println(fp.FileLoader0(file));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TesterMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
