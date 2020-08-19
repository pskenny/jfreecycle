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
    public enum Type {WANTED, OFFER, ALL}

    public App(String groupId) {
        Collection<Post> posts = io.github.pskenny.libjfreecycle.util.PostUtil.getPosts(groupId, Post.Type.ALL);
        posts.forEach(System.out::println);
    }

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("jfreecycle").build()
                .description("Freecycle.org scraper.");
        parser.addArgument("groupid")
                .metavar("groupid")
                .type(String.class)
                .help("Freecycle group ID");
        try {
            Namespace res = parser.parseArgs(args);
            new App(res.getString("groupid"));
        } catch (ArgumentParserException e) {
            parser.handleError(e);
        }
    }
}
