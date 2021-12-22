import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class CoinChange2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private CoinChange2() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        out.println("Enter the amount of change");
        int change = in.nextInteger();
        int[] coinValue = new int[] { 100, 50, 25, 10, 5, 1 };
        int[] coinCount = new int[6];

        for (int i = 0; i < coinValue.length; i++) {
            coinCount[i] = change / coinValue[i];
            change = change % coinValue[i];
        }

        out.println("Dollar Coins: " + coinCount[0]);
        out.println("Half-Dollars: " + coinCount[1]);
        out.println("Quarters:     " + coinCount[2]);
        out.println("Dimes:        " + coinCount[3]);
        out.println("Nickels:      " + coinCount[4]);
        out.println("Pennies:      " + coinCount[5]);

        in.close();
        out.close();
    }

}
