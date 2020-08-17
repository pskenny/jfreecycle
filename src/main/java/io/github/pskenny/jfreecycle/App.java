package io.github.pskenny.jfreecycle;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Freecycle commandline utility
 */
public class App {

    public App(String groupId) {
        final String url = new StringBuilder().append("http://groups.freecycle.org/group/").append(groupId)
                .append("/posts/offer").toString();
        try {
            Document doc = Jsoup.connect(url).get();
            Element content = doc.getElementById("group_posts_table");
            Elements links = content.getElementsByTag("tr");

            links.forEach(x ->
                // Remove " See details" from text and print to stdout
                System.out.println(x.text().subSequence(0, x.text().length() - 12))
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: jfreecycle [GroupID]\n    Example: jfreecycle GalwayIE");
            System.exit(1);
        }
        
        new App(args[0]);
    }
}
