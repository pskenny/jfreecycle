package io.github.pskenny.jfreecycle;

import java.text.SimpleDateFormat;
import java.util.Collection;

import io.github.pskenny.libjfreecycle.model.Post;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * Freecycle command-line utility. Super duper fragile to changes on Freecycle.
 * G'luck.
 */
public class App {

    public App(String groupId) {
        this(groupId, Post.Type.ALL);
    }

    public App(String groupId, Post.Type type) {
        Collection<Post> posts = io.github.pskenny.libjfreecycle.util.PostUtil.getPosts(groupId, type);
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd HH:mm");
        
        posts.forEach(post -> {
            StringBuilder sb = new StringBuilder();

            sb.append(post.getType());
            sb.append(" ");
            sb.append(formatter.format(post.getDate()));
            sb.append(" ");
            sb.append(post.getTitle());
            sb.append(" (");
            sb.append(post.getLocation());
            sb.append(") https://groups.freecycle.org/");
            sb.append(groupId);
            sb.append("/posts/");
            sb.append(post.getId());

            System.out.println(sb.toString());
        });
    }

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("jfreecycle").build()
                .description("Freecycle.org command-line utility.").version("0.1")
                .epilog("Examples:\n"
                        + "  jfreecycle GalwayIE # Display hundred most recent posts from GalwayIE group\n"
                        + "  jfreecycle -t offer GalwayIE # Display hundred most recent offer posts from GalwayIE group\n"
                        + "  jfreecycle -t wanted GalwayIE # Display hundred most recent wanted posts from GalwayIE group");
        parser.addArgument("groupid").metavar("GROUPID").type(String.class).required(true).help("Freecycle group ID");
        parser.addArgument("-t", "-type").metavar("TYPE").type(String.class).help("Enter \"offer\" or \"wanted\"");

        try {
            Namespace res = parser.parseArgs(args);
            String groupIdArgument = res.getString("groupid");
            Post.Type type = Post.Type.ALL;
            String typeArgument = res.getString("t");

            // Handle post type argument
            if (typeArgument != null) {
                if (typeArgument.equals("offer")) {
                    type = Post.Type.OFFER;
                } else if (typeArgument.equals("wanted")) {
                    type = Post.Type.WANTED;
                } else {
                    parser.printUsage();
                    System.exit(1);
                }
            }

            new App(groupIdArgument, type);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
        }
    }
}
