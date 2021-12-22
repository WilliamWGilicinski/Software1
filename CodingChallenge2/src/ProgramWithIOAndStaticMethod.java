import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIOAndStaticMethod {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    private static int secondLargest(int[] a) {

        int secondLargest = a[0];
        int largest = a[1];

        if (a[0] > a[1]) {
            largest = a[0];
            secondLargest = a[1];
        }

        if (a.length > 2) {
            for (int i = 2; i < a.length; i++) {
                if (a[i] > largest && a[i] > secondLargest) {
                    largest = a[i];
                } else if (a[i] > secondLargest) {
                    secondLargest = a[i];
                }
            }
        }
        return secondLargest;

    }

    public static NaturalNumber copy(NaturalNumber src) {

        int digit = src.divideBy10();

        NaturalNumber temp = new NaturalNumber2();

        if (!src.isZero()) {
            temp = copy(src);
        }

        src.multiplyBy10(digit);
        temp.multiplyBy10(digit);

        return temp;
    }

    private static String stringTree(XMLTree exp) {

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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        int[] a = { 1, 5, -7, 4, 1 };
        int[] b = { 1, 5, -7, 4, 5 };
        System.out.println(secondLargest(a));
        System.out.println(secondLargest(b));
        NaturalNumber c = new NaturalNumber2(4465);
        System.out.println(copy(c));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
