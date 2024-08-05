package Test;

import Models.Book;
import Models.Borrow;
import Models.Student;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Utils.DataProcessor;
import Utils.DataLoader;
import Utils.DataSaver;
import Utils.Filters;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

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
        DataLoader fp = new DataLoader();
        Filters fltr = new Filters();

//        ArrayList<Book> books = fp.BookDBLoader(new File("src/DataBase/books.txt"));
//        
//        System.out.println(books.size());
//        for (Book b : books){
//            System.out.println(b.getName());
//        }
        String[] a = {"a", "b"};

        //a = a.toString().concat("k").split("\\w");
        System.out.println();
        ArrayList<Book> books = fp.BookDBLoader(new File("src/Data/books.diadb"));
        ArrayList<Student> students = fp.StudentDBLoader(new File("src/Data/students.diadb"));
        ArrayList<Borrow> borrows = fp.BorrowDBLoader(new File("src/Data/borrows.diadb"));

        for (int i = 0; i < borrows.size(); i++) {
            System.out.println(borrows.get(i).toString());
            System.out.println("-------");
        }
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString());
            System.out.println("-------");
            /*System.out.println(students.get(i).toString());
            System.out.println("\n\n\n");*/
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).toString());
            System.out.println("-------");
        }
//        try {
//            System.out.println(fp.FileLoader0(file));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TesterMain.class.getName()).log(Level.SEVERE, null, ex);
//        }

        /*DataProcessor dataProc = new DataProcessor(students, borrows, books, 8, 10);
        Filters flt = new Filters();
        
        List<Borrow> fBorrows = flt.filterBooks("ac", borrows, 3);
        System.out.println(fBorrows.size());
        fBorrows = flt.filterBooks("et", borrows, 3);
        System.out.println(fBorrows.size());
        fBorrows = flt.filterBooks("e", borrows, 3);
        System.out.println(fBorrows.size());
        fBorrows = flt.filterBooks("all", borrows, 3);
        System.out.println(fBorrows.size());*/
 /*List<Borrow> res = flt.getBorrow("lmnopqrstuv", borrows);
        System.out.println(res.getFirst().getISBN());*/
 /*LocalDate tDate = LocalDate.of(2024,03,03);
        System.out.println(tDate.toString());
        System.out.println(tDate);
        System.out.println(LocalDate.now());*/
 /*DataSaver dts = new DataSaver();
        Book newBook = new Book("Bible", "09809f094j", "GOD", "Phrophets", "unique", "300");
        Borrow newBr = new Borrow("89uoj4efd", "007", "9780374528379", "2024-06-13,9");
        Borrow newBr1 = new Borrow("ni875fdGR", "004", "9780374528379", "2024-07-02,3");
        books.add(newBook);
        borrows.add(newBr);
        borrows.add(newBr1);*/
 /*try {
            dts.persistBorrows(borrows);
        } catch (IOException ex) {
            Logger.getLogger(TesterMain.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        System.out.println("size of: " + fltr.filterStudentsByName("b", students).size());
        String dd = String.valueOf(LocalDate.now().getDayOfMonth());
        String mm = String.valueOf(LocalDate.now().getMonthValue());
        String yy = Integer.toString(LocalDate.now().getYear());
        dd = dd.length() < 2 ? 0+dd : dd;
        mm = mm.length() < 2 ? 0+mm : mm;
        System.out.println(dd+mm+yy.substring(2));
        //System.out.println(JOptionPane.showConfirmDialog(null, "testing msg", "asking", JOptionPane.YES_NO_OPTION));
    }

}
