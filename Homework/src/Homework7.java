import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Homework7 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Homework7() {
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
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        int[] a = { 3, 2, 1 };
        boolean isOrdered = true;
        int count = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                count++;
            }
        }
        if (count == (a.length - 1)) {
            isOrdered = false;
        }

        System.out.println(isOrdered);

        in.close();
        out.close();
    }

}
