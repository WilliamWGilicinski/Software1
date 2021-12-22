import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Homework 17
 *
 * @author William Gilicinski
 * @version March 25, 2021
 */
public final class Homework17 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework17() {
        // no code needed here
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @param in
     * @ensures this = rev(#this)
     */
//    public static void flip(Queue<T> in) {
//        T[] temp = new T[in.length()];
//        for (int i = 0; i < temp.length; i++) {
//            temp[i] = in.dequeue;
//        }
//        for (int i = temp.length; i >= 0; i--) {
//            in.enqueue(temp[i]);
//        }
//
//    }

    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        Queue<Integer> temp = new Queue1L<>();

        output.close();
    }
}
