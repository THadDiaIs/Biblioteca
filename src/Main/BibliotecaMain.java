package Main;

import Models.Student;
import Models.Loan;
import Models.Book;
import Gui.MainGui;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataLoader;
import java.util.HashMap;

/**
 *
 * @author matvey
 */
public class BibliotecaMain {

    public static void main(String[] args) {
        DataLoader dtL = new DataLoader();
        ArrayList<Student> students = dtL.StudentDBLoader(new File("src/Data/students.diadb"));
        ArrayList<Loan> borrows = dtL.BorrowDBLoader(new File("src/Data/borrows.diadb"));
        ArrayList<Book> books = dtL.BookDBLoader(new File("src/Data/books.diadb"));
        HashMap<String, Double> systemConfig = dtL.loadConf(new File("src/Data/biblio.conf"));
        

        MainGui rootWind = new MainGui();
        try {
            rootWind.setDataBase(students, borrows, books, systemConfig);
            rootWind.load();
            java.awt.EventQueue.invokeAndWait(() -> {
                rootWind.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
