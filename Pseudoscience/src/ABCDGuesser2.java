import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double dbl = 0;
        boolean valid = false;

        while (!valid) {
            out.println("Enter a positive real number");
            String input = in.nextLine();

            if (FormatChecker.canParseDouble(input)) {
                dbl = Double.parseDouble(input);
                if (dbl > 0) {
                    valid = true;
                }
            }
        }
        return dbl;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double dbl = 0;
        boolean valid = false;

        while (!valid) {
            out.println("Enter a positive real number not equal to one");
            String input = in.nextLine();

            if (FormatChecker.canParseDouble(input)) {
                dbl = Double.parseDouble(input);
                if (dbl != 1.0 && dbl > 0) {
                    valid = true;
                }
            }
        }
        return dbl;
    }

    /**
     * Gets the users mew value
     *
     * @param in
     * @param out
     * @return the user's entered mew value
     */
    private static double getMew(SimpleReader in, SimpleWriter out) {
        double dbl = 0;
        out.println("What constant μ should be approximated");
        dbl = getPositiveDouble(in, out);
        return dbl;
    }

    /**
     * Gets the users personal numbers
     *
     * @param in
     * @param out
     * @return the users personal numbers
     */
    private static double[] getPersonalNums(SimpleReader in, SimpleWriter out) {
        double[] dbls = new double[4];
        char[] vars = { 'w', 'x', 'y', 'z' };
        for (int i = 0; i < 4; i++) {
            out.println("Enter your personal number " + vars[i]);
            dbls[i] = getPositiveDoubleNotOne(in, out);
        }
        return dbls;
    }

    /**
     * Indexes through all possible combinations of the de Jager formula to be
     * able to find the value most like mew
     *
     * @param mew
     * @param dbls
     * @return The calculated mew value
     */
    private static double[] computeMew(double mew, double[] dbls) {
        double[] vals = { -5, -4, -3, -2, -1, -0.5, -0.33333, -0.25, 0, 0.25,
                0.33333, 0.5, 1, 2, 3, 4, 5 };
        double mewDif = Double.MAX_VALUE;
        double bA = 0; //bA - bD are the best values for the formula
        double bB = 0;
        double bC = 0;
        double bD = 0;
        double product = 0;
        double bestProduct = 0;
        for (int a = 0; a < 17; a++) { //The while loops index through all combinations
            for (int b = 0; b < 17; b++) {
                for (int c = 0; c < 17; c++) {
                    for (int d = 0; d < 17; d++) {
                        product = Math.pow(dbls[0], vals[a])
                                * Math.pow(dbls[1], vals[b])
                                * Math.pow(dbls[2], vals[c])
                                * Math.pow(dbls[3], vals[d]);
                        if (Math.abs(mew - product) < mewDif) {
                            mewDif = Math.abs(mew - product);
                            bestProduct = product;
                            bA = vals[a];
                            bB = vals[b];
                            bC = vals[c];
                            bD = vals[d];
                        }
                    }
                }
            }
        }
        double[] test = { bestProduct, bA, bB, bC, bD };
        return test;
    }

    /**
     * Calculates the relative error
     *
     * @param mew
     * @param calcMew
     * @return the relative error of the real mew and calculated mew
     */
    private static double relError(double mew, double calcMew) {
        double relError = (Math.abs(calcMew - mew) / mew) * 100;
        return relError;
    }

    /**
     * Prints the final results, needed another private static method
     *
     * @param values
     * @param out
     * @param mew
     */
    private static void message(double[] values, SimpleWriter out, double mew) {
        out.print("The approximated mew is: ");
        out.print(values[0], 2, false);
        out.print("\nThe relative error is: ");
        out.print(relError(mew, values[0]), 2, false);
        out.println("%\nThe a value is: " + values[1]);
        out.println("The b value is: " + values[2]);
        out.println("The c value is: " + values[3]);
        out.println("The d value is: " + values[4]);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        double mew = getMew(in, out);
        double[] personalNums = getPersonalNums(in, out);
        double values[] = computeMew(mew, personalNums);
        message(values, out, mew);

        in.close();
        out.close();
    }

}
