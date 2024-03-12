/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gitpratice;

import ClassTemplates.*;
import gui.MainGui;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import utiler.FileProcessor;

/**
 *
 * @author matvey
 */
public class GitPratice {
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args){
        FileProcessor fp = new FileProcessor();
        ArrayList<Book> books = fp.BookDBLoader(new File("src/DataBase/books.diadb"));
        ArrayList<Student> students = fp.StudentDBLoader(new File("src/DataBase/students.diadb"));
        ArrayList<Borrow> borrows = fp.BorrowDBLoader(new File("src/DataBase/borrows.diadb"));
        
        MainGui rootWind = new MainGui();
        JTable table = new JTable();
        TableColumn tc = new TableColumn(1,1);
//        rootWind.add(table.getTableHeader().getColumnModel().addColumn(tc));

//        load overview tab
//        load each control tab: borrows, students, books
//        create a generic formulary to allow users to store new elements to each book, student or borro
//          design and create ui && configure event listeners wich executes functions to filter and present data -a update table-
        try {
            /* Create and display the form */
            java.awt.EventQueue.invokeAndWait(() -> {
                rootWind.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
