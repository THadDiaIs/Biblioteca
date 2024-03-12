/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gitpratice;

import ClassTemplates.*;
import java.io.File;
import utiler.FileProcessor.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import utiler.FileProcessor;

/**
 *
 * @author matvey
 */
public class GitPratice {
//   static Student student0 = new Student("202460030");
//   static Borrow borrow0 = new Borrow("newBorrow",student0.getID(),23456765,new Date(System.currentTimeMillis() - (30*24*60*60*1000L)));

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args){
        FileProcessor fp = new FileProcessor();
//        fp.FileProcessor(bookStock);
        ArrayList<Book> books = fp.BookDBLoader(new File("src/DataBase/books.diadb"));
        ArrayList<Student> students = fp.StudentDBLoader(new File("src/DataBase/students.diadb"));
        ArrayList<Borrow> borrows = fp.BorrowDBLoader(new File("src/DataBase/borrows.diadb"));
        
        


    }
}
