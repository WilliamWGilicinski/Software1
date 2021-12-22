import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void overlap_will_lliw_test() {
        String one = "will";
        String two = "lliw";
        int truth = 2;
        int test = StringReassembly.overlap(one, two);
        assertEquals(truth, test);
    }

    @Test
    public void combination_will_lliw_test() {
        String one = "will";
        String two = "lliw";
        int test = StringReassembly.overlap(one, two);
        String test2 = StringReassembly.combination(one, two, test);
        String truth = "williw";
        assertEquals(truth, test2);
    }

    @Test
    public void combinationSentenceTest() {
        String one = "A very long sentence that is not a single word";
        String two = "Not a long sentence but still not a word :)";
        int test = StringReassembly.overlap(one, two);
        String test2 = StringReassembly.combination(one, two, test);
        String truth = "A very long sentence that is not a single wordNot a long sentence but still not a word :)";
        assertEquals(truth, test2);
    }

    @Test
    public void combinationSentenceOverlapTest() {
        String one = "This sentence has an overlap with the other one";
        String two = "one more time for the people in the back";
        int test = StringReassembly.overlap(one, two);
        String test2 = StringReassembly.combination(one, two, test);
        String truth = "This sentence has an overlap with the other one more time for the people in the back";
        assertEquals(truth, test2);
    }

    @Test
    public void combinationSpaceATest() {
        String one = " ";
        String two = "a";
        int test = StringReassembly.overlap(one, two);
        String test2 = StringReassembly.combination(one, two, test);
        String truth = " a";
        assertEquals(truth, test2);
    }

    @Test
    public void addToSetAvoidingSubstringsBasic() {
        Set<String> test = new Set1L<>();
        test.add("Hello");
        test.add("Hi");
        test.add("Blank");
        Set<String> truth = test.newInstance();
        truth.add("Hello");
        truth.add("Hi");
        truth.add("Blank");
        StringReassembly.addToSetAvoidingSubstrings(test, "Guten Tag!");
        truth.add("Guten Tag!");
        assertEquals(truth, test);
    }

    @Test
    public void addToSetAvoidingSubstringsAdvanced() {
        Set<String> test = new Set1L<>();
        test.add("Hello");
        test.add("Hi");
        test.add("Blank");
        Set<String> truth = test.newInstance();
        truth.add("Hello");
        truth.add("Blank");
        truth.add("Highly swell");
        StringReassembly.addToSetAvoidingSubstrings(test, "Highly swell");
        assertEquals(truth, test);
    }

    @Test
    public void linesFromInputCheerTest() throws FileNotFoundException {
        SimpleReader reader = new SimpleReader1L("cheer");
        Set<String> test = StringReassembly.linesFromInput(reader);
        Set<String> truth = test.newInstance();
        truth.add("Bucks -- Beat");
        truth.add("Go Bucks");
        truth.add("Beat Mich");
        truth.add("Michigan~");
        truth.add("o Bucks -- B");
        assertEquals(truth, test);
    }

    @Test
    public void printWithLineSeperatorsSimpleTest() {
        String test = "Hello~Hi";
        SimpleReader testIn = new SimpleReader1L("testFile");
        SimpleReader truthIn = new SimpleReader1L("expected");
        SimpleWriter testOut = new SimpleWriter1L("testFile");
        StringReassembly.printWithLineSeparators(test, testOut);

        boolean same = true;
        while (!testIn.atEOS()) {
            if (!testIn.nextLine().equals(truthIn.nextLine())) {
                same = false;
            }
        }

        testIn.close();
        truthIn.close();
        assertTrue(same);

    }

    @Test
    public void fullTestDeclaration() {
        SimpleReader in = new SimpleReader1L("declaration");
        SimpleWriter out = new SimpleWriter1L("mainTest");
        Set<String> fragments = StringReassembly.linesFromInput(in);
        in.close();
        StringReassembly.assemble(fragments);
        if (fragments.size() == 1) {
            out.println();
            String text = fragments.removeAny();
            StringReassembly.printWithLineSeparators(text, out);
        } else {
            int fragmentNumber = 0;
            for (String str : fragments) {
                fragmentNumber++;
                out.println();
                out.println("--------------------");
                out.println("  -- Fragment #" + fragmentNumber + ": --");
                out.println("--------------------");
                StringReassembly.printWithLineSeparators(str, out);
            }
        }

        SimpleReader testIn = new SimpleReader1L("mainTest");
        SimpleReader truthIn = new SimpleReader1L("mainTestExpected");

        boolean same = true;
        while (!testIn.atEOS()) {
            if (!testIn.nextLine().equals(truthIn.nextLine())) {
                same = false;
            }
        }

        out.close();

    }

}
