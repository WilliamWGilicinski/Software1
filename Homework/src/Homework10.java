import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * MonteCarlo program built with methods
 *
 * @author William Gilicinski
 * @version February 25, 2021
 */
public final class Homework10 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework10() {
        // no code needed here
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber n3 = new NaturalNumber1L();
        n3.transferFrom(n1);
        n1.transferFrom(n2);
        n2.transferFrom(n3);
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {
        n.power(2);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */

        NaturalNumber n1 = new NaturalNumber1L(1);
        NaturalNumber n2 = new NaturalNumber1L(4);

        square(n2);
        output.println(n2);

        input.close();
        output.close();
    }
}
