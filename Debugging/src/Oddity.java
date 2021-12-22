import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Oddity {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Oddity() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

    /**
     * Main method. outputs whether the array's values are odd or even
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        final int[] values = { 8, -4, 3, 0, -5 };
        int i = 0;
        while (i < values.length) {
            int remainder = values[i] % 2;
            if (remainder == 1) {
                out.println("odd");
            } else {
                out.println("even");
            }
            i = i + 1;
        }
        out.close();
    }
}
