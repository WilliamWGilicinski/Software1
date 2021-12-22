import java.util.Random;

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
public final class Homework5 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Homework5() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

    public static boolean sorted(int x, int y, int z) {
        boolean sorted = false;

        if (x < y) {
            if (y < z) {
                sorted = true;
            }
        }

        return sorted;

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
        Random rnd = new Random();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        myMethod();
        /*
         * Close input and output streams
         */

        //a

        int sum = 0;

        int a;
        int b;

        System.out.println(sorted(1, 2, 3));

        in.close();
        out.close();
    }
}