package Utils;

import Models.Student;
import Models.Borrow;
import Models.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author antonh
 */
public class DataLoader {
//    file format
    //code; //3\d - 3\w
    //name;
    //author;
    //editorial;
    //edition;

//    all the: read from filesystem methods, throws the  expection to function who implements it
    private Scanner FileLoader(File file, String fileName) throws InterruptedException, InvocationTargetException {
        final File[] selectedFile = {null};
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            SwingUtilities.invokeAndWait(() -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select the " + fileName + " file");
                fileChooser.setCurrentDirectory(new File("."));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile[0] = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile[0].getAbsolutePath());
                } else {
                    System.out.println(fileName + " File selection cancelled.");
                    System.exit(0);
                }
            });
            try {
                scan = new Scanner(selectedFile[0]);
            } catch (FileNotFoundException ex1) {
                System.out.println("Your file still does not exist");
                //Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex1);
                System.exit(0);
            }
        }
        return scan;
    }

    private List<String> FileLoader(File file) throws FileNotFoundException {
//        read a file and return an list of strings of the entire file
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        return br.lines().toList();
    }

    public ArrayList<Book> BookDBLoader(File file) {
//        cpnvert the entire file to a book array
        ArrayList<Book> books = new ArrayList<>();
        String[] tmp = new String[6];
        Scanner scanner = null;

        try {
            scanner = FileLoader(file, "Books");
        } catch (InterruptedException | InvocationTargetException ex) {
            System.out.println("Some error selecting file ocurrs it appears to be interrupted");
            //Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (scanner.hasNext()) {
            String firstLine = scanner.nextLine();
            if (firstLine.length() > 1) {
                tmp[0] = firstLine.split(":").length > 1 ? firstLine.split(":")[1].strip() : "DataEntryError";
                for (int i = 1; i < tmp.length; i++) {
                    String[] currLine = scanner.nextLine().split(":");
                    tmp[i] = currLine.length > 1 ? currLine[1].strip() : "DataEntryError";
                }
                books.add(new Book(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5]));
            }
        }
        return books;
    }

//    private String[] scanIterator(int arrLength, Scanner scann){
//        String[][] tmp = new String[][arrLength];
//        
//        while (scann.hasNext()) {
//            String firstLine = scann.nextLine();
//            if (firstLine.length() > 1) {
//                tmp[0] = firstLine.split(":").length > 1 ? firstLine.split(":")[1] : "DataEntryError";
//                for (int i = 1; i < 5; i++) {
////                    not neccesary for but added to make sure we add the 5 props of the book obj
//                    String[] currLine = scann.nextLine().split(":");
//                    tmp[i] = currLine.length > 1 ? currLine[1] : "DataEntryError";
//                }
//                students.add(new Student(tmp[0], tmp[1], tmp[2], tmp[3]));
//            }
//        }
//        
//        return ;
//    }
    public ArrayList<Student> StudentDBLoader(File file) {
//        cpnvert the entire file to a book array
        String[] tmp = new String[4];
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = null;

        try {
            scanner = FileLoader(file, "Students");
        } catch (InterruptedException | InvocationTargetException ex) {
            System.out.println("Some error selecting file ocurrs it appears to be interrupted");
            //Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (scanner.hasNext()) {
            String firstLine = scanner.nextLine();
            if (firstLine.length() > 1) {
                tmp[0] = firstLine.split(":").length > 1 ? firstLine.split(":")[1] : "DataEntryError";
                for (int i = 1; i < tmp.length; i++) {
                    String[] currLine = scanner.nextLine().split(":");
                    tmp[i] = currLine.length > 1 ? currLine[1] : "DataEntryError";
                }
                students.add(new Student(tmp[0], tmp[1], tmp[2], tmp[3]));
            }
        }
        return students;
    }

    public ArrayList<Borrow> BorrowDBLoader(File file) {
//        cpnvert the entire file to a book array
        String[] tmp = new String[4];
        ArrayList<Borrow> borrows = new ArrayList<>();
        Scanner scanner = null;

        try {
            scanner = FileLoader(file, "Borrow");
        } catch (InterruptedException | InvocationTargetException ex) {
            System.out.println("Some error selecting file ocurrs it appears to be interrupted");
            //Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (scanner.hasNext()) {
            String firstLine = scanner.nextLine();
            if (firstLine.length() > 1) {
                tmp[0] = firstLine.split(":").length > 1 ? firstLine.split(":")[1] : "DataEntryError";
                for (int i = 1; i < tmp.length; i++) {
                    String[] currLine = scanner.nextLine().split(":");
                    tmp[i] = currLine.length > 1 ? currLine[1].strip() : "DataEntryError";
                }
                borrows.add(new Borrow(tmp[0], tmp[1], tmp[2], tmp[3]));
            }
        }
        return borrows;
    }
    
    
}
