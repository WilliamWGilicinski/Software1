import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author William Gilicinski
 * @version March 16th, 2021
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {

        NaturalNumber answer = new NaturalNumber1L();

        /**
         * If the number of children is zero then that means the node has to be
         * a number value and is returned as a natural number. If not then that
         * means an operator is involved. The if else if statements look through
         * which operator is being used and continuously recurse until finally
         * the left and right side of the equations are two natural numbers and
         * evaluate them with the correct operator.
         */
        if (exp.numberOfChildren() != 0) {

            if (exp.label().equals("plus")) {
                NaturalNumber plus = (evaluate(exp.child(0)));
                answer.copyFrom(plus);
                answer.add(evaluate(exp.child(1)));
            } else if (exp.label().equals("minus")) {
                NaturalNumber minus = (evaluate(exp.child(0)));
                answer.copyFrom(minus);
                //This reports that the difference is negative, which goes
                //against the contract of evaluate.
                Reporter.fatalErrorToConsole(
                        "Error: subtraction cannot result in a negative");
                answer.subtract(evaluate(exp.child(1)));
            } else if (exp.label().equals("times")) {
                NaturalNumber times = (evaluate(exp.child(0)));
                answer.copyFrom(times);
                answer.multiply(evaluate(exp.child(1)));
            } else {
                NaturalNumber divide = (evaluate(exp.child(0)));
                answer.copyFrom(divide);
                //This reports that the code encountered a divide by zero error
                Reporter.fatalErrorToConsole("Error: cannot divide by zero");
                answer.divide(evaluate(exp.child(1)));
            }

        } else {
            //Copies the node value into the answer variable
            int num = Integer.parseInt(exp.attributeValue("value"));
            NaturalNumber copy = new NaturalNumber1L(num);
            answer.copyFrom(copy);
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