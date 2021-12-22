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
public final class Homework12 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework12() {
        // no code needed here
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        n.divideBy10();
        int count = 1;
        if (!n.isZero()) {
            count += numberOfDigits(n);
        }
        return count;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
//    private static int sumOfDigits(NaturalNumber n) {
//
//        int sum = 0;
//        if (!n.isZero()) {
//            sum += n.divideBy10();
//            sum += sumOfDigits(n);
//        }
//        return sum;
//    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits(NaturalNumber n) {

        NaturalNumber sum = new NaturalNumber1L();
        if (!n.isZero()) {
            int remainder = n.divideBy10();
            NaturalNumber nRem = new NaturalNumber1L(remainder);
            sum.add(nRem);
            sum.add(sumOfDigits(n));
        }

        return sum;

    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {

        int num = n.divideBy10();
        if (!n.isZero()) {
            num /= 2;
        } else {
            int nextTemp = n.divideBy10();
            if (nextTemp % 2 == 1) {
                num /= 2;
                num += 5;
                n.multiplyBy10(nextTemp);
                divideBy2(n);
            } else {
                num /= 2;
                n.multiplyBy10(nextTemp);
                divideBy2(n);
            }
        }
        n.multiplyBy10(num);
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        boolean valid = true;
        if (s.length() > 1) {
            if (s.charAt(0) != s.charAt(s.length() - 1)) {
                valid = false;
            } else {
                String temp = s.substring(1, s.length() - 1);
                valid = valid && isPalindrome(temp);
            }
        }
        return valid;
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

        NaturalNumber n1 = new NaturalNumber1L(2777865);
        NaturalNumber n2 = new NaturalNumber1L(44);

        System.out.println(isPalindrome("bob"));

        input.close();
        output.close();
    }
}
