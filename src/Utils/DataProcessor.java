package Utils;

import Models.Book;
import Models.Borrow;
import Models.Student;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
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
    private DataSaver dtSvr = new DataSaver();

    public DataProcessor(List<Student> students, List<Borrow> borrows, List<Book> books, int daysPerBorrow, double dailyFee) {
        this.students = students;
        this.borrows = borrows;
        this.books = books;
        this.daysPerBorrow = daysPerBorrow;
        this.dailyFee = dailyFee;
        this.updateStatistics();
        this.fltr = new Filters();
    }

    public HashMap getStatistics() {
        this.updateStatistics();
        return this.statistics;
    }

    private void updateStatistics() {
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
        DefaultTableModel dtm = new DefaultTableModel(null, new String[]{
            "Code", "Book", "Student", "Borrowed Date", "Days", "Fees"
        });
        for (int i = 0; i < this.borrows.size(); i++) {
            Borrow tmp = this.borrows.get(i);
            Double tmpPaymnt;
            if (tmp.getBorrowDate()[1] != null) {
                //to select only non returned books
                continue;
            }
            tmpPaymnt = tmp.getBorrowDate()[1] == null ? this.dailyFee * tmp.borrowTime() : 0;
            List<Student> stuBorrw = fltr.getStudent(tmp.getStudentID(), students);
            List<Book> bkBorrw = fltr.getBook(tmp.getISBN(), books);
            data.add(new String[]{tmp.getID(),
                bkBorrw.getFirst().getName(),
                stuBorrw.getFirst().getName(),
                tmp.getBorrowDate()[0].toString(),
                Integer.toString(tmp.borrowTime()),
                tmpPaymnt.toString()});
        }
        for (String[] i : data) {
            dtm.addRow(i);
        }
        return dtm;
    }

    public TableModel borrowsTab(String filter) {
        List<String[]> data = new ArrayList<>();
        DefaultTableModel dtm = new DefaultTableModel(null, new String[]{
            "Code", "Book", "Student", "Start Date", "End Date", "Days", "Fees"
        });
        List<Borrow> fBorrows = fltr.filterBorrows(filter, borrows, this.daysPerBorrow);
        for (int i = 0; i < fBorrows.size(); i++) {
            Borrow tmp = fBorrows.get(i);
            Double tmpPaymnt;
            tmpPaymnt = tmp.getBorrowDate()[1] == null ? this.dailyFee * tmp.borrowTime() : 0;
            List<Student> stuBorrw = fltr.getStudent(tmp.getStudentID(), students);
            List<Book> bkBorrw = fltr.getBook(tmp.getISBN(), books);
            System.out.println(i + "  " + tmp.getID());
            data.add(new String[]{tmp.getID(),
                bkBorrw.getFirst().getName(),
                stuBorrw.getFirst().getName(),
                tmp.getBorrowDate()[0].toString(),
                tmp.getBorrowDate()[1] != null ? tmp.getBorrowDate()[1].toString() : "--",
                Integer.toString(tmp.borrowTime()),
                tmpPaymnt.toString()});
        }
        for (String[] i : data) {
            dtm.addRow(i);
        }
        return dtm;
    }

    public TableModel booksTab(String filterCriteria, char filterOn) {
        List<String[]> data = new ArrayList<>();
        DefaultTableModel dtm = new DefaultTableModel(null, new String[]{
            "Code", "Title", "Author", "Editorial", "Edition", "Stock", "Avaliable"
        });
        List<Book> filteredBooks = this.books;
        switch (filterOn) {
            case 't' -> {
                filteredBooks = fltr.filterBooksByTitle(filterCriteria, books);
            }
            case 'a' -> {
                filteredBooks = fltr.filterBooksByAuthor(filterCriteria, books);
            }
            case 'e' -> {
                filteredBooks = fltr.filterBooksByEditorial(filterCriteria, books);
            }
        }

        for (int i = 0; i < filteredBooks.size(); i++) {
            Book tmp = filteredBooks.get(i);
            data.add(new String[]{
                tmp.getCode(),
                tmp.getName(),
                tmp.getAuthor(),
                tmp.getEditorial(),
                tmp.getEdition(),
                String.valueOf(tmp.getStock()),
                String.valueOf(tmp.getStock() - fltr.getBorrowedBooks(tmp.getCode(), borrows))
            });
        }
        for (String[] i : data) {
            dtm.addRow(i);
        }
        return dtm;
    }

    public TableModel studentsTab(String filterCriteria, char filterOn, char filterBy) {
        List<String[]> data = new ArrayList<>();
        DefaultTableModel dtm = new DefaultTableModel(null, new String[]{
            "Code", "Name", "Grade", "Date of birth"
        });
        List<Student> filteredStudents = this.students;
        switch (filterBy) {
            case 'l' -> {//aLives -> actives
                filteredStudents = fltr.filterStudentsByStatus(this.students, this.borrows, true);
            }
            case 'i' -> {//inactived ones
                filteredStudents = fltr.filterStudentsByStatus(this.students, this.borrows, false);
            }
        }
        switch (filterOn) {
            case 'n' -> {
                filteredStudents = fltr.filterStudentsByName(filterCriteria, filteredStudents);
            }
            case 'c' -> {
                filteredStudents = fltr.filterStudentsByCode(filterCriteria, filteredStudents);
            }
            case 'd' -> {
                filteredStudents = fltr.filterStudentsByDeg(filterCriteria, filteredStudents);
            }
        }
        System.out.println("by -> "+filterBy);
        System.out.println("t-F -> " + filteredStudents.size());
        for (int i = 0; i < filteredStudents.size(); i++) {
            Student tmp = filteredStudents.get(i);
            data.add(new String[]{
                tmp.getID(),
                tmp.getName(),
                String.valueOf(tmp.getDegree()),
                tmp.getDob().toString()
            });
        }
        for (String[] i : data) {
            dtm.addRow(i);
        }
        return dtm;
    }

    public List<String[]> getBorrowDetails(String code) {
        List<String[]> data = new ArrayList<>();
        List<Borrow> borrow = fltr.getBorrow(code, borrows);
        if (!borrow.isEmpty()) {
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
                b.getCode(),
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

    public DefaultComboBoxModel[] newBorrowConf() {
        DefaultComboBoxModel[] models = {new DefaultComboBoxModel(), new DefaultComboBoxModel()};
        for (var book : this.books) {
            models[0].addElement(book.getName());
        }
        for (var stu : this.students) {
            models[1].addElement(stu.getName());
        }
        return models;
    }

    public String saveBorrows(int stuIdx, int bookIdx) {
        Student currStu = students.get(stuIdx);
        Book currBook = books.get(bookIdx);

        if (fltr.getActiveBorrowsOf(currStu.getID(), borrows) < 3) {
            if (currBook.getStock() - fltr.getBorrowedBooks(currBook.getCode(), borrows) > 0) {
                Random rnd = new Random();
                String seed = "BCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", newCode = "";
                //F8P2L6D9K 111224 format example
                for (int i = 0; i < 9; i++) {
                    newCode += seed.charAt(rnd.nextInt(seed.length()));
                }
                String dd = String.valueOf(LocalDate.now().getDayOfMonth());
                String mm = String.valueOf(LocalDate.now().getMonthValue());
                String yy = Integer.toString(LocalDate.now().getYear());
                dd = dd.length() < 2 ? 0 + dd : dd;
                mm = mm.length() < 2 ? 0 + mm : mm;
                newCode += dd+mm+yy.substring(2);
                
                try {
                    LocalDate today = LocalDate.now();
                    borrows.add(new Borrow(newCode, currStu.getID(), currBook.getCode(), today.toString() + ",5"));
                    dtSvr.persistBorrows(borrows);
                } catch (IOException ex) {
                    borrows.removeLast();
                    return "Error while saving data\ntry again later";
                }
            } else {
                return currBook.getName() + "\nno avaliable for now";
            }
        } else {
            return currStu.getName() + "\nhas reached the max-limit of\nallowed books";
        }
        return currStu.getName() + "\n" + currBook.getName()
                + "\ndaily fee: " + this.dailyFee
                + "\nregistred successfull";
    }

    public Double returnABook(String borrowCode) {
        Borrow toReturn = fltr.getBorrow(borrowCode, borrows).getFirst();
        int result = toReturn.returnBook();
        borrows.set(borrows.indexOf(toReturn), toReturn);
        try {
            dtSvr.persistBorrows(borrows);
        } catch (IOException ex) {
            Logger.getLogger(DataProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result * dailyFee;
    }

    public String saveBook(Book newBook) {
        if (!fltr.getBook(newBook.getCode(), books).isEmpty()) {
            return "The desired book alaready exist";
        } else if (newBook.getAuthor().isEmpty()
                || newBook.getCode().isEmpty()
                || newBook.getEdition().isEmpty()
                || newBook.getEditorial().isEmpty()
                || newBook.getName().isEmpty()) {
            return "Error invalid format\nPlease full fill all the fields";
        } else if (!fltr.filterBooksByTitle(newBook.getName(), books).isEmpty()
                && !fltr.filterBooksByAuthor(newBook.getAuthor(), books).isEmpty()
                && !fltr.filterBooksByEdition(newBook.getEdition(), books).isEmpty()) {
            return "A book with this title and the same author alaready exist";
        } else if (newBook.getStock() <= 0) {
            return "Please provide a valid number for 'stock'";
        } else {
            try {
                books.add(newBook);
                dtSvr.persistBooks(books);
            } catch (IOException ex) {
                books.removeLast();
                return "Fail, Not Saved\nTry again later!";
                //Logger.getLogger(DataProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "Registred sucessfull: \n" + newBook.getName();
    }

    public Student getDetailedStudent(String stuCode) {
        int[] loans = {fltr.getBorrowsOf(stuCode, borrows), fltr.getActiveBorrowsOf(stuCode, borrows)};
        Student stu = fltr.getStudent(stuCode, students).getFirst();
        stu.setActiveLoans(loans[1]);
        stu.setAllLoans(loans[0]);
        return stu;
    }
}
