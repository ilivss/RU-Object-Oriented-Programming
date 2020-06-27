package students;

/**
 *
 * @author Sjaak Smetsers
 */
public class Person {

    private String  myName;
    private int     mySSN;

    public Person( String name, int ssn ) {
        this.myName = name;
        this.mySSN  = ssn;
    }

    @Override
    public String toString() {
        return String.format( "%s, SSN:%d" , myName, mySSN );
    }
}
