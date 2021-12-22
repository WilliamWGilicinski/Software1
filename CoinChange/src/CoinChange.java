import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class CoinChange {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private CoinChange() {
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
        int dollarCoins = 0;
        int halfDollar = 0;
        int quarters = 0;
        int dimes = 0;
        int nickles = 0;
        int pennies = 0;

        if (change >= 100) {
            dollarCoins = change / 100;
            change = change % 100;
        }
        if (change >= 50) {
            halfDollar = change / 50;
            change = change % 50;
        }
        if (change >= 25) {
            quarters = change / 25;
            change = change % 25;
        }
        if (change >= 10) {
            dimes = change / 10;
            change = change % 10;
        }
        if (change >= 5) {
            nickles = change / 5;
            change = change % 5;
        }
        if (change > 0) {
            pennies = change;
        }

        out.println("Dollar Coins: " + dollarCoins);
        out.println("Half-Dollars: " + halfDollar);
        out.println("Quarters:     " + quarters);
        out.println("Dimes:        " + dimes);
        out.println("Nickels:      " + nickles);
        out.println("Pennies:      " + pennies);
        out.close();
    }

}
