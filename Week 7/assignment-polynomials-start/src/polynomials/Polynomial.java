package polynomials;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * A skeleton class for representing Polynomials
 */
public class Polynomial {

	/**
	 * A polynomial is a sequence of terms here kept in an List
	 */
	private List<Term> terms;

	/**
	 * A constructor for creating the zero Polynomial zero is presented as an empty
	 * list of terms and not as a single term with 0 as a coefficient
	 */
	public Polynomial() {
		terms = new LinkedList<>();
	}

	/**
	 * A Constructor creating a polynomial from the argument string.
	 *
	 * @param s a String representing a list of terms which is converted to a
	 *          scanner and passed to scanTerm for reading each individual term
	 */
	public Polynomial(String s) {
		terms = new LinkedList<>();
		Scanner scan = new Scanner(s);

		for (Term t = Term.scanTerm(scan); t != null; t = Term.scanTerm(scan)) {
			terms.add(t);
		}
	}

	/**
	 * The copy constructor for making a deep copy
	 *
	 * @param p the copied polynomial
	 *
	 */
	public Polynomial(Polynomial p) {
		terms = new LinkedList<>();
		for (Term t : p.terms) {
			terms.add(new Term(t));
		}
	}

	/**
	 * A straightforward conversion of a Polynomial into a string based on the
	 * toString for terms
	 *
	 * @return a readable string representation of this
	 */
	@Override
	public String toString() {
            StringBuilder out = new StringBuilder();
            ListIterator<Term> lit = terms.listIterator();
            
            while(lit.hasNext()) {
                Term current = lit.next();
                
                if (current.getCoef() >= 0 && lit.previousIndex() != 0) {
                    out.append("+");
                }
                
                out.append(current.toString());
            }
            
            
            return out.toString();
	}

	public void plus(Polynomial b) {
            ListIterator<Term>  lita = terms.listIterator(),
                                litb = b.terms.listIterator();
            
            while (lita.hasNext() && litb.hasNext()) {
                Term ta = lita.next(),
                     tb = litb.next();
                
                if (ta.getExp() == tb.getExp()){
                    ta.plus(tb);
                    
                    if (ta.getCoef() == 0) {
                        lita.remove();
                    }
                } else if (ta.getExp() > tb.getExp()) {
                        lita.previous();
                        lita.add(tb);                            
                } else {
                    litb.previous();
                }
            }
            
            // Add remaining values to a.
            while (litb.hasNext()) {
                Term tb = litb.next();
                lita.add(tb);
            }
	}

	public void minus(Polynomial b) {
            ListIterator<Term>  lita = terms.listIterator(),
                                litb = b.terms.listIterator();
            
            while (lita.hasNext() && litb.hasNext()) {
                Term ta = lita.next(),
                     tb = litb.next();
                
                if (ta.getExp() == tb.getExp()){
                    ta.minus(tb);
                    
                    if (ta.getCoef() == 0) {
                        lita.remove();
                    }
                } else if (ta.getExp() > tb.getExp()) {
                        lita.previous();
                        
                        tb.setCoef(tb.getCoef()*-1);
                        lita.add(tb);
                        
                } else {
                    litb.previous();
                }
            }
            
            // Add remaining values to a.
            while (litb.hasNext()) {
                Term tb = litb.next();
                lita.add(tb);
            }
            
            
	}

	public void times(Polynomial b) {
	}

	public void divide(Polynomial b) {
	}

	@Override
	public boolean equals(Object other_poly) {
		if (other_poly == null || getClass() != other_poly.getClass()) {
			return false;
		}
		else {
			Polynomial p1 = (Polynomial) other_poly;
			return terms.equals(p1.terms);
		}
	}

}
