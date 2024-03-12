/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassTemplates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matvey
 */
public class Student {

    //all this props can be final but this can be accessed
    private String ID;//carne
    private String name;
    private int degree;
    private Date dob;

    public Student(String ID, String name, String degree, String dob) {
        this.ID = ID;
        this.name = name;
        //this.degree = degree; transform to integer
        this.setDob(dob);
    }
    
    private void setDob(String dob){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dob = sdf.parse(dob);
        } catch (ParseException ex) {
            this.dob = new Date(System.currentTimeMillis());
            //Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error ocurred with creating the date prop");
        }
    }
    
    public boolean verifyId() {
        return this.ID.equals("something");
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getDegree() {
        return degree;
    }

    public Date getDob() {
        return dob;
    }

}
