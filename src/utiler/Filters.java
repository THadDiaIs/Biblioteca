package utiler;

import ClassTemplates.Book;
import ClassTemplates.Borrow;
import ClassTemplates.Student;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author antonh
 */
public class Filters {

    public List<Borrow> filterBooks(String filter, List<Borrow> borrows, int expTime) {
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
                .filter(b -> b.getISBN().equals(code))
                .collect(Collectors.toList());
    }
}
