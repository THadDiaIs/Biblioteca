package Main;

import Models.Student;
import Models.Borrow;
import Models.Book;
import Gui.MainGui;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataLoader;

/**
 *
 * @author matvey
 */
public class BibliotecaMain {

    public static void main(String[] args) {
        DataLoader dtL = new DataLoader();
        ArrayList<Student> students = dtL.StudentDBLoader(new File("src/Data/students.diadb"));
        ArrayList<Borrow> borrows = dtL.BorrowDBLoader(new File("src/Data/borrows.diadb"));
        ArrayList<Book> books = dtL.BookDBLoader(new File("src/Data/books.diadb"));

        MainGui rootWind = new MainGui();
        try {
            rootWind.setDataBase(students, borrows, books, 8, 10);
            rootWind.load();
            java.awt.EventQueue.invokeAndWait(() -> {
                rootWind.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
