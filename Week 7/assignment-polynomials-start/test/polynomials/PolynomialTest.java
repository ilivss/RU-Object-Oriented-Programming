/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomials;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class PolynomialTest {
    private     Polynomial p1 = new Polynomial("3.0 1 2 3");
    private	Polynomial p2 = new Polynomial("4 0 5 3");
    private	Polynomial p3 = new Polynomial("4 0 -5 3");
    private	Polynomial p4 = new Polynomial("6 1");
    
    public PolynomialTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Polynomial.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Polynomial instance = new Polynomial("3 0 0.5 1 -2 2 13.37 8");
        String expResult = "3+0.5x-2x^2+13.37x^8";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of plus method, of class Polynomial.
     */    
    @Test
    public void testPlusAgreaterthanB() {
        System.out.println("plus Exp A greater than B");
        Polynomial a = new Polynomial("1 2 1 3");
        Polynomial b = new Polynomial("1 1 1 2");
        a.plus(b);
        Polynomial expResult = new Polynomial ("1 1 2 2 1 3");
        assertEquals(expResult, a);
    }
    
    @Test
    public void testPlusBgreaterthanA() {
        System.out.println("plus: Exp B greater than A");
        Polynomial a = new Polynomial("1 1 1 2");
        Polynomial b = new Polynomial("1 2 1 3");
        a.plus(b);
        Polynomial expResult = new Polynomial ("1 1 2 2 1 3");
        assertEquals(expResult, a);
    }
    
    @Test
    public void plusEmpty(){
        Polynomial p2 = new Polynomial("");
        Polynomial p1 = new Polynomial("2 5 8 5");
        p1.plus(p2);
        assertEquals("2x^5+8x^5", p1.toString());
    }
    
    @Test
    public void plusLarge(){
        Polynomial p1 = new Polynomial("1 1 2 2 3 3 4 4 5 5");
        Polynomial p2 = new Polynomial("6 6 7 7 8 8 9 9 10 10");
        p1.plus(p2);
        assertEquals("1x+2x^2+3x^3+4x^4+5x^5+6x^6+7x^7+8x^8+9x^9+10x^10", p1.toString());
    }

    /**
     * Test of minus method, of class Polynomial.
     */
    @Test
    public void testMinus() {
        Polynomial p1 = new Polynomial("3 2 8 3");
        Polynomial p2 = new Polynomial("5 2 4 3");
        p1.minus(p2);
        assertEquals("-2x^2+4x^3", p1.toString());
    }

    /**
     * Test of times method, of class Polynomial.
     */
    @Test
    public void testTimes() {
        System.out.println("times");
        Polynomial b = null;
        Polynomial instance = new Polynomial();
        instance.times(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class Polynomial.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        Polynomial b = null;
        Polynomial instance = new Polynomial();
        instance.divide(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Polynomial.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object other_poly = null;
        Polynomial instance = new Polynomial();
        boolean expResult = false;
        boolean result = instance.equals(other_poly);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
