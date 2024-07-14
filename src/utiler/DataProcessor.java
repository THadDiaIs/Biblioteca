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
    private int daysPerBorrow = 0;
    private double dailyFee = 0.0;
    private HashMap statistics;
    private Filters fltr;

    public DataProcessor(List<Student> students, List<Borrow> borrows, List<Book> books, int daysPerBorrow, double dailyFee) {
        this.students = students;
        this.borrows = borrows;
        this.books = books;
        this.daysPerBorrow = daysPerBorrow;
        this.dailyFee = dailyFee;
        this.updateStatistics();
        this.fltr = new Filters();
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
                if (b.borrowTime() > this.daysPerBorrow) {
                    tmpExp++;
                }
            }
        }
        map.replace("borrowedBooks", tmp);
        map.replace("expiredBorr", tmpExp);
        this.statistics = map;
    }
    
    public TableModel overviewTab() {
        List<String[]> data = new ArrayList<>();
        DefaultTableModel dtm = new DefaultTableModel(null,new String[]{
            "Code", "Book", "Student", "Borrowed Date","Days", "To Pay"
        });
        for (int i = 0; i < this.borrows.size(); i++) {
            Borrow tmp = this.borrows.get(i);
            Double tmpPaymnt;
            if (tmp.getBorrowDate()[1] != null) {
                //to select only non returned books
                continue;
            }
            tmpPaymnt = tmp.getBorrowDate()[1] == null ? this.dailyFee * tmp.borrowTime() : 0;
            List<Student> stuBorrw = this.students.stream()
                    .filter(stu -> stu.getID().equalsIgnoreCase(tmp.getStudentID()))
                    .collect(Collectors.toList());
            List<Book> bkBorrw = this.books.stream()
                    .filter(b -> b.getISBN().equals(tmp.getISBN()))
                    .collect(Collectors.toList());
            data.add(new String[]{tmp.getID(),
                bkBorrw.getFirst().getName(),
                stuBorrw.getFirst().getName(),
                tmp.getBorrowDate()[0].toString(),
                Integer.toString(tmp.borrowTime()),
                tmpPaymnt.toString()});
        }
        for (String[] i: data){
            dtm.addRow(i);
        }
        return dtm;
    }
    
    public TableModel borrowsTab(String filter) {
        List<String[]> data = new ArrayList<>();
        DefaultTableModel dtm = new DefaultTableModel(null,new String[]{
            "Code", "Book", "Student", "Borrowed Date","Days", "To Pay"
        });
        List<Borrow> fBorrows = fltr.filterBooks(filter, borrows, this.daysPerBorrow);
        for (int i = 0; i < fBorrows.size(); i++) {
            Borrow tmp = fBorrows.get(i);
            Double tmpPaymnt;
            tmpPaymnt = tmp.getBorrowDate()[1] == null ? this.dailyFee * tmp.borrowTime() : 0;
            List<Student> stuBorrw = this.students.stream()
                    .filter(stu -> stu.getID().equalsIgnoreCase(tmp.getStudentID()))
                    .collect(Collectors.toList());
            List<Book> bkBorrw = this.books.stream()
                    .filter(b -> b.getISBN().equals(tmp.getISBN()))
                    .collect(Collectors.toList());
            data.add(new String[]{tmp.getID(),
                bkBorrw.getFirst().getName(),
                stuBorrw.getFirst().getName(),
                tmp.getBorrowDate()[0].toString(),
                Integer.toString(tmp.borrowTime()),
                tmpPaymnt.toString()});
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
    
    public List<String[]> getBorrowDetails(String code){
        List<String[]> data = new ArrayList<>();
        List<Borrow> borrow = fltr.getBorrow(code, borrows);
        if (!borrow.isEmpty()){
            Borrow br = borrow.getFirst();
            Student stu = fltr.getStudent(br.getStudentID(), students).getFirst();
            Book b = fltr.getBook(br.getISBN(), books).getFirst();
            /*System.out.println(br.getID() +" "+ br.getISBN() +" "+ br.getStudentID());
            System.out.println(stu.getID()+" "+stu.getName()+" "+stu.getDob());
            System.out.println(b.getISBN()+" "+b.getName()+" "+b.getAuthor()+"\n");*/
            data.add(new String[]{
                stu.getID(),
                stu.getName()});
            data.add(new String[]{
                b.getISBN(), 
                b.getName(), 
                b.getAuthor()});
            data.add(new String[]{
                br.getBorrowDate()[0].toString(), 
                br.getBorrowDate()[1] == null ? "still" : br.getBorrowDate()[1].toString(), 
                Integer.toString(br.borrowTime()), 
                String.valueOf(this.dailyFee * br.borrowTime())});
        } else {
            System.out.println("no data found");
        }
        return data;
    }
    
    
}
