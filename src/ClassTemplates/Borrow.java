package ClassTemplates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Borrow {
    private String ID;
    private String studentID;
    private String ISBN;
    private Date[] borrowDate = new Date[2];//0:stat borrow 1 returned date will be in format yyyy-mm-dd default for 1 pos = null
    
    
    public Borrow (String ID, String studentID, String ISBN, String borrowDate){
        this.ID = ID;
        this.studentID = studentID;
        this.ISBN = ISBN; //string to long
        this.setDate(borrowDate);       
    }
    
    private void setDate(String dob){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.borrowDate[0] = sdf.parse(dob.split(",")[0]);
            this.borrowDate[1] = sdf.parse(dob.split(",")[1]);
        } catch (ParseException ex) {
            this.borrowDate[1] =null;
            //Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error parsing the date[1] prop for "+this.ID);
        }
    }
    
    public Date[] getBorrowDate() {
        return borrowDate;
    }

    public String getID() {
        return ID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getISBN() {
        return ISBN;
    }
    
    public int borrowTime () {
        //return int of days of the borrow
        if (borrowDate[1] != null){
            return (int) ((borrowDate[1].getTime() - borrowDate[0].getTime())/(1000 * 60 * 60 * 24L));
        }
        return (int) ((System.currentTimeMillis() - borrowDate[0].getTime())/(1000 * 60 * 60 * 24L));
    }
    
    public int returnBook (){
        //set a borrowed book to retourned
        //returns an int that represents the number of days the loand was
        borrowDate[1] = new Date(System.currentTimeMillis());
        return (int) ((borrowDate[1].getTime() - borrowDate[0].getTime())/(1000 * 60 * 60 * 24L));
    }    
}
