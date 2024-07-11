package utiler;

import ClassTemplates.Book;
import ClassTemplates.Borrow;
import ClassTemplates.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public final class DataProcessor {

    private List<Student> students;
    private List<Borrow> borrows;
    private List<Book> books;
    private HashMap statistics;

    public DataProcessor(List<Student> students, List<Borrow> borrows, List<Book> books) {
        this.students = students;
        this.borrows = borrows;
        this.books = books;
        this.updateStatistics();
    }
    
    public HashMap getStatistics(){
        return this.statistics;
    }
    
    public void updateStatistics() {
        HashMap map = new HashMap();
        map.put("borrowedBooks", 0);
        map.put("totBook", 0);
        map.put("students", this.students.size());
        map.put("expiredBorr", 0);

        int tmp = 0, tmpExp = 0;
        for (Book b : this.books) {
            tmp += b.getStock();
            //map.replace("totBook", ((Integer)map.get("totBook") + b.getStock()));
        }
        map.replace("totBook", tmp);
        tmp = 0;
        for (Borrow b : this.borrows) {
            if (b.getBorrowDate()[1] == null) {
                tmp++;
                if (b.borrowTime() > 3) {
                    tmpExp++;
                }
            }
        }
        map.replace("borrowedBooks", tmp);
        map.replace("expiredBorr", tmpExp);
        this.statistics = map;
    }
    
    public TableModel borrowsTab(boolean filtered) {
        List<String[]> data = new ArrayList<>();
        DefaultTableModel dtm = new DefaultTableModel(null,new String[]{
            "Code", "Book ISBN", "Student", "Borrowed Date","Days", "To Pay"
        });
        for (int i = 0; i < this.borrows.size(); i++) {
            Borrow tmp = this.borrows.get(i);
            int tmpPaymnt;
            if (filtered && tmp.getBorrowDate()[1] != null) {
                //to select only non returned books
                continue;
            }
            tmpPaymnt = 10 * tmp.borrowTime();
            int bTime = tmp.borrowTime();
            List<Student> stuBorrw = this.students.stream().filter(stu -> stu.getID().equalsIgnoreCase(tmp.getStudentID())).collect(Collectors.toList());
            List<Book> bkBorrw = this.books.stream().filter(b -> b.getISBN().equalsIgnoreCase(tmp.getISBN())).collect(Collectors.toList());
            String date = tmp.getBorrowDate()[0].toString().replace(" 00:00:00 ACDT ", " ");
            //data.add(new String[]{tmp.getID(), tmp.getISBN(), tmp.getStudentID(), date, Integer.toString(bTime), Integer.toString(tmpPaymnt)});
            data.add(new String[]{tmp.getID(), tmp.getISBN(), stuBorrw.getFirst().getName(), date, Integer.toString(bTime), Integer.toString(tmpPaymnt)});
        }
        for (String[] i: data){
            dtm.addRow(i);
        }
        return dtm;
    }
    
    public TableModel booksTab(boolean filtered) {
        List<String[]> data = new ArrayList<>();
        DefaultTableModel dtm = new DefaultTableModel(null,new String[]{
            "Code", "Book ISBN", "Student", "Borrowed Date","Days", "To Pay"
        });
        for (int i = 0; i < this.books.size(); i++) {
            Book tmp = this.books.get(i);
            int tmpPaymnt;
            /*if (filtered && tmp.getBorrowDate()[1] != null) {
                //to select only non returned books
                continue;
            }*/
            /*tmpPaymnt = 10 * tmp.borrowTime();
            int bTime = tmp.borrowTime();
            String date = tmp.getBorrowDate()[0].toString().replace(" 00:00:00 ACDT ", " ");
            data.add(new String[]{tmp.getID(), tmp.getISBN(), tmp.getStudentID(), date, Integer.toString(bTime), Integer.toString(tmpPaymnt)});*/
        }
        for (String[] i: data){
            dtm.addRow(i);
        }
        return dtm;
    }
}
