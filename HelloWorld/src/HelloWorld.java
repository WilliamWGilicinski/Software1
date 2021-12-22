import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program.
 *
 * @author William Gilicinski
 */

public final class HelloWorld {

    /**
     * No-argument constructor--private to prevent instantiation.
     */
    private HelloWorld() {
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
        out.println(24 * 60 * 60 * 1000 * 1000);
        out.close();

    }

}
