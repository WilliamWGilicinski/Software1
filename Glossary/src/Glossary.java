import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A simple glossary framework that takes an input file and creates a glossary
 * with the terms.
 *
 * @author William Gilicinski
 * @version April 15th, 2021
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Creates a map out of all the words and definitions
     *
     * @param fileName
     *            The name of the input file
     * @requires fileName exists and has at least one term
     * @return Map<String, String> A map built with the structure (word,
     *         definition)
     */
    public static Map<String, String> importFile(String fileName) {
        Map<String, String> words = new Map1L<>();
        SimpleReader in = new SimpleReader1L(fileName);
        //Iterates over input file and adds keys and definitions
        while (!in.atEOS()) {
            String key = in.nextLine();
            //Checks if the key name is a blank line, if not then the loop stops
            if (key.length() > 0) {
                String definition = "";
                String temp = in.nextLine();
                int counter = 0;
                while (temp.length() > 0) {
                    if (counter > 0) {
                        definition += " " + temp;
                    } else {
                        definition += temp;
                        counter++;
                    }
                    temp = in.nextLine();
                }
                if (!words.hasKey(key)) {
                    words.add(key, definition);
                }
            }
        }
        in.close();
        return words;
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        Set<Character> newSet = new Set1L<>();

        for (int i = 0; i < str.length(); i++) {
            if (!newSet.contains(str.charAt(i))) {
                newSet.add(str.charAt(i));
            }
        }

        charSet.clear();
        charSet.add(newSet);

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String output = "";
        if (separators.contains(text.charAt(position))) {
            for (int i = position; i < text.length()
                    && separators.contains(text.charAt(i)); i++) {
                output += text.substring(i, i + 1);
            }
        } else {
            for (int i = position; i < text.length()
                    && !separators.contains(text.charAt(i)); i++) {
                output += text.substring(i, i + 1);
            }
        }
        return output;
    }

    /**
     * Writes the different term.html files.
     *
     * @param word
     *            term that is being defined.
     * @param folder
     *            folder that the file will be saved to.
     * @param terms
     *            The map of <keys, definition>
     * @param separators
     *            The set of characters that are separators in a sentence (
     *            .!?,)
     * @requires folder exists and word is in terms.
     * @ensures A file is created with the html code for the specified term in
     *          the correct folder
     */
    public static void writeToTermFile(String word, String folder,
            Map<String, String> terms, Set<Character> separators) {
        String htmlFile = folder + "/" + word + ".html";
        SimpleWriter out = new SimpleWriter1L(htmlFile);
        out.println("<html>");
        out.println("<head><title>" + word + "</title></head>");
        out.println("<body>");
        //Outputs the header which is red, bold, and italic.
        out.println("<h1><b><i><font color = \"red\">" + word
                + "</font></i></b></h1>");
        //Outputs the definition with appropriate links.
        out.println(linkedDefinition(terms, word, separators));
        out.println("<hr>");
        out.println("<p>Return to <a href=\"index.html\"index</a>index.</p>");
        out.close();
    }

    /**
     * Takes the definition from a term and finds if a word in the definition is
     * also in the list of terms. If it is the html page is linked
     *
     * @param terms
     *            The map of terms and definitions (word, definitions)
     * @param word
     *            The term who's definition is put in HTML
     * @param separators
     *            The set of separators ( ,.?'ect.) that are used to find only
     *            the words in the string
     * @return output The final html code that will go into the term.html
     */
    public static String linkedDefinition(Map<String, String> terms,
            String word, Set<Character> separators) {
        //Start string with blockquote which formats the definition to be a quote
        String output = "<blockquote>";

        int position = 0;
        while (position < terms.value(word).length()) {
            //Finds the next string built of either a word or separator
            String token = nextWordOrSeparator(terms.value(word), position,
                    separators);
            //If token is a term in the Map, it attaches a link to it
            if (terms.hasKey(token)) {
                output += "<a href=\"" + token + ".html\">" + token + "</a>";
                //Else the token is written without the link
            } else {
                output += token;
            }
            position += token.length();
        }

        //complete string
        output += "</blockquote>";

        return output;
    }

    /**
     * A simple comparator that compares strings in lexicographical order
     *
     * @return if the string is more, less, or equal to another.
     */
    public static class stringComp implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Writes the main index.html file and sorts the terms in alphabetical order
     *
     * @param terms
     *            The map of <words, definition>
     * @param folder
     *            The folder the file will be saved in.
     * @ensures index is created and the html code is written
     */
    public static void writeIndex(Map<String, String> terms, String folder) {
        //Creates the file and puts it in the right folder
        String htmlFile = folder + "/index.html";
        SimpleWriter out = new SimpleWriter1L(htmlFile);

        //Creates a queue of the terms in alphabetical order
        Queue<String> alphabetical = new Queue1L<>();
        for (Map.Pair<String, String> str : terms) {
            alphabetical.enqueue(str.key());
        }
        Comparator<String> order = new stringComp();
        alphabetical.sort(order);

        //Write main body of the file
        out.println("<html>");
        out.println("<head><title>Glossary</title></head>");
        out.println("<body>");
        out.println("<h1>Glossary</h1>");
        out.println("<hr>");
        out.println("<h2>Index</h2>");
        out.println("<ul>");
        //Writes the list of terms with the appropriate links
        while (alphabetical.length() > 0) {
            String temp = alphabetical.dequeue();
            out.println(
                    "<li><a href=\"" + temp + ".html\">" + temp + "</a></li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");

        out.close();
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

        //Gets user info about what the input file and output folder will be
        //used.
        out.print("Type the input file: ");
        String input = in.nextLine();
        out.print("Type the folder the files will be saved to: ");
        String folderName = in.nextLine();

        //Generates the set of separators used to find if a word used in the
        //definition is another word in the glossary.
        Set<Character> separators = new Set1L<>();
        String sep = " /.!?,";
        generateElements(sep, separators);

        //Creates the map of all the terms and definitions
        Map<String, String> terms = importFile(input);

        //Writes the index.html file
        writeIndex(terms, folderName);

        //Writes the individual term.html files
        for (Map.Pair<String, String> str : terms) {
            writeToTermFile(str.key(), folderName, terms, separators);
        }

        //Close the input and output streams
        in.close();
        out.close();
    }

}
