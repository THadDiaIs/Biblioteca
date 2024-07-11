/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Tester;

import ClassTemplates.Book;
import ClassTemplates.Borrow;
import ClassTemplates.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
     * @param args the command line arguments,
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
        
//        ArrayList<Book> books = fp.BookDBLoader(new File("src/DataBase/books.txt"));
//        
//        System.out.println(books.size());
//        for (Book b : books){
//            System.out.println(b.getName());
//        }
        String[] a = {"a","b"};
        
        //a = a.toString().concat("k").split("\\w");
        
        System.out.println();
        ArrayList<Book> books = fp.BookDBLoader(new File("src/DataBase/books.diadb"));
        ArrayList<Student> students = fp.StudentDBLoader(new File("src/DataBase/students.diadb"));
        ArrayList<Borrow> borrows = fp.BorrowDBLoader(new File("src/DataBase/borrows.diadb"));
        
        for (Borrow b :borrows){
            System.out.println(b.borrowTime());
        }
        
//        try {
//            System.out.println(fp.FileLoader0(file));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TesterMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
