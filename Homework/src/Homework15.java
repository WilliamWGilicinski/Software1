import java.util.Arrays;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Homework 15
 *
 * @author William Gilicinski
 * @version March 23, 2021
 */
public final class Homework15 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework15() {
        // no code needed here
    }

    /**
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        Queue<Integer> temp = new Queue1L<>();

        int lowest = Integer.MAX_VALUE;
        int tempInt = 0;
        int length = q.length();

        for (int i = 0; i < length; i++) {
            tempInt = q.dequeue();
            if (tempInt < lowest) {
                lowest = tempInt;
            }
            temp.enqueue(tempInt);
        }

        for (int i = 0; i < length; i++) {
            tempInt = temp.dequeue();
            q.enqueue(tempInt);
        }

        return lowest;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        Queue<Integer> temp = new Queue1L<>();

        int lowest = Integer.MAX_VALUE;
        int highest = Integer.MIN_VALUE;
        int tempInt = 0;
        int tempInt2 = 0;
        int length = q.length() / 2;

        for (int i = 0; i < length; i++) {
            tempInt = q.dequeue();
            tempInt2 = q.dequeue();
            int localMin = 0;
            int localMax = 0;
            if (tempInt < tempInt2) {
                localMin = tempInt;
                localMax = tempInt2;
            } else {
                localMin = tempInt2;
                localMax = tempInt;
            }
            if (localMin < lowest) {
                lowest = tempInt;
            }
            if (localMax > highest) {
                highest = tempInt;
            }
            temp.enqueue(tempInt);
            temp.enqueue(tempInt2);
        }

        for (int i = 0; i < length * 2; i++) {
            tempInt = temp.dequeue();
            q.enqueue(tempInt);
        }

        int[] minMax = { lowest, highest };

        return minMax;
    }

    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        Queue<Integer> q = new Queue1L();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(8);
        q.enqueue(3);
        System.out.println(q);
        System.out.println(Arrays.toString(minAndMax(q)));
        System.out.println(q);

        input.close();
        output.close();
    }
}
