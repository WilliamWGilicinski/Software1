import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Homework 11
 *
 * @author William Gilicinski
 * @version February 26, 2021
 */
public final class Homework11 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework11() {
        // no code needed here
    }

    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int grow = n;

        if (p == 0) {
            grow = 1;
        } else {
            for (int i = 1; i < p; i++) {
                grow *= n;
            }
        }
        return grow;
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int low = 0;
        int high = n / 2; //Starts halfway

        //Repeats until n is between two numbers
        while (!(power(high, r) <= n && power(high + 1, r) > n)) {

            //Will cut the list in half if too high
            if (power(high, r) > n) {
                high /= 2;
                //Will Cut the list in half if too low
            } else {
                low = high;
                high *= 2;
            }
        }

        return high;
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

        output.println(root(47226, 5));

        input.close();
        output.close();
    }
}
