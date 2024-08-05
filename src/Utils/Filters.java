package Utils;

import Models.Book;
import Models.Borrow;
import Models.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 *
 * @author antonh
 */
public class Filters {

    public List<Borrow> filterBorrows(String filter, List<Borrow> borrows, int expTime) {
        switch (filter) {
            case "ac"-> {
                return borrows.stream().filter( b -> b.getBorrowDate()[1] == null).collect(Collectors.toList());
            }
            case "r" -> {
                return borrows.stream().filter( b -> b.getBorrowDate()[1] != null).collect(Collectors.toList());
            }
            case "et" -> {
                return borrows.stream().filter( b -> b.getBorrowDate()[1] == null && b.borrowTime() == expTime).collect(Collectors.toList());
            }
            case "e" -> {
                return borrows.stream().filter( b -> b.getBorrowDate()[1] == null && b.borrowTime() > expTime).collect(Collectors.toList());
            }
        }
        return borrows;
    }
    
    public List<Borrow> getBorrow(String code, List<Borrow> borrows){
        return borrows
                .stream()
                .filter(b -> b.getID().equals(code))
                .collect(Collectors.toList());
    }
    
    public List<Student> getStudent(String code, List<Student> students){
        return students
                .stream()
                .filter(b -> b.getID().equals(code))
                .collect(Collectors.toList());
    }
    
    public List<Book> getBook(String code, List<Book> books){
        return books
                .stream()
                .filter(b -> b.getCode().equals(code))
                .collect(Collectors.toList());
    }
    
    public int getActiveBorrowsOf(String stuCode, List<Borrow> allBorrows){
        return allBorrows.stream()
                .filter( br -> br.getBorrowDate()[1] == null && br.getStudentID().equals(stuCode))
                .collect(Collectors.toList()).size();
    }
    
    public int getBorrowsOf(String stuCode, List<Borrow> allBorrows){
        return allBorrows.stream()
                .filter( br ->  br.getStudentID().equals(stuCode))
                .collect(Collectors.toList()).size();
    }
    
    public int getBorrowedBooks(String code, List<Borrow> allBorrows){
        return allBorrows.stream()
                .filter( br -> br.getBorrowDate()[1] == null && br.getISBN().equals(code))
                .collect(Collectors.toList()).size();
    }
    
    public List<Book> filterBooksByTitle(String filter, List<Book> books){
        return books
                .stream()
                .filter(b -> b.getName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
    public List<Book> filterBooksByAuthor(String filter, List<Book> books){
        return books
                .stream()
                .filter(b -> b.getAuthor().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
    public List<Book> filterBooksByEditorial(String filter, List<Book> books){
        return books
                .stream()
                .filter(b -> b.getEditorial().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
    public List<Book> filterBooksByEdition(String filter, List<Book> books){
        return books
                .stream()
                .filter(b -> b.getEdition().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
    public List<Student> filterStudentsByName(String filter, List<Student> students){
        return students.stream()
                .filter(s -> s.getName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
    public List<Student> filterStudentsByCode(String filter, List<Student> students){
        return students.stream()
                .filter(s -> s.getID().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
    public List<Student> filterStudentsByDeg(String filter, List<Student> students){
        return students.stream()
                .filter(s -> s.getDegree().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
    public List<Student> filterStudentsByStatus(List<Student> students, List<Borrow> borrows, boolean actives) {
        //for actives-inactives
        List<String> stuCodes = new ArrayList<>();
        List<Student> stuActives = new ArrayList<>();
        List<Student> inActives = students.reversed();
        for (int i = 0; i < borrows.size(); i++) {
            if (stuCodes.indexOf(borrows.get(i).getStudentID()) == -1 && borrows.get(i).getBorrowDate()[1] == null) {
                stuCodes.add(borrows.get(i).getStudentID());
                
                stuActives.add(this.getStudent(borrows.get(i).getStudentID(), students).getFirst());
                inActives = inActives.stream()
                        .filter((var stu) -> !stu.getID().equals(stuActives.getLast().getID()))
                        .collect(Collectors.toList());
                
            }
        }
        if (actives) {
            return stuActives;
        }
        return inActives;
    }
}
