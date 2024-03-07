/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassTemplates;

import java.util.Date;

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
   
   public Student(String ID, String name, int degree, Date dob){
      this.ID = ID;
      this.name = name;
      this.degree = degree;
      this.dob = dob;
   }
   
   public boolean verifyId(){
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
