package students;

/**
 *
 * @author Sjaak Smetsers
 */
public class Student extends Person {

    public static enum Study {
        CS, AI, MA
    };

    private Study myStudy;
    private int   myStudentNumber;

    public Student( String name, int ssn, Study study, int studentNr ) {
        super( name, ssn );
        this.myStudy         = study;
        this.myStudentNumber = studentNr;
    }

    public String toString() {
        return String.format( "%s,%s,%d", super.toString(), myStudy, myStudentNumber );
    }
}
