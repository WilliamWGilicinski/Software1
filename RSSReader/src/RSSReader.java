import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author William Gilicinski
 *
 */
public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        //The title, link, and descriptions locations as a child are found
        String title = "debug";
        int titleIdx = getChildElement(channel, "title");

        int linkIdx = getChildElement(channel, "link");
        String link = channel.child(linkIdx).child(0).toString();

        String description = "debug";
        int descIdx = getChildElement(channel, "description");

        //Finds if the XML has a title
        if (channel.child(titleIdx).numberOfChildren() < 1) {
            title = "Empty Title";
        } else {
            title = channel.child(titleIdx).child(0).toString();
        }

        //Finds if the XML has a description
        if (channel.child(descIdx).numberOfChildren() < 1) {
            description = "No Description";
        } else {
            description = channel.child(descIdx).child(0).toString();
        }

        //Prints out the proper header. Originally much more condensed, but
        //harder to read.
        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        out.println(title);
        out.println("</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(" <h1><a href = \"" + link + "\">" + title + "</a></h1>");
        out.println(" <p>" + description + "</p>");
        out.println(" <table border=\"1\">");
        out.println("  <tr>");
        out.println("   <th>Date</th>");
        out.println("   <th>Source</th>");
        out.println("   <th>News</th>");
        out.println("  </tr>");

    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        //Standard printing of the footers
        out.println(" </table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int idx = -1;
        //The for loops stops after the idx is updated
        for (int i = 0; i < xml.numberOfChildren() && idx < 0; i++) {
            if (tag.equals(xml.child(i).label())) {
                idx = i;
            }
        }
        return idx;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        //Finds all of the indexs of the different child elements
        int titleIdx = getChildElement(item, "title");
        int descIdx = getChildElement(item, "description");
        int linkIdx = getChildElement(item, "link");
        int pubIdx = getChildElement(item, "pubDate");
        int sourceIdx = getChildElement(item, "source");

        //Establishes some variables such as string and url
        String title, pubDate, source;
        boolean titleUrl = false;
        boolean sourceUrl = false;
        if (sourceIdx > -1) {
            sourceUrl = item.child(sourceIdx).hasAttribute("url");
        }

        //Finds if the XML has a title and if not finds if it has a description
        //If the XML doc has neither, no title available is printed
        if (item.child(titleIdx).numberOfChildren() < 1 || titleIdx < 0) {
            if (item.child(descIdx).numberOfChildren() < 1 || descIdx < 0) {
                title = "No title available";
            } else {
                title = item.child(descIdx).child(0).toString();
                titleUrl = true; //Hyperlinks title if found
            }
        } else {
            title = item.child(titleIdx).child(0).toString();
            titleUrl = true;
        }

        //Finds if there is a source for in the XML
        if (sourceIdx < 0) {
            source = "No source available";
        } else {
            source = item.child(sourceIdx).child(0).toString();
        }

        //Finds if there is a publishing date in the XML
        if (item.child(pubIdx).numberOfChildren() < 1 || pubIdx < 0) {
            pubDate = "No date available";
        } else {
            pubDate = item.child(pubIdx).child(0).toString();
        }

        //Final footer print statements
        out.println("  <tr>");
        out.println("   <td>" + pubDate + "</td>");
        if (sourceUrl) {
            out.println("   <td><a href=\""
                    + item.child(sourceIdx).attributeValue("url") + "\">"
                    + source + "</a></td>");
        } else {
            out.println("   <td>" + source + "</td>");
        }

        if (titleUrl) {
            out.println("   <td><a href=\"" + item.child(linkIdx).child(0)
                    + "\">" + title + "</a></td>");
        } else {
            out.println("   <td>" + title + "</td>");
        }

        out.println("  </tr>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //User must enter a valid RSS feed or the program will crash or stop
        //running.
        out.println("Enter an RSS 2.0 URL");
        String url = in.nextLine();
        XMLTree xml = new XMLTree1(url);

        xml.display();

        if (xml.attributeValue("version").equals("2.0")) {

            //User enters the output file
            out.println("Choose a file to output the HTML page");
            String HTMLFile = in.nextLine();
            SimpleWriter HTMLWriter = new SimpleWriter1L(HTMLFile);

            XMLTree channel = xml.child(0);

            outputHeader(channel, HTMLWriter);
            //The for loop runs processItem for every item
            int firstItem = getChildElement(channel, "item");
            for (int i = firstItem; i < channel.numberOfChildren(); i++) {
                processItem(channel.child(i), HTMLWriter);
            }

            outputFooter(HTMLWriter);

            in.close();
            out.close();
            HTMLWriter.close();
        }
    }

}