//public class Homework14 {
//
//    //temp is a placeholder for the class that contains the method.
//
//    @Test
//    public void testZero() {
//        NaturalNumber zero = new NaturalNumber1L();
//        String truth = "0";
//        String test = temp.toStringWithCommas(zero);
//        assertEquals(truth, test, "0");
//    }
//
//    @Test
//    public void test1000() {
//        NaturalNumber oneThousand = new NaturalNumber1L(1000);
//        String truth = "1,000";
//        String test = temp.toStringWithCommas(oneThousand);
//        assertEquals(truth, test, "1,000");
//    }
//
//    @Test
//    public void test1234567890() {
//        NaturalNumber large = new NaturalNumber1L(1234567890);
//        String truth = "1,234,567,890";
//        String test = temp.toStringWithCommas(large);
//        assertEquals(truth, test, "1,234,567,890");
//    }
//
//    @Test
//    public void testIntMax() {
//        NaturalNumber max = new NaturalNumber1L(Integer.MAX_VALUE);
//        String truth = "2,147,483,647";
//        String test = temp.toStringWithCommas(max);
//        assertEquals(truth, test, "2,147,483,647");
//    }
//
//    @Test
//    public void testAboveMax() {
//        NaturalNumber aboveMax = new NaturalNumber1L(Integer.MAX_VALUE);
//        NaturalNumber max = new NaturalNumber1L(Integer.MAX_VALUE);
//        aboveMax.add(max);
//        String truth = "4,294,967,294";
//        String test = temp.toStringWithCommas(aboveMax);
//        assertEquals(truth, test, "4,294,967,294");
//    }
//
//    @Test
//    public void test10000() {
//        NaturalNumber tenThousand = new NaturalNumber(10000);
//        String truth = "10,000";
//        String test = temp.toStringWithCommas(tenThousand);
//        assertEquals(truth, test, "10,000");
//        }
//    }
//
//}
