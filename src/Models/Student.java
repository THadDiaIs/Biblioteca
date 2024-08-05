package Models;

import java.time.LocalDate;

/**
 *
 * @author matvey
 */
public class Student {

    //all this props can be final but this can be accessed
    private String ID;//carne
    private String name;
    private String degree;
    private LocalDate dob;
    private int allLoans, activeLoans;

    public int getAllLoans() {
        return allLoans;
    }

    public void setAllLoans(int allLoans) {
        this.allLoans = allLoans;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(int activeLoans) {
        this.activeLoans = activeLoans;
    }

    public Student(String ID, String name, String degree, String dob) {
        this.ID = ID;
        this.name = name;
        this.setDegree(degree);
        this.setDob(dob);
    }
    
    private void setDob(String dob){
        this.dob = LocalDate.parse(dob);
    }
    
    private void setDegree (String deg) {
        this.degree = deg;
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

    public String getDegree() {
        return degree;
    }

    public LocalDate getDob() {
        return dob;
    }

    @Override
    public String toString() {
        /*ID:24J9V8M2
        NAME:Emily Davis
        DEGREE:Psychology
        DOB:2003-10-05*/
        return "ID:" + ID + "\nNAME:" + name + "\nDEGREE:" + degree + "\nDOB:" + dob + "\n\n\n\n";
    }

}
