/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

/**
 *
 * @author s4707079 & s1037202
 */
public class Student {
    private String firstname;
    private String lastname;
    private int snumber;
    
    public Student (String name, String lastname, int studentnumber) {
        this.firstname = name;
        this.lastname = lastname;
        this.snumber = studentnumber;
    }
    
    public void changeName (String name, String lastname) {
        this.firstname = name;
        this.lastname = lastname;
    }
    
    // Return student number
    public int getSnumber () {
        return this.snumber;
    }
    
    @Override
    public String toString(){
        return firstname + " " + lastname + ", s" + snumber;
    }
}