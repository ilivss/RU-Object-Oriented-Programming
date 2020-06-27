package students;

/**
 *
 * @author Sjaak Smetsers
 */
public class Week4Test {

    public static void main(String[] args) {
        Person sjaak = new Person( "Sjaak Smetsers", 827312 );
        System.out.println( sjaak );
        Student wout = new Student( "Wout van den Heuvel", 76022, Student.Study.CS, 414970 );
        System.out.println( wout );
    }
}
