package Utils;

import Models.Book;
import Models.Borrow;
import Models.Student;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ma4rt
 */
public class DataSaver {
    
    private File testFile = new File("src/Data/test.diadb");
    private File borrowsFile = new File("src/Data/borrows.diadb");
    private File booksFile = new File("src/Data/books.diadb");
    
    public void persistBooks(List<Book> booksToSave) throws IOException{
        FileWriter fw = new FileWriter(booksFile);
        System.out.println(booksToSave.size()+" list.length");
        for (Book cr : booksToSave){
            fw.write(cr.toString()+"\n\n\n\n");
        }
        fw.close();
    }
    
    public void persistBorrows(List<Borrow> borrowsToSave) throws IOException{
        FileWriter fw = new FileWriter(borrowsFile);
        System.out.println(borrowsToSave.size()+" list.length");
        for (Borrow br : borrowsToSave){
            fw.write(br.toString()+"\n\n\n\n");
        }
        fw.close();
    }
    
    public void persistStudents(List<Student> studentsToSave) throws IOException{
        FileWriter fw = new FileWriter(borrowsFile);
        System.out.println(studentsToSave.size()+" list.length");
        for (Student br : studentsToSave){
            fw.write(br.toString()+"\n\n\n\n");
        }
        fw.close();
    }
}
