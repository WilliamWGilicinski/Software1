import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author William Gilicinski
 * @version March 16th, 2021
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        int answer = 0;

        /**
         * If the number of children is zero than that means the node must be an
         * integer and the answer with just one number is the number. That's why
         * if there are no children for the node the attributeValue is returned
         * as the answer. The if else sequence is used when there is an operator
         * involved and for both sides of the equation, they are found
         * recursively in order to align with the order of operations.
         */
        if (exp.numberOfChildren() != 0) {

            if (exp.label().equals("plus")) {
                answer = (evaluate(exp.child(0)) + evaluate(exp.child(1)));
            } else if (exp.label().equals("minus")) {
                answer = (evaluate(exp.child(0)) - evaluate(exp.child(1)));
            } else if (exp.label().equals("times")) {
                answer = (evaluate(exp.child(0)) * evaluate(exp.child(1)));
            } else {
                answer = (evaluate(exp.child(0)) / evaluate(exp.child(1)));
            }

        } else {
            answer = Integer.parseInt(exp.attributeValue("value"));
        }
        return answer;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}