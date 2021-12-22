import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

public class NNtoStringWithCommasTest {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas6.toStringWithCommas(n);
    }

    @Test
    public void testZero() {
        NaturalNumber zero = new NaturalNumber1L();
        String truth = "0";
        String test = redirectToMethodUnderTest(zero);
        System.out.println(test);
        assertEquals(truth, test);
    }

    @Test
    public void test1000() {
        NaturalNumber oneThousand = new NaturalNumber1L(1000);
        String truth = "1,000";
        String test = redirectToMethodUnderTest(oneThousand);
        assertEquals(truth, test);
    }

    @Test
    public void test1234567890() {
        NaturalNumber large = new NaturalNumber1L(1234567890);
        String truth = "1,234,567,890";
        String test = redirectToMethodUnderTest(large);
        assertEquals(truth, test);
    }

    @Test
    public void testIntMax() {
        NaturalNumber max = new NaturalNumber1L(Integer.MAX_VALUE);
        String truth = "2,147,483,647";
        String test = redirectToMethodUnderTest(max);
        assertEquals(truth, test);
    }

    @Test
    public void testAboveMax() {
        NaturalNumber aboveMax = new NaturalNumber1L(Integer.MAX_VALUE);
        NaturalNumber max = new NaturalNumber1L(Integer.MAX_VALUE);
        aboveMax.add(max);
        String truth = "4,294,967,294";
        String test = redirectToMethodUnderTest(aboveMax);
        assertEquals(truth, test);
    }

    @Test
    public void test10000() {
        NaturalNumber tenThousand = new NaturalNumber1L(10000);
        String truth = "10,000";
        String test = redirectToMethodUnderTest(tenThousand);
        assertEquals(truth, test);
    }

}
