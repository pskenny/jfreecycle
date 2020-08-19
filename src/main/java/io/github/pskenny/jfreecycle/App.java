package io.github.pskenny.jfreecycle;

import java.util.Collection;

import io.github.pskenny.libjfreecycle.model.Post;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * Freecycle commandline utility. Super duper fragile to changes on Freecycle.
 * G'luck.
 */
public class App {

    public App(String groupId) {
        this(groupId, Post.Type.ALL);
    }

    public App(String groupId, Post.Type type) {
        Collection<Post> posts = io.github.pskenny.libjfreecycle.util.PostUtil.getPosts(groupId, type);
        posts.forEach(System.out::println);
    }

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("jfreecycle").build().description("Freecycle.org command-line utility.").version("0.1");
        parser.addArgument("groupid").metavar("GROUPID").type(String.class).required(true).help("Freecycle group ID");
        parser.addArgument("-t", "-type").metavar("TYPE").type(String.class)
                .help("Enter \"offer\" or \"wanted\"");

        try {
            Namespace res = parser.parseArgs(args);
            String groupIdArgument = res.getString("groupid");
            Post.Type type = Post.Type.ALL;
            String typeArgument = res.getString("t");

            // Handle post type argument
            if (typeArgument != null){
                if (typeArgument.equals("offer")) {
                    type = Post.Type.OFFER;
                } else if (typeArgument.equals("wanted")) {
                    type = Post.Type.WANTED;
                } else {
                    parser.printUsage();
                    return;
                }
            }

            new App(groupIdArgument, type);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
        }
    }
}
