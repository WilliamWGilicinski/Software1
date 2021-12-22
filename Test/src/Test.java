
/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.queue.Queue;
import components.set.Set;
import components.simplereader.SimpleReader;
import components.simplewriter.SimpleWriter;
import components.utilities.FormatChecker;

public final class Test {
    /**
     * Default constructor--private to prevent instantiation.
     */
    private Test() {
        // no code needed here
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        boolean valid = false;
        int posInt = 0;
        while (!valid) {
            out.println("Enter a positive integer");
            String str = in.nextLine();
            if (FormatChecker.canParseInt(str)) {
                posInt = Integer.parseInt(str);
                if (posInt >= 0) {
                    valid = true;
                }
            }
        }
        return posInt;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */

    public static int secondSmallest(int[] arr) {
        int secondSmallest = arr[0];
        int smallest = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                ;
            }
        }

        return secondSmallest;
    }

    /*
     * Determine if the char query is in the string str
     *
     * @param str String to examine
     *
     * @param query character to look for in str
     *
     * @return true if query is in str, false otherwise.
     */
    public static boolean match(String str, char query) {
        boolean match = false;
        if (str.length() > 0) {
            String temp = str.substring(1, str.length());
            match = match(temp, query);
            if (str.charAt(0) == query) {
                match = true;
            }
        }
        return match;
    }

    public static int mystery(int n) {
        int result = 0;
        if (n > 0) {
            result = mystery(n - 1) + 1;
        }
        return result;
    }

    public static int[] squares(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] * numbers[i];
        }
        return numbers;
    }

    public static int sumEvens(int n) {
        int length;
        int tempNum = 0, sum = 0;
        for (length = 0; length < n; length++) {
            length *= 10;
        }

        for (int i = 0; i < length; i++) {
            tempNum = n % 10;
            n = n / 10;
            if (tempNum % 2 == 0) {
                sum += tempNum;
            }
        }
        return sum;
    }

    public static boolean pointInCircle(double xCoord, double yCoord) {

        boolean inCircle = false;

        double relX = Math.abs(2 - xCoord);
        double relY = Math.abs(1 - yCoord);
        double hyp = relY * relY + relX * relX;

        if (hyp < 9) {
            inCircle = true;
        }
        return inCircle;
    }

    public static double sum_frac(int n) {
        double denominator = 1;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += 1 / denominator;
            denominator += 2;
        }
        return sum;
    }

    /*
     * Determine the index of str in list between indexes start and end or -1 if
     * it is not between start and end using a recursive linear search.
     *
     * @param str String to examine
     *
     * @param list List to search for string
     *
     * @param start first position to start your linear search from
     *
     * @param end last position to end your linear search at
     *
     * @return index of any occurrence of str in list if it is between start and
     * end, -1 otherwise.
     */
    public static int recursiveLinearSearch(String str, List<String> list,
            int start, int end) {
        int result = -1;
        if (start <= end) {
            result = recursiveLinearSearch(str, list, start + 1, end);
            if (str.equals(list.get(start))) {
                result = start;
            }
        }
        return result;
    }

    /*
     * Prints a countdown based on the number supplied by the caller followed by
     * a 'Blastoff!'
     *
     * @param val integer to use for countdown
     *
     * @precond val >= 0
     */

    public static void countdown(int val) {

        if (val > 0) {
            System.out.println(val);
            countdown(val - 1);
        } else {
            System.out.println("Blastoff!");
        }

    }

    /*
     * Return a new stack of elements in orders without Pancakes. The original
     * stack orders will be empty when this method ends.
     *
     * @param orders stack of restaurant orders
     *
     * @postcond orders is empty
     *
     * @return stack containing what was in orders without Pancakes in the same
     * order
     */
    public static Deque<String> pancakesOut(Deque<String> orders) {
        Deque<String> stack = new LinkedList<String>();

        for (int i = 0; i < orders.size(); i++) {
            String temp = orders.pop();
            if (!temp.equals("Pancakes")) {
                stack.push(temp);
            }
        }

        for (int i = 0; i < stack.size(); i++) {
            orders.push(stack.pop());
        }

        return orders;
    }

    public static void pp3(int[] arr, int num) {
        arr[num] = 17;
        num = arr.length;
    }

    public static void mystery3(NaturalNumber[] a, NaturalNumber n) {
        System.out.println(Arrays.toString(a) + " " + n);
        n = a[0];
        System.out.println(Arrays.toString(a) + " " + n);
        a[0] = a[1];
        System.out.println(Arrays.toString(a) + " " + n);
        a[1] = n;
        System.out.println(Arrays.toString(a) + " " + n);
        n.decrement();
        System.out.println(Arrays.toString(a) + " " + n);
    }

    private static void mystery2(String x, NaturalNumber n) {
        x = x + "bux";
        n = new NaturalNumber1L(3);
    }

    private static void makeSetFromFile(SimpleReader in, Set<String> makeSet) {

        while (!in.atEOS()) {
            String s = in.nextLine();
            if (!makeSet.contains(s)) {
                makeSet.add(s);
            }
        }
    }

    /**
     * Returns the sum of integers in a queue
     *
     * @param si
     *            - the queue to be summed
     * @requires si /= <>
     * @ensures sumQueue = [sum of all entries in si]
     */
    private static int sumQueue(Queue<Integer> si) {

        int total = si.dequeue();
        if (si.length() != 0) {
            total += sumQueue(si);
        }
        return total;
    }

    /**
     * Reports whether receiver is a subset of another set
     *
     * @ensures isSubset = (this is a subset of s)
     */
//    public boolean isSubset(Set<T> s) {
//
//        boolean subset = true;
//        for (T item : this) {
//            if (!s.contains(item)) {
//                subset = false;
//            }
//        }
//        return subset;
//
//    }

    //Come back to 21

//    SimpleReader keyboard = new SimpleReader1L();
//    SimpleWriter console = new SimpleWriter1L();
//    int diffCount = 0, lineCount = 0;
//
//    //Gets the file names
//    console.print("Enter the name of a file: ");
//    String fileOne = keyboard.nextLine();
//    console.print("Enter the name of another file: ");
//    String fileTwo = keyboard.nextLine();
//
//    //Creates reader objects to read the files
//    SimpleReader fileOneRead = new SimpleReader1L(fileOne);
//    SimpleReader fileTwoRead = new SimpleReader1L(fileTwo);
//
//    //Iterates through files and compares line by line
//    while (!fileOneRead.atEOS() && !fileTwoRead.atEOS()) {
//        lineCount++;
//        String temp1 = fileOneRead.nextLine();
//        String temp2 = fileTwoRead.nextLine();
//        if (!temp1.equals(temp2)) {
//            diffCount++;
//        }
//    }
//
//    //Output results
//    console.println("Total lines compared: " + lineCount);
//    console.println("The number of different lines was: " + diffCount);
//
//    //Close input and output streams
//    keyboard.close();
//    fileOneRead.close();
//    fileTwoRead.close();
//    console.close();

    private static void mystery1(int[] a, int i) {
        i = a.length;
        a = new int[i];
        for (int j = 0; j < i; j++) {
            a[j]++;
        }
    }

    public static void main(String[] args) {

        NaturalNumber n = new NaturalNumber2();
        NaturalNumber k = n;
        n = new NaturalNumber1L();

        n.add(k);

        System.out.println(k.getClass());

    }
}
