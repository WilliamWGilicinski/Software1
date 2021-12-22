import java.util.Arrays;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;

/**
 * MonteCarlo program built with methods
 *
 * @author William Gilicinski
 * @version February 25, 2021
 */
public final class Homework13 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework13() {
        // no code needed here
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        int remainder = n.divideBy10();
        NaturalNumber product = new NaturalNumber1L(remainder);
        if (!n.isZero()) {
            product.multiply(productOfDigits1(n));
        }
        return product;

    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        NaturalNumber n2 = new NaturalNumber1L(n);
        int remainder = n2.divideBy10();
        NaturalNumber product = new NaturalNumber1L(remainder);
        if (!n.isZero()) {
            product.multiply(productOfDigits1(n2));
        }
        return product;
    }

    /**
     * Reports the value of {@code n} as an {@code int}, when {@code n} is small
     * enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        NaturalNumber max = new NaturalNumber1L(Integer.MAX_VALUE);
        String printInt = "";
        if (n.compareTo(max) <= 0) {
            int remainder = n.divideBy10();
            if (!n.isZero()) {
                printInt = toInt(n) + "" + remainder;
            }
        }
        return Integer.parseInt(printInt);
    }

    /**
     * Reports whether the given tag appears in the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return true if the given tag appears in the given {@code XMLTree}, false
     *         otherwise
     * @ensures <pre>
     * findTag =
     *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]
     * </pre>
     */
    private static boolean findTag(XMLTree xml, String tag) {

        boolean found = false;
        if (xml.isTag()) {
            for (int i = 0; i < xml.numberOfChildren(); i++) {
                found = findTag(xml.child(i), tag);
            }
            if (xml.label().equals(tag)) {
                found = true;
            }
        }
        return found;
    }

    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */

        /*
         * Initialize an array of NaturalNumbers with values 1 through 5.
         */
        NaturalNumber[] array = new NaturalNumber[5];
        System.out.println(Arrays.toString(array));
        NaturalNumber count = new NaturalNumber1L(1);
        for (int i = 0; i < array.length; i++) {
            array[i] = count;
            count.increment();
        }

        System.out.println(Arrays.toString(array));
        System.out.println(count);

        input.close();
        output.close();
    }
}
