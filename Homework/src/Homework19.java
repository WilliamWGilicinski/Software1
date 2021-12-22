import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Homework 17
 *
 * @author William Gilicinski
 * @version March 25, 2021
 */
public final class Homework19 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Homework19() {
        // no code needed here
    }

//    /**
//     * Reverses ("flips") {@code this}.
//     *
//     * @updates this
//     * @ensures this = rev(#this)
//     */
//    public void flip() {
//        Stack<T> reverse = new Stack1L<>();
//        int length = this.length();
//        for (int i = 0; i < length; i++) {
//            reverse.push(this.pop());
//        }
//        for (int i = 0; i < length; i++) {
//            this.push(reverse.pop());
//        }
//
//    }
//
//    /**
//     * Reverses ("flips") {@code this}.
//     *
//     * @updates this
//     * @ensures this = rev(#this)
//     */
//    public void flip() {
//        Sequence<T> reverse = new Sequence1L<>();
//        int length = this.length();
//        for (int i = 0; i < length; i++) {
//            reverse.add(0, this.remove(this.length() - 1));
//        }
//        for (int i = 0; i < length; i++) {
//            this.add(0, reverse.remove(0));
//        }
//    }
//
//    /**
//     * Reverses ("flips") {@code this}.
//     *
//     * @updates this
//     * @ensures this = rev(#this)
//     */
//    public void flipRecursive() {
//        if (this.length() == 2) {
//            T temp = this.remove(1);
//            this.add(0, temp);
//        } else if (this.length() <= 1) {
//
//        } else {
//            this.remove(0);
//            this.flipRecursive();
//        }
//    }

    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        Stack<Integer> og = new Stack1L<>();
        og.push(5);
        og.flip();

        output.close();
    }
}
