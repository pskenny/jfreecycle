package io.github.pskenny.jfreecycle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Freecycle commandline utility. Super duper fragile to changes on Freecycle. G'luck.
 */
public class App {

    public App(String groupId) {
        final String url = new StringBuilder().append("http://groups.freecycle.org/group/").append(groupId)
                .append("/posts/offer").toString();
        try {
            Document doc = Jsoup.connect(url).get();
            Element table = doc.getElementById("group_posts_table");
            Elements tableRow = table.getElementsByTag("tr");

            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);

            tableRow.forEach(x -> {
                // ☠️ Beware of the String/DOM manipulation filth that follows ☠️
                // Column 1 contains post type (OFFER/WANTED), date/time, post id
                String column1 = x.child(0).text();
                String[] splitCol1 = column1.split(" ");
                String type = splitCol1[1];
                String datetime = column1.substring((splitCol1[0].length() + splitCol1[1].length() + 2),
                        column1.length() - (splitCol1[7].length() + 1));
                long postId = Long.parseLong(
                        splitCol1[splitCol1.length - 1].substring(2, splitCol1[splitCol1.length - 1].length() - 1));
                // Column two contains the title and location
                String column2 = x.child(1).text();
                String title = x.child(1).child(0).text();
                String location = column2.substring(title.length() + 2, column2.length() - 13);

                Post p = new Post();
                if (type.equals("OFFER")) {
                    p.setType(Post.Type.OFFER);
                } else {
                    p.setType(Post.Type.WANTED);
                }
                p.setId(postId);
                p.setTitle(title);
                p.setLocation(location);

                try {
                    p.setDate(formatter.parse(datetime));
                } catch (java.text.ParseException ex) {
                    // Don't care atm
                }
                
                System.out.println(p);
            });
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
