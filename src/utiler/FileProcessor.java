/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utiler;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
//import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antonh
 */
public class FileProcessor {

    //code; //3\d - 3\w
    //name;
    //author;
    //editorial;
    //edition;
    private File FileLoader() throws InterruptedException, InvocationTargetException {
        final File[] selectedFile = {null};
        //SwingUtilities.invokeLater(() -> {
        SwingUtilities.invokeAndWait(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile[0].getAbsolutePath());
            } else {
                System.out.println("File selection cancelled.");
            }
        });
        return selectedFile[0];
    }

    
    private List<String> FileLoader(File file) throws FileNotFoundException{
//        read a file and return an list of strings of the entire file
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        return br.lines().toList();
    }

    public ArrayList<String> FileProcessor(File file) {
        //cpnvert the entire file to a string array
        ArrayList<String> processed = new ArrayList<>();

        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            try {
                System.out.println("provided file not found, prompting for a new one");
                //Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex);
                scan = new Scanner(this.FileLoader());
            } catch (InterruptedException | InvocationTargetException ex1) {
                //Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("invocation error or interrupted");
            } catch (FileNotFoundException ex1) {
                System.out.println("YOur file still does not exist");
                //Logger.getLogger(FileProcessor.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            while (scan.hasNext()) {
                processed.add(scan.nextLine());
            }

        }
        System.out.println(processed);
        return processed;
    }
}