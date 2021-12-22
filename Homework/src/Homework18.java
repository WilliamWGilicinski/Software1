import java.util.Comparator;

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
public final class Homework18 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework18() {
        // no code needed here
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        String lowest = q.front();
        for (String word : q) {
            if (order.compare(word, lowest) < 0) {
                lowest = word;
            }
        }

        for (int i = 0; i < q.length(); i++) {
            String temp = q.dequeue();
            if (order.compare(temp, lowest) != 0) {
                q.enqueue(temp);
            }
        }

        return lowest;
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        Queue<String> temp = new Queue1L<>();
        int length = q.length();
        for (int i = 0; i < length; i++) {
            temp.enqueue(removeMin(q, order));
        }

        q.append(temp);

    }

    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

//        Queue<String> queue = new Queue1L<>();
//        Comparator<String> order = new Comparator<>();
//        queue.enqueue("bruh");
//        queue.enqueue("bru");
//        queue.enqueue("br");
//        queue.enqueue("b");
//        System.out.println(queue.toString());
//        sort(queue, )

        output.close();
    }
}
