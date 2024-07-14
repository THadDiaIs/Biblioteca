/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Tester;

import ClassTemplates.Book;
import ClassTemplates.Borrow;
import ClassTemplates.Student;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utiler.DataProcessor;

import utiler.FileProcessor;
import utiler.Filters;
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
        
        for (int i = 0; i < borrows.size(); i++){
            System.out.println(borrows.get(i).getISBN());
            System.out.println(books.get(i).getISBN());
        }
        
//        try {
//            System.out.println(fp.FileLoader0(file));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TesterMain.class.getName()).log(Level.SEVERE, null, ex);
//        }

        DataProcessor dataProc = new DataProcessor(students, borrows, books, 8, 10);
        Filters flt = new Filters();
        
        List<Borrow> fBorrows = flt.filterBooks("ac", borrows, 3);
        System.out.println(fBorrows.size());
        fBorrows = flt.filterBooks("et", borrows, 3);
        System.out.println(fBorrows.size());
        fBorrows = flt.filterBooks("e", borrows, 3);
        System.out.println(fBorrows.size());
        fBorrows = flt.filterBooks("all", borrows, 3);
        System.out.println(fBorrows.size());
        
        List<Borrow> res = flt.getBorrow("lmnopqrstuv", borrows);
        System.out.println(res.getFirst().getISBN());
        
        LocalDate tDate = LocalDate.of(2024,03,03);
        System.out.println(tDate.toString());
        System.out.println(tDate);
        System.out.println(LocalDate.now());
    }
    
}
