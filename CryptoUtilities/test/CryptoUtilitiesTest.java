import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author William Gilicinski
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsWitness15() {
        NaturalNumber fifteen = new NaturalNumber2(15);
        NaturalNumber two = new NaturalNumber2(2);
        boolean truth = true;
        boolean test = CryptoUtilities.isWitnessToCompositeness(two, fifteen);
        assertEquals(truth, test);

    }

    @Test
    public void testPowerMod_3_2_5() {
        NaturalNumber three = new NaturalNumber2(3);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber five = new NaturalNumber2(5);
        NaturalNumber four = new NaturalNumber2(4);
        CryptoUtilities.powerMod(three, two, five);
        assertEquals(four, three);
    }

    @Test
    public void testgenerateNextLikelyPrime4() {
        NaturalNumber four = new NaturalNumber2(4);
        NaturalNumber truth = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(four);
        assertEquals(truth, four);

    }

    @Test
    public void testgenerateNextLikelyPrime666() {
        NaturalNumber sixes = new NaturalNumber2(666);
        NaturalNumber truth = new NaturalNumber2(673);
        CryptoUtilities.generateNextLikelyPrime(sixes);
        assertEquals(truth, sixes);
    }

    @Test
    public void testIsPrime2_2() {
        NaturalNumber two = new NaturalNumber2(2);
        boolean truth = true;
        boolean test = CryptoUtilities.isPrime2(two);
        assertEquals(truth, test);
    }

    @Test
    public void testIsPrime2_7984651() {
        NaturalNumber big = new NaturalNumber2(7984651);
        boolean truth = true;
        boolean test = CryptoUtilities.isPrime2(big);
        assertEquals(truth, test);
    }

    @Test
    public void testIsPrime2_9() {
        NaturalNumber nine = new NaturalNumber2(9);
        boolean truth = false;
        boolean test = CryptoUtilities.isPrime2(nine);
        assertEquals(truth, test);
    }

    @Test
    public void testIsEven3() {
        NaturalNumber three = new NaturalNumber2(3);
        boolean truth = false;
        boolean test = CryptoUtilities.isEven(three);
        assertEquals(truth, test);
    }

    @Test
    public void testIsWitnessToCompositness_3_10() {
        NaturalNumber three = new NaturalNumber2(3);
        NaturalNumber ten = new NaturalNumber2(10);
        boolean truth = true;
        boolean test = CryptoUtilities.isWitnessToCompositeness(three, ten);
        assertEquals(truth, test);
    }

    @Test
    public void testIsWitnessToCompositness_2_4() {
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber four = new NaturalNumber2(4);
        boolean truth = true;
        boolean test = CryptoUtilities.isWitnessToCompositeness(two, four);
        assertEquals(truth, test);
    }
}
