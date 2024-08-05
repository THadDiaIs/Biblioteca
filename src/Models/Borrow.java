package Models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Borrow {

    private String ID;
    private String studentID;
    private String ISBN;
    private LocalDate[] borrowDate = new LocalDate[2];//0:stat borrow 1 returned date will be in format yyyy-mm-dd default for 1 pos = null

    public Borrow(String ID, String studentID, String ISBN, String borrowDate) {
        this.ID = ID;
        this.studentID = studentID;
        this.ISBN = ISBN; //string to long
        this.setDate(borrowDate);
    }

    private void setDate(String dob) {
        String[] bDat1 = dob.split(",")[0].split("-");
        String[] bDat2 = dob.split(",")[1].split("-");
        borrowDate[0] = LocalDate.of(Integer.parseInt(bDat1[0]), Integer.parseInt(bDat1[1]), Integer.parseInt(bDat1[2]));
        if (bDat2.length == 3) {
            borrowDate[1] = LocalDate.of(Integer.parseInt(bDat2[0]), Integer.parseInt(bDat2[1]), Integer.parseInt(bDat2[2]));
        } else {
            this.borrowDate[1] = null;
            System.out.println("error parsing the date[1] prop for " + this.ID);
        }
    }

    public LocalDate[] getBorrowDate() {
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

    public int borrowTime() {
        //return int of days of the borrow
        if (borrowDate[1] != null) {
            return (int) (Math.abs(ChronoUnit.DAYS.between(borrowDate[0], borrowDate[1])));
            //return (int) ((borrowDate[1].getTime() - borrowDate[0].getTime())/(1000 * 60 * 60 * 24L));
        }
        //return (int) ((System.currentTimeMillis() - borrowDate[0].getTime())/(1000 * 60 * 60 * 24L));
        return (int) (Math.abs(ChronoUnit.DAYS.between(borrowDate[0], LocalDate.now())));
    }

    public int returnBook() {
        //set a borrowed book to retourned
        //returns an int that represents the number of days the loand was
        borrowDate[1] = LocalDate.now();
        return this.borrowTime();
    }

    @Override
    public String toString() {
        String endDate = borrowDate[1] == null ? "7" : borrowDate[1].toString();
        return "ID:" + ID + "\nSTD_ID:" + studentID + "\nISBN:" + ISBN + "\nB_DATE:" + borrowDate[0].toString()+","+endDate;
    }

}
