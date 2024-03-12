/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utiler;

import ClassTemplates.Borrow;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author antonh
 */
public class Filters {

    public TableModel overview(ArrayList<Borrow> borrows) {
        String[][] data = new String[borrows.size()][4];

        for (int i = 0; i < data.length; i++) {
            Borrow tmp = borrows.get(i);
            data[i] = new String[]{tmp.getID(), tmp.getISBN(), tmp.getStudentID(), tmp.getBorrowDate()[0].toString()};
        }

        return new DefaultTableModel(data, new String[]{
            "Title 1", "Title 2", "Title 3", "Title 4"
        });
    }

}
