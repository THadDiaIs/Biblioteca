/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassTemplates;

import java.util.Date;

/**
 *
 * @author arfcd
 */
public class Borrow {
    private String ID;
    private String studentID;
    private long ISBN;
    private Date[] borrowDate = new Date[2];//0:stat borrow 1 returned date will be in format yyyy-mm-dd
    
    
    public Borrow (String ID, String studentID, long ISBN, Date borrowDate){
        this.ID = ID;
        this.studentID = studentID;
        this.ISBN = ISBN;
        this.borrowDate[0] = borrowDate;        
    }

    public Date[] getBorrowDate() {
        return borrowDate;
    }
    
    public int borrowTime () {
        //return int of days of the borrow
        if (borrowDate[1] != null){
            return (int) ((borrowDate[1].getTime() - borrowDate[0].getTime())/(1000 * 60 * 60 * 24L));
        }
        return (int) ((System.currentTimeMillis() - borrowDate[0].getTime())/(1000 * 60 * 60 * 24L));
    }
    
    public int returnedBook (){
        borrowDate[1] = new Date(System.currentTimeMillis());
        return (int) ((borrowDate[1].getTime() - borrowDate[0].getTime())/(1000 * 60 * 60 * 24L));
    }
//    cretae the function to set whe the book is returned
}
