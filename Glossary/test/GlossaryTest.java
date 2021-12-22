import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;

public class GlossaryTest {

    @Test
    public void importFileTermsTest() {
        String fileName = "terms.txt";
        Map<String, String> test = Glossary.importFile(fileName);
        Map<String, String> truth = new Map1L<>();
        truth.add("meaning",
                "something that one wishes to convey, especially by language");
        truth.add("term", "a word whose definition is in a glossary");
        truth.add("word",
                "a string of characters in a language, which has at least one character");
        truth.add("definition",
                "a sequence of words that gives meaning to a term");
        truth.add("glossary",
                "a list of difficult or specialized terms, with their definitions,"
                        + " usually near the end of a book");
        truth.add("language",
                "a set of strings of characters, each of which has meaning");
        truth.add("book", "a printed or written literary work");
        System.out.println(test);
        System.out.println(truth);
        assertEquals(truth, test);
    }

}
