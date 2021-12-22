import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * MonteCarlo program built with methods
 *
 * @author William Gilicinski
 * @version January 26th, 2021
 */
public final class Homework4 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework4() {
        // no code needed here
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        boolean inCircle = false;

        if ((xCoord - 1) * (xCoord - 1) + (yCoord - 1) * (yCoord - 1) < 1) {
            inCircle = true;
        }
        return inCircle;
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        int counter = 0;
        int inCircle = 0;

        Random rnd1 = new Random1L();
        Random rnd2 = new Random1L();

        while (counter < n) {
            counter++;
            double x = 2 * rnd1.nextDouble();
            double y = 2 * rnd2.nextDouble();
            if (pointIsInCircle(x, y)) {
                inCircle++;
            }
        }

        return inCircle;
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
        output.print("Number of points: ");
        int n = input.nextInteger();

        int numInCircle = numberOfPointsInCircle(n);
        double pi = 4.0 * numInCircle / n;
        output.println(pi);

        input.close();
        output.close();
    }
}
