package gitpratice;

import ClassTemplates.*;
import gui.MainGui;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/*import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;*/
import utiler.FileProcessor;

/**
 *
 * @author matvey
 */
public class GitPratice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileProcessor fp = new FileProcessor();
        ArrayList<Student> students = fp.StudentDBLoader(new File("src/DataBase/students.diadb"));
        ArrayList<Borrow> borrows = fp.BorrowDBLoader(new File("src/DataBase/borrows.diadb"));
        ArrayList<Book> books = fp.BookDBLoader(new File("src/DataBase/books.diadb"));

        MainGui rootWind = new MainGui();
//        String[][] data = {{"co","co","tu","ty"},{"co","co","tu","ty"},{"co","co","tu","ty"},{"co","co","tu","ty"}};
//        String[] headers = {"dc","cf","ds","ey5"};
//        DefaultTableModel dTM = new DefaultTableModel(data,headers);
//        
//        JTable table = new JTable();
//        table.setModel(dTM);
//        JTabbedPane tabbedPane = new JTabbedPane();
//        JPanel pane = new JPanel();
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.add(table);
//        
//        rootWind.add(scrollPane);
        try {
            rootWind.setDataBase(students, borrows, books, 8, 10);
            rootWind.loadOverview();
            rootWind.loadBorrows("a");
            java.awt.EventQueue.invokeAndWait(() -> {
                rootWind.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
